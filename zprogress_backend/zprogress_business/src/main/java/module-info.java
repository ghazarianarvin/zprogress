module business {
    requires commons;
    requires persistence;
    requires spring.tx;
    requires spring.context;
    requires org.slf4j;
    requires spring.beans;

    exports com.zprogress.service;
    exports com.zprogress.service.impl;

    opens com.zprogress.service.impl to spring.core;
}