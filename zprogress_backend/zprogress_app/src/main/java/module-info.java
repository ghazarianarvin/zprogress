module app {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires java.sql;
    requires jdk.unsupported;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.classmate;

    requires commons;
    requires persistence;
    requires business;
    requires api;

    exports com.zprogress;
    exports com.zprogress.config;
    opens com.zprogress to spring.core;
    opens com.zprogress.config to spring.core;
}