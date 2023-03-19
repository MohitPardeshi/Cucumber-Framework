package util;

import driverFactory.DriverUtil;

public class Navigation {
    public static void navigateBack(){
        DriverUtil.getDriver().navigate().back();
    }

    public static void navigateForward(){
        DriverUtil.getDriver().navigate().forward();
    }

    public static void navigateRefresh(){
        DriverUtil.getDriver().navigate().refresh();
    }

    public static void navigateTo(String url){
        DriverUtil.getDriver().navigate().to(url);
    }
}
