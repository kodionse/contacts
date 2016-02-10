package se.kodion.contacts.health;

import com.codahale.metrics.health.HealthCheck;
import se.kodion.contacts.ContactsConfiguration;

public class ContactsConfigurationHealthCheck extends HealthCheck {
    private final ContactsConfiguration configuration;

    public ContactsConfigurationHealthCheck(ContactsConfiguration conf) {
        this.configuration = conf;
    }

    @Override
    protected Result check() throws Exception {
        if (configuration.getDataSourceFactory() != null) {
            return Result.unhealthy("No data source configured");
        }
        return Result.healthy();
    }
}