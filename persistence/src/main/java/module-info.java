module org.maidavale.music.persistence {
    requires slf4j.api;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.data.neo4j;
    requires com.google.common;
    requires mp3agic;
    requires spring.context;
    requires org.neo4j.ogm.core;
    requires spring.data.commons;
    requires commons.lang3;
    exports org.maidavale.music.persistence.domain;
    exports org.maidavale.music.persistence.repositories;
    exports org.maidavale.music.persistence.services;
}