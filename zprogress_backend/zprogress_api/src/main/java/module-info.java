module api {
    requires commons;
    requires business;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.web;
    requires spring.beans;
    requires spring.hateoas;
    requires com.fasterxml.classmate; // why ??

    exports com.zprogress;
    exports com.zprogress.controller;

    opens com.zprogress to spring.core;
}