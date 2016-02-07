package se.kodion.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Contact {

    private long id;
    private String firstName;
    private String lastName;

    public Contact() {
    }

    public Contact(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    public static class ContactMapper implements ResultSetMapper<Contact> {
        public Contact map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            return new Contact(r.getInt("id"), r.getString("firstName"), r.getString("lastName"));
        }
    }
}
