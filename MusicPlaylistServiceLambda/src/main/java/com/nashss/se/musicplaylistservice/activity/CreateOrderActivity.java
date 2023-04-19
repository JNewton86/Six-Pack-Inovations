package com.nashss.se.musicplaylistservice.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.musicplaylistservice.activity.requests.CreateOrderRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreateOrderResult;
import com.nashss.se.musicplaylistservice.converters.BeerToBeerModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.InventoryDao;
import com.nashss.se.musicplaylistservice.dynamodb.OrderDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Beer;
import com.nashss.se.musicplaylistservice.dynamodb.models.Order;
import com.nashss.se.musicplaylistservice.dynamodb.models.OrderItem;
import com.nashss.se.musicplaylistservice.models.OrderModel;

import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the CreateOrderActivity for the MusicPlaylistService's CreatePlaylist API.
 * <p>
 * This API allows the customer to create a new order.
 */
public class CreateOrderActivity {
    private final Logger log = LogManager.getLogger();
    private final OrderDao orderDao;
    private final InventoryDao inventoryDao;

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param orderDao OrderDao to access the order table.
     */
    @Inject
    public CreateOrderActivity(OrderDao orderDao, InventoryDao inventoryDao) {
        this.orderDao = orderDao;
        this.inventoryDao = inventoryDao;
    }

    /**
     * Creates and returns an order.
     *
     * @param createOrderRequest request object containing the orderId, clientId, orderItems, totalCost, and processing status.
     * @return createOrderResult result object containing the API defined {@link OrderModel}
     */
    public CreateOrderResult handleRequest(final CreateOrderRequest createOrderRequest) {
        log.info("Received OrderRequest {}", createOrderRequest);
/*      TODO: I DON'T THINK WE NEED THIS FUNCTIONALITY IN OUR DESIGN.
 */
//        if (!MusicPlaylistServiceUtils.isValidString(createOrderRequest.getName())) {
//            throw new InvalidAttributeValueException("Playlist name [" + createPlaylistRequest.getName() +
//                    "] contains illegal characters");
//        }
//
//        if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getCustomerId())) {
//            throw new InvalidAttributeValueException("Playlist customer ID [" + createPlaylistRequest.getCustomerId() +
//                    "] contains illegal characters");
//        }
//
//        Set<String> playlistTags = null;
//        if (createOrderRequest.getTags() != null) {
//            playlistTags = new HashSet<>(createOrderRequest.getTags());
//        }

        Order newOrder = new Order();
        newOrder.setId(MusicPlaylistServiceUtils.generatePlaylistId());
        newOrder.setClientId(createOrderRequest.getClientId());
        newOrder.setOrderItems(createOrderRequest.getOrderItems());
        //To reviewer: double check the logic of this line below
        newOrder.setTotalCost(createOrderRequest.getOrderItems());
        newOrder.setOrderProcessed(createOrderRequest.isOrderProcessed());

        orderDao.saveOrder(newOrder);

        for(OrderItem item : newOrder.getOrderItems()) {
            //for each item on list, get quantity, type, and packaging, name.
            int units = item.getUnits();
            String beerType = item.getBeerType();
            String beerPackaging = item.getBeerType();
            String beerName = item.getName();

            //from Dao, load all beers of type and packaging
            List<Beer> allBeersOfSameType = inventoryDao.getBeersByType(beerType);
            for(Beer beer : allBeersOfSameType) {
                //identifying the beer item in the inventory table that should be updated
                if (beer.getName() == beerName && beer.getPackagingType() == beerPackaging) {
                    //calculate update to available units
                    int availableUnitsBefore = beer.getAvailableUnits();
                    int updatedAvailableUnits = availableUnitsBefore - units;
                    beer.setAvailableUnits(updatedAvailableUnits);
                    //calculate update to reserved units
                    int reservedUnitsBefore = beer.getReservedUnits();
                    int updatedReservedUnits = reservedUnitsBefore + units;
                    beer.setReservedUnits(updatedReservedUnits);
                    inventoryDao.saveBeer(beer);
                }
            }


        }

        OrderModel orderModel = new BeerToBeerModelConverter().toOrderModel(newOrder);
        return CreateOrderResult.builder()
                .withOrder(orderModel)
                .build();
    }
}
