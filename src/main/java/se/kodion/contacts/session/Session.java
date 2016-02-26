package se.kodion.contacts.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {

    private String token;
    private long userId;
    private DateTime createdDate;

    public Session() {
    }

    private Session(String token, long userId, DateTime createdDate) {
        this.token = token;
        this.userId = userId;
        this.createdDate = createdDate;
    }

    @JsonProperty
    public String getToken() {
        return token;
    }

    @JsonProperty
    public long getUserId() {
        return userId;
    }

    @JsonProperty
    public DateTime getCreatedDate() {
        return createdDate;
    }

    public static class SessionMapper implements ResultSetMapper<Session> {
        public Session map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            return new Session(r.getString("token"), r.getInt("userId"), new DateTime(r.getDate("createdDate").getTime()));
        }
    }
}
