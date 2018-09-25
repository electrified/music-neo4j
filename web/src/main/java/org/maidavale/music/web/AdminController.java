package org.maidavale.music.web;

import org.maidavale.music.persistence.domain.Source;
import org.maidavale.music.persistence.services.AudioFileService;
import org.maidavale.music.persistence.services.MetadataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@PreAuthorize("hasRole('ADMIN')")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AudioFileService audioFileService;
    private final MetadataService metadataService;

    public AdminController(AudioFileService audioFileService, MetadataService metadataService) {
        this.audioFileService = audioFileService;
        this.metadataService = metadataService;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/sources")
    public Iterable<Source> listSources() {
        return audioFileService.getSources();
    }

    @PostMapping("sources")
    public ResponseEntity addSource(@RequestBody Source source) {
        return new ResponseEntity(audioFileService.addSource(source.getPath()), HttpStatus.OK);
    }

    @PostMapping("sources/{sourceId}/scan")
    public ResponseEntity scanFiles(@PathVariable("sourceId") Long sourceId) {
        audioFileService.importAudio(sourceId);
        return new ResponseEntity(sourceId, HttpStatus.OK);
    }

    @PostMapping("sources/{sourceId}/metadata")
    public void updateMetadata(@PathVariable("sourceId") Long sourceId) {
        metadataService.populateMetadata(sourceId);
    }

    @PostMapping("delete")
    public void delete() {
        audioFileService.deleteAudioFiles();
    }

    @PostMapping("deletemeta")
    public void deleteMetadata() {
        metadataService.deleteMetadata();
    }
}
