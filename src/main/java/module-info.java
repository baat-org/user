module org.baat.user {
    requires org.baat.core;

    requires spring.core;
    requires spring.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.webmvc;

    requires org.slf4j;

    requires spring.data.jpa;
    requires java.persistence;
}
