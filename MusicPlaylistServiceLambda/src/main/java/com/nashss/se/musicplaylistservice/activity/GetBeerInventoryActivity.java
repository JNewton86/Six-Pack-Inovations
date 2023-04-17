package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetBeerInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetBeerInventoryResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverterSPI;
import com.nashss.se.musicplaylistservice.dynamodb.InventoryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.exceptions.BeerNotFoundException;
import com.nashss.se.musicplaylistservice.models.BeerModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class GetBeerInventoryActivity {

    private final Logger log = LogManager.getLogger();
    private final InventoryDao inventoryDao;

    @Inject
    public GetBeerInventoryActivity(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public GetBeerInventoryResult handleRequest(final GetBeerInventoryRequest getBeerInventoryRequest) {
        log.info("Received GetPlaylistSongsRequest {}", getBeerInventoryRequest);
        String beerType = getBeerInventoryRequest.getBeerType();

        if (beerType == null){
            throw new BeerNotFoundException("This BeerType is invalid!");
        }

        List<Beer> listOfBeers = inventoryDao.getBeersByType(beerType);
        log.info("made it past inventorydao.getBeerByType List<Beer> = {}", listOfBeers.toString());
        List<BeerModel> beerModelList = new ModelConverterSPI().toBeerModelList(listOfBeers);
        log.info("made it past converter in getbeeractivity");
        return GetBeerInventoryResult.builder()
                .withBeerModelList(beerModelList)
                .build();
    }
}
