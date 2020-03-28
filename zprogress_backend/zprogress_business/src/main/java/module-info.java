module business {
    requires persistence;
    requires commons;

    requires spring.tx;
    requires org.slf4j;
    requires spring.context;
    requires spring.beans;

    exports com.zprogress.service.impl;
    opens com.zprogress.service.impl to spring.core;
}