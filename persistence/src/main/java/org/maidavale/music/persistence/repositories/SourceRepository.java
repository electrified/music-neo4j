package org.maidavale.music.persistence.repositories;

import org.maidavale.music.persistence.domain.AudioFile;
import org.maidavale.music.persistence.domain.Source;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface SourceRepository extends Neo4jRepository<Source, Long> {

    Source findByPath(final String path);

    // TODO: This doesn't work if the source has no files
    @Query("MATCH (s:Source)-[r:STORED_ON]-(f:`AudioFile`) " +
            "MATCH (f:`AudioFile`)-[tr]->(Track) " +
            "WHERE id(s) = {sourceId} " +
            "DELETE f, r, s, tr")
    void deleteSource(@Param("sourceId") Long sourceId);
}
