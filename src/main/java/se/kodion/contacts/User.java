package se.kodion.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private int id;
    private String email;
    private String googleId;

    public User() {
    }

    public User(int id, String email, String googleId) {
        this.id = id;
        this.email = email;
        this.googleId = googleId;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public String getGoogleId() {
        return googleId;
    }

    public static class UserMapper implements ResultSetMapper<User> {
        public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            return new User(r.getInt("id"), r.getString("email"), r.getString("googleId"));
        }
    }
}
