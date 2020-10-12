package uk.co.zenitech.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Album {
    private final String name;
    private final LocalDate releaseDate;
    private final List<String> songs;

    public Album(String name, LocalDate releaseDate, List<String> songs) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<String> getSongs() {
        return songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Album album = (Album) o;
        return Objects.equals(name, album.name) && Objects.equals(releaseDate, album.releaseDate)
            && Objects.equals(songs, album.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseDate, songs);
    }

    @Override
    public String toString() {
        return "Album{" + "name='" + name + '\'' + ", releaseDate=" + releaseDate + ", songs="
            + songs + '}';
    }
}