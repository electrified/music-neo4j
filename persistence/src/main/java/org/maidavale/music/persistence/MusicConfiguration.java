package org.maidavale.music.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories("org.maidavale.music.persistence.repositories")
public class MusicConfiguration {

}
