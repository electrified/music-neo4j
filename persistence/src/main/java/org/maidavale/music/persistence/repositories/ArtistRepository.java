package org.maidavale.music.persistence.repositories;

import org.maidavale.music.persistence.domain.Artist;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ArtistRepository extends Neo4jRepository<Artist, Long> {

    Collection<Artist> findByNameLike(@Param("name") final String name);

    Artist findByName(@Param("name") final String name);
}
