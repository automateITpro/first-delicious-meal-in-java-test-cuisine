package uk.co.zenitech;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import uk.co.zenitech.domain.Artist;
import uk.co.zenitech.domain.Genre;
import uk.co.zenitech.factory.ArtistFactory;
import uk.co.zenitech.repository.ArtistRepository;
import uk.co.zenitech.service.ArtistService;

public class HamcrestTest {
    private static final String ARTIST_NAME = "artist_name";
    private static final Artist ARTIST = ArtistFactory.createArtist(ARTIST_NAME, Genre.jazz);
    private static final LocalDate PREV_MONTH_DATE = LocalDate.of(2020, 8, 1);

    private static final ArtistRepository artistRepository = new LocalArtistRepository();
    private static final ArtistService artistService = new ArtistService(artistRepository);

    @BeforeAll
    static void setUp() {
        artistService.save(ARTIST);
    }

    @Test
    void givenArtistNotExists_whenSavingArtist_thenAllAlbumsHaveSong1_withoutHamcrest() {
        var savedArtist = artistService.find(ARTIST_NAME);

        Assertions.assertFalse(savedArtist.getAlbums().isEmpty());

        savedArtist.getAlbums().forEach(album -> {
            Assertions.assertTrue(album.getReleaseDate().isAfter(PREV_MONTH_DATE));

            var songs = album.getSongs();
            Assertions.assertFalse(songs.isEmpty());
            Assertions.assertTrue(songs.contains("song_1"));

            songs.forEach(song -> {
                Assertions.assertTrue(song.startsWith("song"));
            });
        });
    }

    @Test
    void givenArtistNotExists_whenSavingArtist_thenAllAlbumsHaveSong1_withHamcrest() {
        var savedArtist = artistService.find(ARTIST_NAME);

        assertThat(savedArtist.getAlbums(), is(not(empty())));

        savedArtist.getAlbums().forEach(album -> {
            assertThat(album.getReleaseDate(), greaterThan(PREV_MONTH_DATE));

            var songs = album.getSongs();
            assertThat(songs, is(not(empty())));
            assertThat(songs, hasItem("song_1")); // can check multiple items
            assertThat(songs, everyItem(startsWith("song")));
        });
    }
}
