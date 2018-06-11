package myprojects.automation.assignment4.utils;


import org.openqa.selenium.remote.BrowserType;

/**
 * Help class to get passed parameters from environment for further usage in the automation project
 */
public class Properties {
    private static final String DEFAULT_BASE_URL = "https://dev.fegllc.com";
    private static final String DEFAULT_BASE_ADMIN_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
    private static final String DEFAULT_ADMIN_EMAIL = "greg@element5digital.com";
    private static final String DEFAULT_ADMIN_PWD = "password";
    //private static final String DEFAULT_CATEGORY_NAME = "Шаритоновна";
   // private static final String DEFAULT_BROWSER = BrowserType.FIREFOX;
    private static final String DEFAULT_BROWSER = BrowserType.CHROME;

    /**
     *
     * @return Website frontend.
     */
    public static String getBaseUrl() {
        return System.getProperty(EnvironmentVariables.BASE_URL.toString(), DEFAULT_BASE_URL);
    }

    /**
     *
     * @return Website backend (ULR of the Admin Panel.)
     */
    public static String getBaseAdminUrl() {
        return System.getProperty(EnvironmentVariables.BASE_ADMIN_URL.toString(), DEFAULT_BASE_ADMIN_URL);
    }

    /**
     *
     * @return The name of the browser that need to be used for execution.
     */
    public static String getBrowser() {
        return System.getProperty(EnvironmentVariables.BROWSER.toString(), DEFAULT_BROWSER);
    }

    /**
     *
     * @return The admin email that need to be used for execution.
     */
    public static String getAdminEmail() {
        return System.getProperty(EnvironmentVariables.BASE_ADMIN_EMAIL.toString(), DEFAULT_ADMIN_EMAIL);
    }

    /**
     *
     * @return The admin pwd that need to be used for execution.
     */
    public static String getAdminPWD() {
        return System.getProperty(EnvironmentVariables.BASE_ADMIN_PWD.toString(), DEFAULT_ADMIN_PWD);
    }

    /**
     *
     * @return The product category name that need to be used for execution.
     *//*
    public static String getCategoryName() {
        return System.getProperty(EnvironmentVariables.BASE_CATEGORY_NAME.toString(), DEFAULT_CATEGORY_NAME);
    }*/


}

/**
 * All parameters that are passed to automation project
 */
enum EnvironmentVariables {
    BASE_URL("env.url"),
    BASE_ADMIN_URL("env.admin.url"),
    BASE_ADMIN_EMAIL("env.admin.email"),
    BASE_ADMIN_PWD("env.admin.pwd"),
    //BASE_CATEGORY_NAME("env.category"),
    BROWSER("browser");

    private String value;
    EnvironmentVariables(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}