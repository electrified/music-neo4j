package org.maidavale.music.persistence.repositories;

import org.maidavale.music.persistence.domain.AudioFile;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AudioFileRepository extends Neo4jRepository<AudioFile, Long> {

    @Query("MATCH (n:AudioFile) MATCH (m0:Source) WHERE id(m0) = {sourceId} AND n.relativePath = {relativePath} MATCH (n)-[:`STORED_ON`]->(m0) RETURN n")
    Collection<AudioFile> findBySourceAndRelativePath(@Param("sourceId") Long sourceId, @Param("relativePath") final String relativePath);

    @Query("MATCH (n:`AudioFile`) MATCH (m0:Source) WHERE id(m0) = {sourceId} WITH n RETURN n,[ [ (n)-[r_s1:`STORED_ON`]->(s1:`Source`) | [ r_s1, s1 ] ], [ (n)-[r_f1:`FILE_TO_TRACK`]->(t1:`Track`) | [ r_f1, t1 ] ] ]")
    Collection<AudioFile> findBySource(@Param("sourceId") Long sourceId);
}
