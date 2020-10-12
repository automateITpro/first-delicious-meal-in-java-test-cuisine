package uk.co.zenitech;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.zenitech.domain.Artist;
import uk.co.zenitech.repository.ArtistRepository;

public class LocalArtistRepository implements ArtistRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalArtistRepository.class);

    private final Map<String, Artist> map = new HashMap<>();

    @Override
    public void save(Artist artist) {
        LOGGER.info("saving artist to local repository {}", artist);
        map.put(artist.getName(), artist);
    }

    @Override
    public Artist find(String name) {
        LOGGER.info("Find artist from local repository by name: {}", name);
        return map.get(name);
    }
}