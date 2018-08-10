module org.maidavale.music.web {
    requires slf4j.api;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.data.neo4j;
    requires com.google.common;
    requires mp3agic;
    requires spring.context;
    requires org.neo4j.ogm.core;
    requires spring.web;
    requires spring.webmvc;
    requires spring.data.commons;
    requires commons.lang3;
    requires org.maidavale.music.persistence;
    requires org.apache.commons.io;
    requires tomcat.embed.core;
    requires spring.security.config;
    requires spring.beans;
}