package se.kodion.contacts.session;

import org.eclipse.jetty.util.security.Credential;
import org.joda.time.DateTime;
import se.kodion.contacts.User;
import se.kodion.contacts.dao.SessionDAO;

public class SessionManager {

    private final SessionDAO sessionDAO;

    public SessionManager(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    public String createSession(User user) {
        String token = Credential.MD5.digest("kodion-contacts" + user.getId() + "+" + DateTime.now().getMillis());
        sessionDAO.createSession(token, user.getId());
        return token;
    }
}
