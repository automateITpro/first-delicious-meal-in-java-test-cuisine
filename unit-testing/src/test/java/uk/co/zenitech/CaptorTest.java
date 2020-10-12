package uk.co.zenitech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import uk.co.zenitech.domain.Artist;
import uk.co.zenitech.domain.Genre;
import uk.co.zenitech.factory.ArtistFactory;

public class CaptorTest {
    @Spy
    private final ArtistFactory artistFactorySpy = new ArtistFactory();
    @Captor
    private ArgumentCaptor<String> artistNameCaptor;
    @Captor
    private ArgumentCaptor<Genre> genreNameCaptor;

    private static final Artist ARTIST = ArtistFactory.createArtist("random artist name", Genre.jazz);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenCreatingArtist_thenCaptureArtistName() {
        Mockito.when(artistFactorySpy.delegateCreateArtist(Mockito.anyString(), Mockito.any()))
            .thenReturn(ARTIST);

        artistFactorySpy.delegateCreateArtist("passed name", Genre.dubstep);

        Mockito.verify(artistFactorySpy)
            .delegateCreateArtist(artistNameCaptor.capture(), genreNameCaptor.capture());

        System.out.println(artistNameCaptor.getValue());
        System.out.println(genreNameCaptor.getValue());
    }
}
