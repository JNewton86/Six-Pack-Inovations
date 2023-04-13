package com.nashss.se.musicplaylistservice.dynamodb;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import static com.nashss.se.musicplaylistservice.dynamodb.models.Beer.BEERS_BY_TYPE_INDEX;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.exceptions.BeerNotFoundException;
import com.nashss.se.musicplaylistservice.models.beerenums.BeerType;
import com.nashss.se.musicplaylistservice.models.beerenums.PackagingType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    /**
     * Saves (creates or updates) the given beer in the inventory table.
     *
     * @param beer The playlist to save
     * @return The Playlist object that was saved
     */
    public Beer saveBeer(Beer beer) {
        this.dynamoDbMapper.save(beer);
        return beer;
    }

    public List<Beer> getBeersByType(BeerType beerType){
        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":beerType", new AttributeValue().withS(String.valueOf(beerType)));
        DynamoDBQueryExpression<Beer> queryExpression = new DynamoDBQueryExpression<Beer>()
                .withIndexName(BEERS_BY_TYPE_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("beerType = :beerType")
                .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<Beer> beersListedByType = mapper.query(Beer.class, queryExpression);


        return beersListedByType;
    }

}
