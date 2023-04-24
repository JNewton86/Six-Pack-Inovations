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
import java.util.ArrayList;

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

        Order newOrder = new Order();
        newOrder.setId(MusicPlaylistServiceUtils.generatePlaylistId());
        newOrder.setOrderItems(createOrderRequest.getOrderItems());
        newOrder.setOrderProcessed(false);
        newOrder.setClientId(createOrderRequest.getClientId());

        orderDao.saveOrder(newOrder);

        OrderModel orderModel = new BeerToBeerModelConverter().toOrderModel(newOrder);
        return CreateOrderResult.builder()
                .withOrder(orderModel)
                .build();
    }
}
//If this is the place we ultimately decide to calculate totalCost, then this code may be helpful:
    //    private Double calculateTotalCost(List<OrderItem> order) {
    //        Double totalCost = 0.0;
    //        for(OrderItem item : order) {
    //            totalCost = totalCost + item.getLineItemPrice();
    //        }
    //        return totalCost;
    //    }
