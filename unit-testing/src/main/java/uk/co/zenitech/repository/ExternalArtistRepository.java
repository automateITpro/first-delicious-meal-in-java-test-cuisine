package uk.co.zenitech.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.zenitech.domain.Artist;

public class ExternalArtistRepository implements ArtistRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalArtistRepository.class);

    @Override
    public void save(Artist artist) {
        LOGGER.info("saving artist to external repository {}", artist);
        throw new RuntimeException("exception while saving artist to external service");
    }

    @Override
    public Artist find(String name) {
        LOGGER.info("Find artist from external repository by name: {}", name);
        throw new RuntimeException("exception while finding artist from external service");
    }
}