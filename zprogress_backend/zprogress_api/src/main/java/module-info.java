module api {
    requires commons;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.web;
    requires spring.beans;
    requires spring.hateoas;

    exports com.zprogress.controller;
    exports com.zprogress.dto;

}