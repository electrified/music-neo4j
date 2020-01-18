package org.maidavale.music.persistence.repositories;

import org.maidavale.music.persistence.domain.Artist;
import org.maidavale.music.persistence.dto.ArtistWithTrackCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Map;

public interface ArtistRepository extends Neo4jRepository<Artist, Long> {

    Collection<Artist> findByNameLike(@Param("name") final String name);

    Collection<Artist> findByName(@Param("name") final String name);

    @Query("MATCH (t:Track)-[:BY]->(n:Artist) return n as artist, count(t) as trackCount order by count (t) desc")
    Collection<ArtistWithTrackCount> findMostPopularArtists();
}
