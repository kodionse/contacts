package se.kodion.contacts;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.kodion.contacts.dao.ContactDAO;
import se.kodion.contacts.health.ContactsConfigurationHealthCheck;
import se.kodion.contacts.rest.ContactsResource;

public class ContactsApplication extends Application<ContactsConfiguration> {

    private final static Logger logger = LoggerFactory.getLogger(ContactsApplication.class);

    public static void main(String[] args) throws Exception {
        logger.info("Starting Contacts app...");
        ContactsApplication app = new ContactsApplication();
        app.run(args);
        logger.info("Contacts app started.");
    }

    @Override
    public String getName() {
        return "Kodion Contacts";
    }

    @Override
    public void initialize(Bootstrap<ContactsConfiguration> bootstrap) {
        logger.trace("initialize()");
        bootstrap.addBundle(new DBIExceptionsBundle());
    }

    @Override
    public void run(ContactsConfiguration configuration, Environment environment) throws Exception {
        logger.trace("run()");

        // Health check
        logger.debug("Adding health check...");
        ContactsConfigurationHealthCheck healthCheck =
                new ContactsConfigurationHealthCheck(configuration);
        environment.healthChecks().register("test", healthCheck);

        // Init database connection
        logger.debug("Connecting to mysql database...");
        DBIFactory factory = new DBIFactory();
        DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        ContactDAO contactDAO = jdbi.onDemand(ContactDAO.class);

        // Init rest API
        logger.debug("Initializing Rest API...");
        environment.jersey().register(new ContactsResource(contactDAO));
    }
}
