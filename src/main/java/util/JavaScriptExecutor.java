package util;

import driverFactory.DriverUtil;

public class JavaScriptExecutor {

    public static JavaScriptExecutor getExecutor(){
        return ((JavaScriptExecutor)DriverUtil.getDriver());
    }

}
