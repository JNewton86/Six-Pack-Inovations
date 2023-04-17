package com.nashss.se.musicplaylistservice.activity.requests;


public class GetBeerInventoryRequest {

    private final String beerType;

    public GetBeerInventoryRequest(String beerType) {
        this.beerType = beerType;
    }

    public String getBeerType() {
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
        private String beerType;

        public Builder withBeerType(String beerType){
            this.beerType = beerType;
            return this;
        }

        public GetBeerInventoryRequest build(){
            return new GetBeerInventoryRequest(beerType);
        }
    }

}
