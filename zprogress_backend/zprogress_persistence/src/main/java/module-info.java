module persistence {
    requires commons;
    requires spring.context;
    requires spring.boot;
    requires java.sql;
    requires spring.beans;
    requires spring.jdbc;

    exports com.zprogress.reporsitory;
    exports com.zprogress.config.database;

    opens com.zprogress.reporsitory to spring.core;
    opens com.zprogress.config.database to spring.core;
}