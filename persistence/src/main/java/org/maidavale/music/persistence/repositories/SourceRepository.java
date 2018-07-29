package org.maidavale.music.persistence.repositories;

import org.maidavale.music.persistence.domain.Source;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SourceRepository extends Neo4jRepository<Source, Long> {

    Source findByPath(final String path);
}
