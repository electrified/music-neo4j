package org.maidavale.music.persistence.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Release {
    @Id
    @GeneratedValue
    private Long id;

    @Index(unique=true)
    private String name;

    @Relationship(type = "APPEARS_ON")
    private Set<Track> tracks;

    public Release() {
    }

    public Release(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
