module app {
    requires commons;
    requires persistence;
    requires business;
    requires api;

    requires spring.security.core;
    requires spring.security.config;

    requires java.sql;
    requires jdk.unsupported;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.classmate;
    requires org.apache.tomcat.embed.core;
    requires jjwt;
    requires java.xml.bind;
    requires spring.security.web;

    exports com.zprogress;
    exports com.zprogress.config;
    exports com.zprogress.security;
    opens com.zprogress to spring.core;
    opens com.zprogress.config to spring.core;
    opens com.zprogress.security to spring.core;
}