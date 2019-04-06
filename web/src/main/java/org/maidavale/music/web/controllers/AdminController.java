package org.maidavale.music.web.controllers;

import org.apache.commons.lang3.NotImplementedException;
import org.maidavale.music.persistence.domain.AudioFile;
import org.maidavale.music.persistence.domain.Source;
import org.maidavale.music.persistence.services.AudioFileService;
import org.maidavale.music.persistence.services.MetadataService;
import org.maidavale.music.web.entities.SuccessfulResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@PreAuthorize("hasRole('ADMIN')")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AudioFileService audioFileService;
    private final MetadataService metadataService;

    public AdminController(final AudioFileService audioFileService, final MetadataService metadataService) {
        this.audioFileService = audioFileService;
        this.metadataService = metadataService;
    }

    @RequestMapping("/user")
    public Principal user(final Principal user) {
        return user;
    }

    @RequestMapping("/sources")
    public Iterable<Source> listSources() {
        return audioFileService.getSources();
    }

    @PostMapping("sources")
    public ResponseEntity addSource(@RequestBody final Source source) {
        return new ResponseEntity(audioFileService.addSource(source.getPath()), HttpStatus.OK);
    }

    @DeleteMapping("sources/{sourceId}")
    public ResponseEntity deleteSource(@PathVariable("sourceId") final Long sourceId) {
        throw new NotImplementedException("todo");
    }

    @PostMapping("sources/{sourceId}/scan")
    public ResponseEntity scanFiles(@PathVariable("sourceId") final Long sourceId) {
        audioFileService.importAudio(sourceId);
        return new ResponseEntity(sourceId, HttpStatus.OK);
    }

    @PostMapping("sources/{sourceId}/metadata")
    public ResponseEntity updateMetadata(@PathVariable("sourceId") final Long sourceId) {
        metadataService.populateMetadata(sourceId);
        return new ResponseEntity(new SuccessfulResponse(sourceId.toString()), HttpStatus.OK);
    }

    @GetMapping("sources/{sourceId}/files")
    public Iterable<AudioFile> getFiles(@PathVariable("sourceId") final Long sourceId) {
        return audioFileService.getAudioFilesBySource(sourceId);
    }

    @PostMapping("delete")
    public void delete() {
        audioFileService.deleteAudioFiles();
    }

    @PostMapping("deletemeta")
    public void deleteMetadata() {
        metadataService.deleteAllMetadata();
    }
}
