package org.maidavale.music.persistence.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Source {
    @Id
    @GeneratedValue
    private Long id;

    private String path;

    public Source(String path) {
        this.path = path;
    }

    public Source() {
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
