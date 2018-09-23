package org.maidavale.music.web;

import org.maidavale.music.persistence.domain.Track;
import org.maidavale.music.persistence.services.AudioFileService;
import org.maidavale.music.persistence.services.MetadataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SearchController {
    private final AudioFileService audioFileService;
    private final MetadataService metadataService;

    public SearchController(AudioFileService audioFileService, MetadataService metadataService) {
        this.audioFileService = audioFileService;
        this.metadataService = metadataService;
    }

    @RequestMapping("/search")
    public Collection<Track> search( @RequestParam(value="query") final String query) {
        return audioFileService.search(query);
    }
}
