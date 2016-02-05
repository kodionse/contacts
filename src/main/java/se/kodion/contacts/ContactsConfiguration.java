package se.kodion.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ContactsConfiguration extends Configuration {

    @NotEmpty
    private String test;

    @JsonProperty
    public String getTest() {
        return test;
    }

}
