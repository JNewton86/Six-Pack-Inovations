package com.nashss.se.musicplaylistservice.converters;

import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.models.BeerModel;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class BeerToBeerModelConverterTest {

    private BeerToBeerModelConverter convertMyBeer = new BeerToBeerModelConverter();

    @Test
    void toBeerModel_valid_convertsBeer() {
        Beer beer = new Beer();
        beer.setBeerId("Laeger123");
        beer.setPackagingType("KEG");
        beer.setName("Two Beers and Burger");
        beer.setBeerType("LAGER");
        beer.setAvailableUnits(10);
        beer.setReservedUnits(10);
        beer.setUnitPrice(9.99);

        BeerModel beerModel = convertMyBeer.toBeerModel(beer);
        assertEquals(beer.getBeerId(), beerModel.getBeerId());
        assertEquals(beer.getPackagingType(), beerModel.getPackagingType());
        assertEquals(beer.getName(), beerModel.getName());
        assertEquals(beer.getBeerType(), beerModel.getBeerType());
        assertEquals(beer.getAvailableUnits(), beerModel.getAvailableUnits());
        assertEquals(beer.getReservedUnits(), beerModel.getReservedUnits());
        assertEquals(beer.getUnitPrice(), beerModel.getUnitPrice());

    }

    @Test
    void toBeerModel_invalidAvailableBeer_convertsBeer() {
        Beer beer = new Beer();
        beer.setBeerId("Laeger123");
        beer.setPackagingType("KEG");
        beer.setName("Two Beers and Burger");
        beer.setBeerType("LAGER");
        beer.setAvailableUnits(-10);
        beer.setReservedUnits(10);
        beer.setUnitPrice(.99);

        BeerModel beerModel = convertMyBeer.toBeerModel(beer);
        assertEquals(beer.getBeerId(), beerModel.getBeerId());
        assertEquals(beer.getPackagingType(), beerModel.getPackagingType());
        assertEquals(beer.getName(), beerModel.getName());
        assertEquals(beer.getBeerType(), beerModel.getBeerType());
        assertEquals(beer.getAvailableUnits(), beerModel.getAvailableUnits());
        assertEquals(beer.getReservedUnits(), beerModel.getReservedUnits());
        assertEquals(beer.getUnitPrice(), beerModel.getUnitPrice());
    }

    @Test
    void toBeerModel_nullField_convertsBeer() {
        Beer beer = new Beer();
        beer.setBeerId("Laeger123");
        beer.setPackagingType("KEG");
        beer.setName("Two Beers and Burger");
        beer.setBeerType(null);
        beer.setAvailableUnits(-10);
        beer.setReservedUnits(10);
        beer.setUnitPrice(9.99);

        BeerModel beerModel = convertMyBeer.toBeerModel(beer);
        assertEquals(beer.getBeerId(), beerModel.getBeerId());
        assertEquals(beer.getPackagingType(), beerModel.getPackagingType());
        assertEquals(beer.getName(), beerModel.getName());
        assertEquals(beer.getBeerType(), beerModel.getBeerType());
        assertEquals(beer.getAvailableUnits(), beerModel.getAvailableUnits());
        assertEquals(beer.getReservedUnits(), beerModel.getReservedUnits());
        assertEquals(beer.getUnitPrice(), beerModel.getUnitPrice());
    }
}
