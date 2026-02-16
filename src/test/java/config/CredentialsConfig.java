package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "classpath:config/credentials.properties"})
public interface CredentialsConfig extends Config {

    @Config.Key("email")
    String getEmail();

    @Config.Key("password")
    String getPassword();

    @Config.Key("token")
    String getToken();
}
