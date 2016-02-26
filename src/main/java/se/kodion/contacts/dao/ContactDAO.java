package se.kodion.contacts.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import se.kodion.contacts.Contact;

import java.util.Iterator;

@RegisterMapper(Contact.ContactMapper.class)
public interface ContactDAO {

    @SqlUpdate("INSERT INTO contacts (firstName, lastName) values (:firstName, :lastName)")
    void insert(@Bind("firstName") String firstName, @Bind("lastName") String lastName);

    @SqlQuery("SELECT id, firstName, lastName FROM contacts WHERE id = :id")
    Contact findContactById(@Bind("id") long id);

    @SqlQuery("SELECT id, firstName, lastName FROM contacts WHERE googleId = :googleId")
    Contact findContactByGoogleId(@Bind("googleId") String googleId);

    @SqlQuery("SELECT * FROM contacts ORDER BY id ASC")
    Iterator<Contact> getAllContacts();

    /**
     * close with no args is used to close the connection
     */
    void close();
}
