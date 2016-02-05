package se.kodion.contacts;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import se.kodion.contacts.health.ContactsConfigurationHealthCheck;

public class ContactsApplication extends Application<ContactsConfiguration> {

    public static void main(String[] args) throws Exception {
        new ContactsApplication().run(args);
    }

    @Override
    public String getName() {
        return "Kodion Contacts";
    }

    @Override
    public void initialize(Bootstrap<ContactsConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(ContactsConfiguration configuration, Environment environment) throws Exception {
        String test = configuration.getTest();
        System.out.println(test);

        final ContactsConfigurationHealthCheck healthCheck =
                new ContactsConfigurationHealthCheck(configuration);
        environment.healthChecks().register("test", healthCheck);

        //environment.jersey().register(resource);

    }
}
