package couture;
import couture.DropwizardMySQLHealthCheckResource;
import couture.ApplicationHealthCheck;
import couture.DropApiConfiguration;
import couture.Brand;
import couture.BrandService;
import couture.BrandResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTimeZone;
import java.util.*;
// import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import javax.sql.DataSource;
import org.skife.jdbi.v2.DBI;

public class DropApiApplication extends Application<DropApiConfiguration> {
    private static final String SQL = "sql";
    private static final String DROPWIZARD_MYSQL_SERVICE = "Dropwizard MySQL Service";
    public static void main(String[] args) throws Exception {
        new DropApiApplication().run("server","config.yml");
    }

    @Override
    public String getName() {
        return "DropApi";
    }
	
    @Override
    public void initialize(final Bootstrap<DropApiConfiguration> bootstrap) {
        // TODO: application initialization
		bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
	        super.initialize(bootstrap);

	    DateTimeZone.setDefault(DateTimeZone.UTC);

    }
    @Override
    public void run(final DropApiConfiguration config,
                    final Environment env) {
        // TODO: implement application
		final int defaultSize = config.getDefaultSize();
        final DataSource dataSource =
                config.getDataSourceFactory().build(env.metrics(), SQL);
        DBI dbi = new DBI(dataSource);

        // Register Health Check
        DropwizardMySQLHealthCheckResource healthCheck =
                new DropwizardMySQLHealthCheckResource(dbi.onDemand(BrandService.class));
        env.healthChecks().register(DROPWIZARD_MYSQL_SERVICE, healthCheck);
        // logger.info("Registering RESTful API resources");
        // env.jersey().register(new PingResource());
        env.jersey().register(new BrandResource(dbi.onDemand(BrandService.class)));
    }
	
}
 