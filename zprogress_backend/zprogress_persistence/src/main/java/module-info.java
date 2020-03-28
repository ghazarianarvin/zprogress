module persistence {
    requires commons;

    requires spring.context;
    requires spring.beans;
    requires spring.boot;
    requires java.sql;
    requires spring.jdbc;
    requires spring.tx;

    exports com.zprogress.reporsitory;

}