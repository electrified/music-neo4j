package org.maidavale.music.persistence.services;

import org.maidavale.music.persistence.domain.AudioFile;
import org.maidavale.music.persistence.domain.Source;
import org.maidavale.music.persistence.domain.Track;
import org.maidavale.music.persistence.repositories.AudioFileRepository;
import org.maidavale.music.persistence.repositories.SourceRepository;
import org.maidavale.music.persistence.repositories.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Collection;

@Service
public class AudioFileService {

    private final static Logger LOG = LoggerFactory.getLogger(AudioFileService.class);

    private final AudioFileRepository audioFileRepository;
    private final SourceRepository sourceRepository;
    private final TrackRepository trackRepository;

    public AudioFileService(AudioFileRepository audioFileRepository, SourceRepository sourceRepository, TrackRepository trackRepository) {
        this.audioFileRepository = audioFileRepository;
        this.sourceRepository = sourceRepository;
        this.trackRepository = trackRepository;
    }

    private void addFile(final Source documentRoot, final String audioFile) {
        var existingFile = audioFileRepository.findBySourceAndRelativePath(documentRoot, audioFile);

        if (existingFile == null) {
            final var audioFileNode = new AudioFile(documentRoot, audioFile);
            LOG.info("adding {}", audioFile);
            audioFileRepository.save(audioFileNode);
//            documentRoot.addAudioFile(audioFileNode);
        } else {
            LOG.info("Already in database {}", audioFile);
        }
    }

    void updateFile(final AudioFile audioFile) {
        audioFileRepository.save(audioFile);
        if (audioFile.getTrack() != null) {
            trackRepository.save(audioFile.getTrack());
        }
    }

    public void deleteSource(final String path) {
        var source = sourceRepository.findByPath(path);
        if (source != null) {
            sourceRepository.delete(source);
        }
    }

    public void deleteAudioFiles() {
        audioFileRepository.deleteAll();
        trackRepository.deleteAll();
    }

    Iterable<AudioFile> getAudioFiles() {
        return audioFileRepository.findAll();
    }

    public Collection<Track> search(String searchCriteria) {
        return trackRepository.findByTitleLike(searchCriteria);
    }

    private String calculateRelativePath(Path documentRoot, Path audioFile) {
        return documentRoot.relativize(audioFile).toString();
    }

    public void importAudio(final String pathToScan) {
        final var documentRootPath = Paths.get(pathToScan);

        final PathMatcher filter = documentRootPath.getFileSystem().getPathMatcher("glob:**.{mp3,wav,flac,ogg}");

        var src = sourceRepository.findByPath(documentRootPath.toString());

        if (src == null) {
            src = sourceRepository.save(new Source(documentRootPath.toString()));
            LOG.info("Saved source {}", src);
        }

        try {
            Source finalSrc = src;
            Files.walk(documentRootPath, FileVisitOption.FOLLOW_LINKS)
//                    .parallel()
                    .filter(Files::isRegularFile)
                    .filter(filter::matches)
                    .forEach(f -> addFile(finalSrc, calculateRelativePath(documentRootPath, f)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}