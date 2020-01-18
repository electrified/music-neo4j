package org.maidavale.music.persistence.repositories;

import org.maidavale.music.persistence.domain.Release;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ReleaseRepository extends Neo4jRepository<Release, Long> {

    Collection<Release> findByNameLike(@Param("name") final String name);

    Collection<Release> findByName(@Param("name") final String name);
}
