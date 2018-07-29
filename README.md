# Music

This is a Java 10/Gradle/Spring Boot/Spring Data project built to improve my understanding of neo4j

## What it does

- Given a filesystem path it indexes all the available audio files (*.mp3, *.flac, *.wav, *.ogg)
- The ID3/flac metadata is then parsed and also indexed into neo4j
- Provides REST endpoints for querying the data

This is a work in progress so there are hardcoded paths in the code which you will need to change

##TODO

- Add real web interface with search ability
- Remove hardcoded paths/ dummy credentials
- Parse FLAC metadata
- Improve indexing speed
- Get working on the module path rather than the classpath