package uk.co.zenitech;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import uk.co.zenitech.domain.Album;
import uk.co.zenitech.domain.Genre;
import uk.co.zenitech.factory.ArtistFactory;
import uk.co.zenitech.repository.ArtistRepository;
import uk.co.zenitech.service.ArtistService;

public class AssertJTest {
    private final ArtistRepository artistRepository = new LocalArtistRepository();
    private final ArtistService artistService = new ArtistService(artistRepository);

    @Test
    void givenFakeRepository_whenSavingAndFindingArtist_thenReturnSaved() {
        var artist = ArtistFactory.createArtist("artist", Genre.jazz);
        artistService.save(artist);

        var savedArtist = artistService.find("artist");

        assertThat(savedArtist)
            .isNotNull()
            .isEqualTo(artist)
            .matches(artist1 -> !artist.getAlbums().isEmpty());

        assertThat(savedArtist.getName()).startsWith("art")
            .endsWith("ist")
            .isEqualToIgnoringCase("artist");

        assertThat(savedArtist.getAlbums())
            .hasSizeLessThan(100)
            .hasSizeBetween(1, 100);

        assertThat(savedArtist.getAlbums())
            .extracting("name")
            .contains("album_1")
            .doesNotContain("album_5000");
    }
}
