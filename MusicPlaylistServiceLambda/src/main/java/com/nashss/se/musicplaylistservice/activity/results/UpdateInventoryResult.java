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
        return "UpdateInventoryResult{" +
                "BeerModel=" + beerModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static UpdateInventoryResult.Builder builder() {
        return new UpdateInventoryResult.Builder();
    }

    public static class Builder {
        private BeerModel beerModel;

        public UpdateInventoryResult.Builder withBeerModel(BeerModel beerModel) {
            this.beerModel = beerModel;
            return this;
        }

        public UpdateInventoryResult build() {
            return new UpdateInventoryResult(beerModel);
        }
    }
}
