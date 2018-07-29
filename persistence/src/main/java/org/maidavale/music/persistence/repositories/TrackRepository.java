package org.maidavale.music.persistence.repositories;

import org.maidavale.music.persistence.domain.Track;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface TrackRepository extends Neo4jRepository<Track, Long> {
    Collection<Track> findByTitleLike(@Param("title") final String title);
}
