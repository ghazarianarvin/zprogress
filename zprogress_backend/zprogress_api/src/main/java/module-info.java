module api {
    requires commons;

    requires transitive spring.boot.autoconfigure;
    requires transitive spring.boot;
    requires transitive spring.hateoas;
    requires transitive spring.web; // in the app module there is the security authentication web service controller
    requires transitive spring.context;
    requires transitive spring.beans;

    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive com.fasterxml.classmate;


    requires org.apache.tomcat.embed.core;
    requires org.slf4j;

    exports com.zprogress.controller.goal;
    exports com.zprogress.controller.step;
    exports com.zprogress.controller.evaluation;
    exports com.zprogress.controller;

    opens com.zprogress.controller.step to spring.core;
    opens com.zprogress.controller.goal to spring.core;
    opens com.zprogress.controller.evaluation to spring.core;
    opens com.zprogress.controller to spring.core;
}