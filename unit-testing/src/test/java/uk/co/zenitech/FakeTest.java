package uk.co.zenitech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.co.zenitech.domain.Genre;
import uk.co.zenitech.factory.ArtistFactory;
import uk.co.zenitech.repository.ArtistRepository;
import uk.co.zenitech.service.ArtistService;

public class FakeTest {
    private final ArtistRepository artistRepository = new LocalArtistRepository();
    private final ArtistService artistService = new ArtistService(artistRepository);

    @Test
    void givenFakeRepository_whenSavingAndFindingArtist_thenReturnSaved() {
        var artist = ArtistFactory.createArtist("artist", Genre.jazz);
        artistService.save(artist);

        Assertions.assertEquals(artist, artistService.find("artist"));
    }
}
