package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetBeerInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetBeerInventoryResult;
import com.nashss.se.musicplaylistservice.dynamodb.InventoryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.exceptions.BeerNotFoundException;
import com.nashss.se.musicplaylistservice.models.beerenums.BeerType;
import com.nashss.se.musicplaylistservice.models.beerenums.PackagingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetBeerInventoryActivityTest {

    @Mock
    private InventoryDao inventoryDao;
    private GetBeerInventoryActivity getBeerInventoryActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        getBeerInventoryActivity = new GetBeerInventoryActivity(inventoryDao);
    }

    @Test
    void handleRequest_validBeerType_ReturnsBeerModels() {
        BeerType beerType = BeerType.ALE;
        List<Beer> beers = new ArrayList<>();
        Beer beer = new Beer();
        beer.setName("Test Beer");
        beer.setBeerType(beerType);
        beers.add(beer);

        when(inventoryDao.getBeersByType(beerType)).thenReturn(beers);

        GetBeerInventoryRequest request = GetBeerInventoryRequest.builder()
                .withBeerType(beerType)
                .build();
        GetBeerInventoryResult result = getBeerInventoryActivity.handleRequest(request);

        assertNotNull(result);
        assertNotNull(result.getBeerModelList());
        assertFalse(result.getBeerModelList().isEmpty());
        assertEquals(beer.getName(), result.getBeerModelList().get(0).getName());
        assertEquals(beerType, result.getBeerModelList().get(0).getBeerType());
    }
    @Test
    void handleRequest_invalidBeerType_ThrowsBeerNotFoundException() {
        when(inventoryDao.getBeersByType(any())).thenReturn(new ArrayList<>());

        assertThrows(BeerNotFoundException.class, () -> {
            GetBeerInventoryRequest request = GetBeerInventoryRequest.builder()
                    .withBeerType(null)
                    .build();
            getBeerInventoryActivity.handleRequest(request);
        });
    }

    @Test
    void handleRequest_givenBeers_LocatesInTable() {
        List<Beer> beerList = new ArrayList<>();
        Beer beer1 = new Beer();
        beer1.setBeerId("1");
        beer1.setBeerType(BeerType.ALE);
        beer1.setName("Barnacle Brew");
        beer1.setPackagingType(PackagingType.KEG);
        beer1.setUnitPrice(BigDecimal.valueOf(5));
        beer1.setAvailableUnits(10);
        beer1.setReservedUnits(2);
        beerList.add(beer1);

        Beer beer2 = new Beer();
        beer2.setBeerId("2");
        beer2.setBeerType(BeerType.LAGER);
        beer2.setName("Cat Paw IPA");
        beer2.setPackagingType(PackagingType.CASE);
        beer2.setUnitPrice(BigDecimal.valueOf(6));
        beer2.setAvailableUnits(5);
        beer2.setReservedUnits(0);
        beerList.add(beer2);

        Mockito.when(inventoryDao.getBeersByType(BeerType.ALE)).thenReturn(beerList);
        GetBeerInventoryActivity activity = new GetBeerInventoryActivity(inventoryDao);
        GetBeerInventoryRequest request = GetBeerInventoryRequest.builder().withBeerType(BeerType.ALE).build();

        GetBeerInventoryResult result = activity.handleRequest(request);

        assertEquals(beerList.size(), result.getBeerModelList().size());
        assertEquals(beer1.getName(), result.getBeerModelList().get(0).getName());
        assertEquals(beer1.getPackagingType().toString(), result.getBeerModelList().get(0).getPackagingType().toString());
        assertEquals(beer2.getName(), result.getBeerModelList().get(1).getName());
        assertEquals(beer2.getPackagingType().toString(), result.getBeerModelList().get(1).getPackagingType().toString());
    }
}