package org.maidavale.music.persistence.dto;

import org.maidavale.music.persistence.domain.Artist;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class ArtistWithTrackCount {
    public Artist artist;
    public int trackCount;
}
