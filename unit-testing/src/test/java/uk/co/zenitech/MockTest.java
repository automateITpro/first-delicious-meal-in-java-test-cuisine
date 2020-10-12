package uk.co.zenitech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import uk.co.zenitech.domain.Artist;
import uk.co.zenitech.domain.Genre;
import uk.co.zenitech.factory.ArtistFactory;
import uk.co.zenitech.repository.ArtistRepository;
import uk.co.zenitech.service.ArtistService;

public class MockTest {
    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistService artistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private static final String ARTIST_NAME = "artist_name";
    private static final Artist ARTIST = ArtistFactory.createArtist(ARTIST_NAME, Genre.jazz);

    @Test
    void givenDummyRepository_whenSavingAndFindingArtist_thenNull() {
        artistService.save(ARTIST);

        Assertions.assertNull(artistService.find(ARTIST_NAME));
    }

    @Test
    void givenMockedRepositoryMethod_whenFindingArtist_thenReturnOk() {
//        Mockito.when(artistRepository.find(ARTIST_NAME)).thenReturn(ARTIST);
        Mockito.when(artistRepository.find(Mockito.any())).thenReturn(ARTIST);

        // redundant operation only for presentation
//          artistService.save(ARTIST);

        Assertions.assertEquals(ARTIST, artistService.find(ARTIST_NAME));
    }

    @Test
    void givenMockedRepositoryMethod_whenSavingAndFindingArtist_thenReturnOk() {
        Mockito.when(artistRepository.find(Mockito.any())).thenReturn(ARTIST);

        artistService.save(ARTIST);

        Assertions.assertEquals(ARTIST, artistService.find(ARTIST_NAME));
        Mockito.verify(artistRepository, Mockito.times(1)).save(ARTIST);
        Mockito.verify(artistRepository, Mockito.times(1)).find(ARTIST_NAME);
    }
}
