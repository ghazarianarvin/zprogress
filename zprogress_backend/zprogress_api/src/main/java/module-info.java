module api {
    requires commons;

    requires transitive spring.boot.autoconfigure;
    requires transitive spring.boot;
    requires spring.web;
    requires spring.beans;
    requires spring.hateoas;

    exports com.zprogress.controller;
    opens com.zprogress.controller to spring.core;
}