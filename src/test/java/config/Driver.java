package config;

import org.aeonbits.owner.ConfigFactory;

public class Driver {

    public static DriverConfig config = ConfigFactory.create(DriverConfig.class, System.getProperties());
}
