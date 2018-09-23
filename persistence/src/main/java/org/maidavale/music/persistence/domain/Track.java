package org.maidavale.music.persistence.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Track {
    @Id
    @GeneratedValue
    private Long id;
    private String track;
    @Relationship(type = "BY")
    private Set<Artist> artist = new HashSet<>();
    private String title;
    @Relationship(type = "APPEARS_ON")
    private Set<Release> release = new HashSet<>();
    private String year;
    private String genre;
    private String comment;
    private Long length;

    @Relationship(type = "FILE_TO_TRACK")
    private Set<AudioFile> files;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public Set<Artist> getArtist() {
        return artist;
    }

    public void setArtist(Set<Artist> artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Release> getRelease() {
        return release;
    }

    public void setRelease(Set<Release> release) {
        this.release = release;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<AudioFile> getFiles() {
        return files;
    }

    public void setFiles(Set<AudioFile> files) {
        this.files = files;
    }

    public void addRelease(Release release) {
        this.release.add(release);
    }

    public void addArtist(Artist artist) {
        this.artist.add(artist);
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
