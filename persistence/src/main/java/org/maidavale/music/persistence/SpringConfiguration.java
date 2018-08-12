package org.maidavale.music.persistence;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import  org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;

//public class SpringConfiguration extends Neo4jDataAutoConfiguration {
//    @Bean(destroyMethod = "shutdown")
//    public GraphDatabaseService graphDatabaseService() {
//        return new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(new File("db"))
//                .setConfig("enable_remote_shell", "true").newGraphDatabase();
//    }
//}


@Configuration
@EnableNeo4jRepositories("org.maidavale.music.persistence.repositories")
@EnableTransactionManagement
public class SpringConfiguration {

//    @Bean
//    public SessionFactory sessionFactory() {
//        // with domain entity base package(s)
//        return new SessionFactory(configuration(), "org.maidavale.music.persistence.domain");
//    }
//
//    @Bean
//    public org.neo4j.ogm.config.Configuration configuration() {
//        ConfigurationSource properties = new ClasspathConfigurationSource("ogm.properties");
//        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder(properties).build();
//        return configuration;
//    }
//
//    @Bean
//    public Neo4jTransactionManager transactionManager() {
//        return new Neo4jTransactionManager(sessionFactory());
//    }

}