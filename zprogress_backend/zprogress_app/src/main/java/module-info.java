module app {
    requires commons;
    requires persistence;
    requires business;
    requires api;

    requires spring.security.core;
    requires spring.security.config;
    requires spring.security.web;

    requires java.sql;
    requires jdk.unsupported;
    requires org.apache.tomcat.embed.core;
    requires jjwt;
    requires java.xml.bind;
    requires org.slf4j;
    requires org.aspectj.weaver;

    exports com.zprogress;
    exports com.zprogress.aspect;
    exports com.zprogress.config;
    exports com.zprogress.security;
    exports com.zprogress.security.filter;

    opens com.zprogress.aspect to spring.core;
    opens com.zprogress to spring.core;
    opens com.zprogress.config to spring.core;
    opens com.zprogress.security to spring.core;
    opens com.zprogress.security.filter to spring.core;
}