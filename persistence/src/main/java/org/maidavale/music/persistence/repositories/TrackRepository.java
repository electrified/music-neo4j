package org.maidavale.music.persistence.repositories;

import org.maidavale.music.persistence.domain.Track;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface TrackRepository extends Neo4jRepository<Track, Long> {
    @Query("MATCH (n:`Track`) WHERE LOWER(n.title) =~ LOWER('.*' + { `title` } + '.*') " +
            "WITH n RETURN n,[ [ (n)-[r_b1:`BY`]->(a1:`Artist`) | [ r_b1, a1 ] ], [ (n)-[r_a1:`APPEARS_ON`]->(r1:`Release`) | [ r_a1, r1 ] ], [ (a1:`AudioFile`)-[r_f1:`FILE_TO_TRACK`]->(n) | [ r_f1, a1 ] ] ]")
    Collection<Track> findByTitleLike(@Param("title") final String title);

    @Query("MATCH (n:Track)-[:BY]->(a:Artist) where id(a) = {id} RETURN n, [ [ (n)-[r_b1:`BY`]->(a1:`Artist`) | [ r_b1, a1 ] ], [ (n)-[r_a1:`APPEARS_ON`]->(r1:`Release`) | [ r_a1, r1 ] ], [ (a1:`AudioFile`)-[r_f1:`FILE_TO_TRACK`]->(n) | [ r_f1, a1 ] ] ]")
    Collection<Track> findByArtistId(@Param("id") final int id);
}
