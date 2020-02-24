package org.maidavale.music.web.controllers;

import org.maidavale.music.persistence.services.AudioFileService;
import org.maidavale.music.persistence.services.MetadataService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import static java.net.URLConnection.guessContentTypeFromName;
import static org.apache.commons.io.IOUtils.copy;

//@PreAuthorize("principal.claims['groups'].contains('listeners')")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/audio")
public class AudioController {
    private final AudioFileService audioFileService;

    public AudioController(final AudioFileService audioFileService) {
        this.audioFileService = audioFileService;
    }

    @RequestMapping(value ="/play/{id}") //, produces = "audio/*"
    public void play(@PathVariable(value="id") final Long id, final HttpServletResponse response) throws IOException {
        var audioFile = audioFileService.getFileByTrackId(id);

        if (audioFile.isPresent()) {
            var file = audioFile.get();
            final var path = Paths.get(file.getSource().getPath() + "/" + file.getRelativePath());

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

    }
}
