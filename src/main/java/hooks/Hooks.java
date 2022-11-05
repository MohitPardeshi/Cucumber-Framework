package hooks;

import io.cucumber.java.Before;
import util.GlobalConfigProperties;
import util.PageObjectGenerator;

import java.io.IOException;

public class Hooks {
    @Before()
    public void setUpHook() throws IOException {
        System.out.println("Inside Before Hook");
        //Load property files
        GlobalConfigProperties.loadProperties();
        PageObjectGenerator.initialize();
    }

}
