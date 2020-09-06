package couture;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotNull;
import java.util.List;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
public class DropApiConfiguration extends Configuration{
    // TODO: implement service configuration
	@NotNull 
	private int defaultSize;

    @JsonProperty
    public String apiUsername;

	@JsonProperty
	private List<String> allowedGrantTypes;

    @JsonProperty
    public String apiPassword;

     @Valid
    @NotNull
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

	
    @JsonProperty
    private String bearerRealm;
//    private final boolean webLoggerEnabled;
    private static final String DATABASE = "database";
    
    @JsonProperty(DATABASE)
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty(DATABASE)
    public void setDataSourceFactory(final DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }
 
    public int getDefaultSize() {
        return defaultSize;
    }
	public String getApiUsername() {
        return apiUsername;
    }

    public void setApiUsername(String apiUsername) {
        this.apiUsername = apiUsername;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }
	public void setBearerRealm(String bearerRealm) {
        this.bearerRealm = bearerRealm;
    }
	public String getBearerRealm(){
		return this.bearerRealm;	
	}
	public List<String> getAllowedGrantTypes(){
		return allowedGrantTypes;
	}


//    @Override
//    public boolean isWebLoggerEnabled() {
//        return this.webLoggerEnabled;
//    }
}
