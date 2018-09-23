package org.maidavale.music.web;

import org.apache.commons.io.IOUtils;
import org.maidavale.music.persistence.services.AudioFileService;
import org.maidavale.music.persistence.services.MetadataService;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PlaybackController {
    private final AudioFileService audioFileService;
    private final MetadataService metadataService;

    public PlaybackController(AudioFileService audioFileService, MetadataService metadataService) {
        this.audioFileService = audioFileService;
        this.metadataService = metadataService;
    }

    @RequestMapping(value ="/play/{id}", produces = "audio/*")
    public void search(@PathVariable(value="id") final Long id, HttpServletResponse response) throws IOException {
        var audioFile =  audioFileService.getFileById(id);

        final var path = Paths.get(audioFile.get().getSource().getPath() + "/" + audioFile.get().getRelativePath());

        String mimeType = URLConnection.guessContentTypeFromName(path.toString());
        if (mimeType == null) {
            //unknown mimetype so set the mimetype to application/octet-stream
            mimeType = "application/octet-stream";
        }


        InputStream inputStream = new BufferedInputStream(new FileInputStream(path.toString()));

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"%d\"", id));

//        response.setContentLength((int) path.length());

        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
