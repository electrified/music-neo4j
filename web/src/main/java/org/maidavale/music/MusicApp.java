package org.maidavale.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
public class MusicApp {
    public static void main(String... args ) {
        SpringApplication.run(MusicApp.class, args);
    }
}
