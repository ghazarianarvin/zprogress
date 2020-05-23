module persistence {
    requires commons;

    requires spring.context;
    requires spring.boot;
    requires java.sql;
    requires transitive spring.jdbc;
    requires spring.tx;

    exports com.zprogress.reporsitory;
    opens com.zprogress.reporsitory to spring.core;
}