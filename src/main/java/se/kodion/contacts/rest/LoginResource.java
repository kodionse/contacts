package se.kodion.contacts.rest;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.kodion.contacts.User;
import se.kodion.contacts.dao.ContactDAO;
import se.kodion.contacts.dao.SessionDAO;
import se.kodion.contacts.dao.UserDAO;
import se.kodion.contacts.session.SessionManager;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Path("/login")
public class LoginResource {

    private static final Logger log = LoggerFactory.getLogger(LoginResource.class);

    private static final String CLIENT_ID = "250601506540-5md5fbnqno5r9400ggmibven2pve5ve2.apps.googleusercontent.com";

    private static final ApacheHttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

    private final SessionManager sessionManager;
    private final UserDAO userDAO;

    public LoginResource(SessionManager sessionManager, UserDAO userDAO) {
        this.sessionManager = sessionManager;
        this.userDAO = userDAO;
    }

    @POST
    @Path("google")
    public Response google(final String idTokenString) {
        log.debug("Calling login/google with {}", idTokenString);

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
                .setAudience(Arrays.asList(CLIENT_ID))
                // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
                // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
                // "accounts.google.com". If you need to verify tokens from multiple sources, build
                // a GoogleIdTokenVerifier for each issuer and try them both.
                .setIssuer("accounts.google.com")
                .build();

        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (IOException | GeneralSecurityException e) {
            log.error("ERROR", e);
        }

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            log.debug("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            String googleId = payload.getSubject();

            User user = userDAO.findUserByGoogleId(googleId);
            if (user == null) {
                userDAO.insert(payload.getEmail(), googleId);
                user = userDAO.findUserByGoogleId(googleId);
                if (user == null) {
                    log.error("Failed to create new user!");
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                }
            }
            String sessionToken = sessionManager.createSession(user);

            return Response.accepted(sessionToken).build();
        } else {
            log.warn("Invalid ID token!");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
