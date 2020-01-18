package org.maidavale.music.web.controllers;

import org.maidavale.music.persistence.domain.Track;
import org.maidavale.music.persistence.dto.ArtistWithTrackCount;
import org.maidavale.music.persistence.services.AudioFileService;
import org.maidavale.music.persistence.services.MetadataService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;

import static java.net.URLConnection.guessContentTypeFromName;
import static org.apache.commons.io.IOUtils.copy;

@PreAuthorize("principal.claims['groups'].contains('listeners')")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/playback")
public class PlaybackController {

    private final MetadataService metadataService;

    public PlaybackController(final MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @RequestMapping("/search")
    public Collection<Track> search(@RequestParam("query") final String searchCriteria) {
        return metadataService.search(searchCriteria);
    }

    @RequestMapping("/artists")
    public Collection<ArtistWithTrackCount> getArtists() {
        return metadataService.getArtists();
    }

    @RequestMapping("/artists/{artistId}/tracks")
    public Collection<Track> getTracksByArtist(@PathVariable("artistId") final int artistId) {
        return metadataService.getTracksByArtist(artistId);
    }
}
