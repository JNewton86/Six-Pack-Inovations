package com.nashss.se.musicplaylistservice.converters;

import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.models.BeerModel;
import com.nashss.se.musicplaylistservice.models.beerenums.BeerType;
import com.nashss.se.musicplaylistservice.models.beerenums.PackagingType;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class BeerToBeerModelConverterTest {

    private BeerToBeerModelConverter convertMyBeer = new BeerToBeerModelConverter();

    @Test
    void toBeerModel_valid_convertsBeer() {
        Beer beer = new Beer();
        beer.setBeerId("Laeger123");
        beer.setPackagingType(PackagingType.KEG);
        beer.setName("Two Beers and Burger");
        beer.setBeerType(BeerType.LAGER);
        beer.setAvailableUnits(10);
        beer.setReservedUnits(10);
        beer.setUnitPrice(new BigDecimal(9.99));

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
        beer.setPackagingType(PackagingType.KEG);
        beer.setName("Two Beers and Burger");
        beer.setBeerType(BeerType.LAGER);
        beer.setAvailableUnits(-10);
        beer.setReservedUnits(10);
        beer.setUnitPrice(new BigDecimal(9.99));

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
        beer.setPackagingType(PackagingType.KEG);
        beer.setName("Two Beers and Burger");
        beer.setBeerType(null);
        beer.setAvailableUnits(-10);
        beer.setReservedUnits(10);
        beer.setUnitPrice(new BigDecimal(9.99));

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
