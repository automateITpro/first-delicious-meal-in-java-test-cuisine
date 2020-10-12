package uk.co.zenitech.domain;

import java.util.Objects;
import java.util.Set;

public class Artist {
    private final String name;
    private final Genre genre;
    private final Set<Album> albums;

    public Artist(String name, Genre genre, Set<Album> albums) {
        this.name = name;
        this.genre = genre;
        this.albums = albums;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name) && genre == artist.genre && Objects
            .equals(albums, artist.albums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, albums);
    }

    @Override
    public String toString() {
        return "Artist{" + "name='" + name + '\'' + ", musicGenre=" + genre + ", albums="
            + albums + '}';
    }
}
