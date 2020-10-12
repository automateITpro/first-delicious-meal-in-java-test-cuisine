package uk.co.zenitech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import uk.co.zenitech.domain.Artist;
import uk.co.zenitech.domain.Genre;
import uk.co.zenitech.factory.ArtistFactory;
import uk.co.zenitech.repository.ArtistRepository;
import uk.co.zenitech.repository.ExternalArtistRepository;
import uk.co.zenitech.service.ArtistService;

public class SpyTest {
    @Spy
    private final ArtistRepository artistRepository = new ExternalArtistRepository();

    @InjectMocks
    private ArtistService artistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private static final String ARTIST_NAME = "artist";
    private static final Artist ARTIST = ArtistFactory.createArtist(ARTIST_NAME, Genre.jazz);

    @Test
    void givenNotMockedRepositoryMethod_whenSavingArtist_thenThrowException() {
        Assertions.assertThrows(RuntimeException.class, () -> artistService.save(ARTIST));
    }

    @Test
    void givenNotMockedRepositoryMethod_whenFindingArtist_thenThrowException() {
        Assertions.assertThrows(RuntimeException.class, () -> artistService.find(ARTIST_NAME));
    }

    @Test
    void givenMockedRepositoryMethod_whenFindingArtist_thenReturnOk() {
        Mockito.doReturn(ARTIST).when(artistRepository).find(ARTIST_NAME);
//        Mockito.when(artistRepository.find(ARTIST_NAME)).thenReturn(ARTIST); // does not work

        Assertions.assertEquals(ARTIST, artistService.find(ARTIST_NAME));
    }

    @Test
    void givenMockedRepositoryMethod_whenSavingAndFindingArtist_thenReturnOk() {
        Mockito.doNothing().when(artistRepository).save(ARTIST);
        Mockito.doReturn(ARTIST).when(artistRepository).find(ARTIST_NAME);

        artistService.save(ARTIST);

        Assertions.assertEquals(ARTIST, artistService.find(ARTIST_NAME));
        Mockito.verify(artistRepository, Mockito.times(1)).save(ARTIST);
        Mockito.verify(artistRepository, Mockito.times(1)).find(ARTIST_NAME);
    }
}
