package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.BeerModel;
import com.nashss.se.musicplaylistservice.models.PlaylistModel;

public class UpdateInventoryResult {

    private final BeerModel beerModel;

    private UpdateInventoryResult(BeerModel beerModel) {
        this.beerModel = beerModel;
    }

    public BeerModel getBeerModel() {
        return beerModel;
    }

    @Override
    public String toString() {
        return "UpdatePlaylistResult{" +
                "playlist=" + playlist +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static UpdatePlaylistResult.Builder builder() {
        return new UpdatePlaylistResult.Builder();
    }

    public static class Builder {
        private PlaylistModel playlist;

        public UpdatePlaylistResult.Builder withPlaylist(PlaylistModel playlist) {
            this.playlist = playlist;
            return this;
        }

        public UpdatePlaylistResult build() {
            return new UpdatePlaylistResult(playlist);
        }
    }
}
