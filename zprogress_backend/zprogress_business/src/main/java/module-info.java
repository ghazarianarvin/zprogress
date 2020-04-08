module business {
    requires persistence;
    requires commons;

    requires spring.tx; // transactions are part of the business layer
    requires transitive spring.core;
    requires org.slf4j;

    exports com.zprogress.service.impl;
    opens com.zprogress.service.impl to spring.core;
}