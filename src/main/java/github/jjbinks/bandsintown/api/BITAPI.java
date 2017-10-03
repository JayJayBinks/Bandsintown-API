package github.jjbinks.bandsintown.api;

import github.jjbinks.bandsintown.dto.Artist;
import github.jjbinks.bandsintown.dto.ArtistEvent;
import github.jjbinks.bandsintown.exception.BITException;

import java.util.List;

public interface BITAPI {
    List<ArtistEvent> getArtistEvents(String artist) throws BITException;
    Artist getArtist(String artist) throws BITException;
}
