package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.musicplaylistservice.dynamodb.models.AlbumTrack;
import com.nashss.se.musicplaylistservice.exceptions.AlbumTrackNotFoundException;
import com.nashss.se.musicplaylistservice.exceptions.BeerNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;

//TODO update @Link with Beer
/**
 * Accesses data for an album using {@link AlbumTrack} to represent the model in DynamoDB.
 */
@Singleton
public class InventoryDao {

    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates an InventoryDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the album_track table
     */

    @Inject
    public InventoryDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Retrieves a beer by beerId and packagingType.
     *
     * If not found, throws BeerNotFoundException.
     *
     * @param beerId The beerId to look up
     * @param packageType the type of packaging
     * @return The corresponding AlbumTrack if found
     */
    public Beer getBeer(String beerId, Enum packageType) {
        Beer beer = dynamoDbMapper.load(Beer.class, beerId, packageType);
        if (null == beer) {
            throw new BeerNotFoundException(
                    String.format("Could not find beer with beerID '%s' and packageType %d", beerId, packageType));
        }

        return beer;
    }






}
