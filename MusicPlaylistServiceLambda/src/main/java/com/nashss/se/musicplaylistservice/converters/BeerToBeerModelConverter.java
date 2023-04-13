package com.nashss.se.musicplaylistservice.converters;

import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.models.BeerModel;

public class BeerToBeerModelConverter {

    /**
     * Converts a provided {@link Beer} into a {@link com.nashss.se.musicplaylistservice.models.BeerModel}
     * representation.
     *
     * @param beer the beer to convert
     * @return the converted beermodel
     */
    public BeerModel toBeerModel(Beer beer) {

        return BeerModel.builder()
                .withBeerId(beer.getBeerId())
                .withBeerType(beer.getBeerType())
                .withName(beer.getName())
                .withPackagingType(beer.getPackagingType())
                .withUnitPrice(beer.getUnitPrice())
                .withAvailableUnits(beer.getAvailableUnits())
                .withReservedUnits(beer.getReservedUnits())
                .withUnitPrice(beer.getUnitPrice())
                .build();
    }
}
