package org.maidavale.music.web.controllers;

import org.maidavale.music.persistence.domain.Track;
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

//@PreAuthorize("principal.claims['groups'].contains('listeners')")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/playback")
public class PlaybackController {
    private final AudioFileService audioFileService;
    private final MetadataService metadataService;

    public PlaybackController(final AudioFileService audioFileService, final MetadataService metadataService) {
        this.audioFileService = audioFileService;
        this.metadataService = metadataService;
    }

    @RequestMapping(value ="/play/{id}", produces = "audio/*")
    public void play(@PathVariable(value="id") final Long id, final HttpServletResponse response) throws IOException {
        var audioFile =  audioFileService.getFileById(id);

        final var path = Paths.get(audioFile.get().getSource().getPath() + "/" + audioFile.get().getRelativePath());

        String mimeType = guessContentTypeFromName(path.toString());
        if (mimeType == null) {
            //unknown mimetype so set the mimetype to application/octet-stream
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"%d\"", id));

//        response.setContentLength((int) path.length());

        copy(new BufferedInputStream(new FileInputStream(path.toString())), response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping("/search")
    public Collection<Track> search(@RequestParam("query") final String searchCriteria) {
        return metadataService.search(searchCriteria);
    }
}
