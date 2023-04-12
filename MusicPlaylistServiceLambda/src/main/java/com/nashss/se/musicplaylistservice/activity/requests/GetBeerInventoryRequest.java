package com.nashss.se.musicplaylistservice.activity.requests;

import com.nashss.se.musicplaylistservice.models.beerenums.BeerType;

public class GetBeerInventoryRequest {

    private final BeerType beerType;

    public GetBeerInventoryRequest(BeerType beerType) {
        this.beerType = beerType;
    }

    public BeerType getBeerType() {
        return beerType;
    }

    @Override
    public String toString() {
        return "GetBeerInventoryRequest{" +
                "beerType=" + beerType +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private BeerType beerType;

        public Builder withBeerType(BeerType beerType){
            this.beerType = beerType;
            return this;
        }

        public GetBeerInventoryRequest build(){
            return new GetBeerInventoryRequest(beerType);
        }
    }

}
