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

    @PostMapping("scan")
    public ResponseEntity scanFiles(@RequestParam("path") String pathToScan) {
        audioFileService.importAudio(pathToScan);
        return new ResponseEntity(pathToScan, HttpStatus.OK);
    }

    @PostMapping("delete")
    public void delete() {
        audioFileService.deleteAudioFiles();
    }

    @PostMapping("deletemeta")
    public void deleteMetadata() {
        metadataService.deleteMetadata();
    }

    @PostMapping("meta")
    public void updateMetadata() {
        metadataService.displayMetadata();
    }

    @RequestMapping("/sources")
    public Iterable<Source> listSources() {
        return audioFileService.getSources();
    }
}
