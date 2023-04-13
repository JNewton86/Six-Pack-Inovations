package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.BeerModel;

import java.util.ArrayList;
import java.util.List;

public class GetBeerInventoryResult {

    private final List<BeerModel> beerModelList;

    public GetBeerInventoryResult(List<BeerModel> beerModelList) {
        this.beerModelList = beerModelList;
    }

    @Override
    public String toString() {
        return "GetBeerInventoryActivity{" +
                "beerList=" + beerModelList +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public List<BeerModel> getBeerModelList() {
        return new ArrayList<>(beerModelList);
    }

    public static class Builder{
        private List<BeerModel>  beerModelList;

        public Builder withBeerModelList(List<BeerModel> beerModelList){
            this.beerModelList = new ArrayList<>(beerModelList);
            return this;
        }
        public GetBeerInventoryResult build(){
            return new GetBeerInventoryResult(beerModelList);
        }
    }
}
