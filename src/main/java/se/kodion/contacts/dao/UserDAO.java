package se.kodion.contacts.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import se.kodion.contacts.Contact;
import se.kodion.contacts.User;

import java.util.Iterator;

@RegisterMapper(User.UserMapper.class)
public interface UserDAO {

    @SqlUpdate("INSERT INTO users (email, googleId) values (:email, :googleId)")
    void insert(@Bind("email") String email, @Bind("googleId") String googleId);

    @SqlQuery("SELECT id, email, googleId FROM users WHERE id = :id")
    User findUserById(@Bind("id") int id);

    @SqlQuery("SELECT id, email, googleId FROM users WHERE googleId = :googleId")
    User findUserByGoogleId(@Bind("googleId") String googleId);

    void close();
}
