package com.nashss.se.musicplaylistservice.dynamodb;
import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.exceptions.BeerNotFoundException;
import com.nashss.se.musicplaylistservice.models.beerenums.PackagingType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for an album using {@link Beer} to represent the model in DynamoDB.
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
    public Beer getBeer(String beerId, PackagingType packageType) {
        Beer beer = dynamoDbMapper.load(Beer.class, beerId, packageType);
        if (null == beer) {
            throw new BeerNotFoundException(
                    String.format("Could not find beer with beerID '%s' and packageType %s", beerId, packageType));
        }

        return beer;
    }






}
