package se.kodion.contacts.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import se.kodion.contacts.session.Session;

@RegisterMapper(Session.SessionMapper.class)
public interface SessionDAO {

    @SqlUpdate("INSERT INTO sessions (token, userId) values (:token, :userId)")
    void createSession(@Bind("token") String token, @Bind("userId") long userId);

    @SqlQuery("SELECT token, userId, createdDate FROM session WHERE token = :token")
    Session findSession(@Bind("token") String token);

    void close();


}
