module api {
    requires commons;

    requires transitive spring.boot.autoconfigure;
    requires transitive spring.boot;
    requires transitive spring.hateoas;
    requires transitive spring.web; // in the app module there is the security authentication web service controller
    requires transitive spring.context;
    requires transitive spring.beans;


    requires org.apache.tomcat.embed.core;

    exports com.zprogress.controller;
    opens com.zprogress.controller to spring.core;
}