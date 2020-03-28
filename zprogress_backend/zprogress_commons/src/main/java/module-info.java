module commons {
    requires transitive java.validation;

    exports com.zprogress.domain;
    exports com.zprogress.domain.services;
    exports com.zprogress.domain.repository;

    opens com.zprogress.domain to org.hibernate.validator;
}