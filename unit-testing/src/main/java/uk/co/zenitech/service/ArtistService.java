package uk.co.zenitech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.zenitech.domain.Artist;
import uk.co.zenitech.repository.ArtistRepository;

public class ArtistService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistService.class);

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void save(Artist artist) {
        LOGGER.info("saving artist in service {}", artist);
        this.artistRepository.save(artist);
    }

    public Artist find(String name) {
        LOGGER.info("Find artist in service by name: {}", name);
        return this.artistRepository.find(name);
    }
}
