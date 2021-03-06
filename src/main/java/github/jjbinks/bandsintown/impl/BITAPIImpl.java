package github.jjbinks.bandsintown.impl;

import github.jjbinks.bandsintown.impl.resource.ArtistInfoResource;
import github.jjbinks.bandsintown.api.BITAPI;
import github.jjbinks.bandsintown.api.BITAPIClient;
import github.jjbinks.bandsintown.dto.Artist;
import github.jjbinks.bandsintown.dto.ArtistEvent;
import github.jjbinks.bandsintown.exception.BITException;
import github.jjbinks.bandsintown.impl.resource.ArtistEventsResource;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BITAPIImpl implements BITAPI {

    private BITAPIClient bitAPIClient;

    public BITAPIImpl(String appId) {
        this(JerseyClientBuilder.createClient(), appId);
    }

    public BITAPIImpl(Client restClient, String appId) {
        bitAPIClient = new BITAPIClientImpl(restClient, appId);
    }


    @Override
    public List<ArtistEvent> getArtistEvents(String artist) throws BITException {
        return bitAPIClient.getBITResource(new ArtistEventsResource(artist));
    }

    @Override
    public List<ArtistEvent> getArtistEvents(String artist, LocalDate fromDate, LocalDate toDate) throws BITException {
        Objects.requireNonNull(fromDate);
        Objects.requireNonNull(toDate);
        if(toDate.isBefore(fromDate)){
            throw new BITException("toDate cannot be before fromDate!");
        }
        return bitAPIClient.getBITResource(new ArtistEventsResource(artist, fromDate, toDate));
    }

    @Override
    public Artist getArtist(String artist) throws BITException {
        return bitAPIClient.getBITResource(new ArtistInfoResource(artist));
    }

}
