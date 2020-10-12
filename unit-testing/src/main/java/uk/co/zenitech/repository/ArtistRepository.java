package uk.co.zenitech.repository;

import uk.co.zenitech.domain.Artist;

public interface ArtistRepository {
    void save(Artist artist);

    Artist find(String name);
}
