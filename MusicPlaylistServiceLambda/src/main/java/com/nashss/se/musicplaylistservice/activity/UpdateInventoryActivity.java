package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.UpdateInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdateInventoryResult;
import com.nashss.se.musicplaylistservice.converters.BeerToBeerModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.InventoryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.exceptions.BeerNotFoundException;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.musicplaylistservice.metrics.MetricsConstants;
import com.nashss.se.musicplaylistservice.metrics.MetricsConstantsSPI;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;
import com.nashss.se.musicplaylistservice.models.PlaylistModel;
import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the UpdatePlaylistActivity for the MusicPlaylistService's UpdatePlaylist API.
 *
 * This API allows the customer to update their saved playlist's information.
 */
public class UpdateInventoryActivity {

    private final Logger log = LogManager.getLogger();

    private final InventoryDao inventoryDao;

    private final MetricsPublisher metricsPublisher;


    /**
     * Instantiates a new UpdatePlaylistActivity object.
     *
     * @param inventoryDao InventoryDao to access the inventory table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public UpdateInventoryActivity(InventoryDao inventoryDao, MetricsPublisher metricsPublisher) {
        this.inventoryDao = inventoryDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the beer object, updating it,
     * and persisting the inventory object.
     * <p>
     * It then returns the updated inventory item.
     * <p>
     * If the beer does not exist, this should throw a BeerNotFoundException.
     * <p>
     * If the provided beer name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the request tries to update the customer ID,
     * this should throw an InvalidAttributeChangeException
     *
     * @param updateInventoryRequest request object containing the beer ID, packageing type,
     *                               available units and reserved units associated with it
     * @return updatePlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    public UpdateInventoryResult handleRequest(final UpdateInventoryRequest updateInventoryRequest) {
        log.info("Received UpdateInventoryRequest {}", updateInventoryRequest);

        // Check to ensure requested changes will not result in a negative inventory amount, add count to CloudWatch
        if (updateInventoryRequest.getAvailableUnits() < 0 || updateInventoryRequest.getReservedUnits() < 0) {
            publishExceptionMetrics(true);
            throw new InvalidAttributeValueException("requested availableUnit or reservedUnits invalid");
        }
        log.info("passed check to confirm request did not contain update to less than zero");
        Beer beer = inventoryDao.getBeer(updateInventoryRequest.getBeerId(), updateInventoryRequest.getPackagingType());
        log.info("passed call to talbe");
        // If beer not found in the table throws BeerNotFound and adds count to CloudWatch
        if (beer == null) {
            metricsPublisher.addCount(MetricsConstantsSPI.UPDATEINVENTORY_BEERNOTFOUND_COUNT, 0);
            throw new BeerNotFoundException("beer inventory object not found to update");
        }
        log.info("passed call to inventory table beer = {}", beer);
        beer.setAvailableUnits(updateInventoryRequest.getAvailableUnits());
        beer.setReservedUnits(updateInventoryRequest.getReservedUnits());
        beer.setUnitPrice(updateInventoryRequest.getUnitPrice());

        beer = inventoryDao.saveBeer(beer);
        log.info("updated retrieved object and saved beer to table beer = {}", beer);
        publishExceptionMetrics(false);
        return UpdateInventoryResult.builder()
                .withBeerModel(new BeerToBeerModelConverter().toBeerModel(beer))
                .build();
    }

    /**
     * Helper method to publish exception metrics.
     * @param isInvalidAttributeValue indicates whether InvalidAttributeValueException is thrown
     */
    private void publishExceptionMetrics(final boolean isInvalidAttributeValue) {
        metricsPublisher.addCount(MetricsConstantsSPI.UPDATEINVENTORY_INVALIDATTRIBUTEVALUE_COUNT,
                isInvalidAttributeValue ? 1 : 0);
    }






}
