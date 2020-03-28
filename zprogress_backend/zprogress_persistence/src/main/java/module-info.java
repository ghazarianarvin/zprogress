module persistence {
    requires commons;

    requires spring.context;
    requires spring.beans;
    requires spring.boot;
    requires java.sql;
    requires spring.jdbc;

    exports com.zprogress.reporsitory;

}