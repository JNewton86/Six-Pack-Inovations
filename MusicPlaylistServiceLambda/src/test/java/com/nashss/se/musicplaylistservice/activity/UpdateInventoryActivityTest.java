package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.UpdateInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdateInventoryResult;
import com.nashss.se.musicplaylistservice.dynamodb.InventoryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.exceptions.BeerNotFoundException;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.musicplaylistservice.metrics.MetricsConstantsSPI;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class UpdateInventoryActivityTest {


    @Mock
    private InventoryDao inventoryDao;

    @Mock
    private MetricsPublisher metricsPublisher;

    private UpdateInventoryActivity updateInventoryActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        updateInventoryActivity = new UpdateInventoryActivity(inventoryDao, metricsPublisher);
    }

    @Test
    public void handleRequest_goodRequest_updatesAvailableUnits() {
        // GIVEN
        String id = "id";
        String packagingType = "KEG";
        int availableUnits = 10;
        int reservedUnits = 10;

        UpdateInventoryRequest request = UpdateInventoryRequest.builder()
                .withBeerId(id)
                .withPackagingType(packagingType)
                .withAvailableUnits(availableUnits)
                .withReservedUnits(reservedUnits)
                .build();

        Beer startingBeerStatus = new Beer();
        startingBeerStatus.setBeerId(id);
        startingBeerStatus.setPackagingType(packagingType);
        startingBeerStatus.setAvailableUnits(availableUnits);
        startingBeerStatus.setReservedUnits(reservedUnits);

        when(inventoryDao.getBeer(id, packagingType)).thenReturn(startingBeerStatus);
        when(inventoryDao.saveBeer(startingBeerStatus)).thenReturn(startingBeerStatus);

        // WHEN
        UpdateInventoryResult result = updateInventoryActivity.handleRequest(request);

        // THEN
        assertEquals(id, result.getBeerModel().getBeerId());
        assertEquals(availableUnits, result.getBeerModel().getAvailableUnits());
        assertEquals(reservedUnits, result.getBeerModel().getReservedUnits());
    }

    @Test
    public void handleRequest_invalidAvailable_throwsInvalidAttributeValueException() {
        // GIVEN
        UpdateInventoryRequest request = UpdateInventoryRequest.builder()
                .withBeerId("id")
                .withPackagingType("KEG")
                .withAvailableUnits(-10)
                .withReservedUnits(10)
                .build();

        // WHEN + THEN
        try {
            updateInventoryActivity.handleRequest(request);
            fail("Expected InvalidAttributeValueException to be thrown");
        } catch (InvalidAttributeValueException e) {
            verify(metricsPublisher).addCount(MetricsConstantsSPI.UPDATEINVENTORY_INVALIDATTRIBUTEVALUE_COUNT, 1);
        }
    }

    @Test
    public void handleRequest_beerDoesNotExist_throwsBeerNotFoundException() {
        // GIVEN
        String id = "id";
        UpdateInventoryRequest request = UpdateInventoryRequest.builder()
                .withBeerId(id)
                .withPackagingType("KEG")
                .withAvailableUnits(10)
                .withReservedUnits(10)
                .build();

        when(inventoryDao.getBeer(id, "CASE")).thenThrow(new BeerNotFoundException());

        // THEN
        assertThrows(BeerNotFoundException.class, () -> updateInventoryActivity.handleRequest(request));
    }

//    @Test
//    public void handleRequest_customerIdNotMatch_throwsSecurityException() {
//        // GIVEN
//        String id = "id";
//        UpdatePlaylistRequest request = UpdatePlaylistRequest.builder()
//                .withId(id)
//                .withName("name")
//                .withCustomerId("customerId")
//                .build();
//
//        Playlist differentCustomerIdPlaylist = new Playlist();
//        differentCustomerIdPlaylist.setCustomerId("different");
//
//        when(playlistDao.getPlaylist(id)).thenReturn(differentCustomerIdPlaylist);
//
//        // WHEN + THEN
//        try {
//            updatePlaylistActivity.handleRequest(request);
//            fail("Expected InvalidAttributeChangeException to be thrown");
//        } catch (SecurityException e) {
//            verify(metricsPublisher).addCount(MetricsConstants.UPDATEPLAYLIST_INVALIDATTRIBUTEVALUE_COUNT, 0);
//            verify(metricsPublisher).addCount(MetricsConstants.UPDATEPLAYLIST_INVALIDATTRIBUTECHANGE_COUNT, 1);
//        }
    }

