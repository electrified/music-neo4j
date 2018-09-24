package org.maidavale.music.persistence.repositories;

import org.maidavale.music.persistence.domain.AudioFile;
import org.maidavale.music.persistence.domain.Source;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface AudioFileRepository extends Neo4jRepository<AudioFile, Long> {
    AudioFile findByRelativePath(final String path);
    AudioFile findBySourceAndRelativePath(final Source source, final String relativePath);
    Collection<AudioFile> findBySourceEquals(final Source source);
}
