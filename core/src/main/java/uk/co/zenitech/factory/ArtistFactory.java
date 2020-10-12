package uk.co.zenitech.factory;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomUtils;
import uk.co.zenitech.domain.Album;
import uk.co.zenitech.domain.Artist;
import uk.co.zenitech.domain.Genre;

public class ArtistFactory {
    private static final int MAX_ALBUMS_EXCLUSIVE = 4;
    private static final int MAX_SONGS_EXCLUSIVE = 11;
    private static final int MAX_DAYS_IN_SEPT_EXCLUSIVE = 31;

    public Artist delegateCreateArtist(String name, Genre genre) {
        return createArtist(name, genre);
    }

    public static Artist createArtist(String name, Genre genre) {
        var songs = IntStream.rangeClosed(1, RandomUtils.nextInt(1, MAX_SONGS_EXCLUSIVE))
            .mapToObj(i  -> String.format("song_%d", i))
            .collect(Collectors.toList());

        var albums = IntStream.rangeClosed(1, RandomUtils.nextInt(1, MAX_ALBUMS_EXCLUSIVE))
            .mapToObj(i -> new Album(
                String.format("album_%d", i),
                LocalDate.of(2020, 9, RandomUtils.nextInt(1, MAX_DAYS_IN_SEPT_EXCLUSIVE)),
                songs))
            .collect(Collectors.toSet());

        return new Artist(name, genre, albums);
    }
}
