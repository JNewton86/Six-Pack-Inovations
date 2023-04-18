package com.nashss.se.musicplaylistservice.converters;

import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.dynamodb.models.Order;
import com.nashss.se.musicplaylistservice.models.BeerModel;
import com.nashss.se.musicplaylistservice.models.OrderModel;

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

    public OrderModel toOrderModel(Order order) {

        return OrderModel.builder()
                .withId(order.getId())
                .withClientId(order.getId())
                .withOrderItems(order.getOrderItems())
                .withTotalCost(order.getTotalCost())
                .withOrderProcessed(order.getOrderProcessed())
                .build();
    }
}
