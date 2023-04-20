package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetOrderRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetOrderResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverterSPI;
import com.nashss.se.musicplaylistservice.dynamodb.OrderDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Order;
import com.nashss.se.musicplaylistservice.models.OrderModel;
import com.nashss.se.musicplaylistservice.models.PlaylistModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the GetPlaylistActivity for the MusicPlaylistService's GetPlaylist API.
 *
 * This API allows the customer to get one of their saved playlists.
 */
public class GetOrderActivity {
    private final Logger log = LogManager.getLogger();
    private final OrderDao orderDao;

    @Inject
    public GetOrderActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public GetOrderResult handleRequest(final GetOrderRequest getOrderRequest) {
        log.info("Received GetOrderRequest {}", getOrderRequest);
        if (getOrderRequest.getId() == null) {
            List<Order> orders = orderDao.getAllOrders();
            List<OrderModel> orderModels = orders.stream().map(order -> new ModelConverterSPI().toOrderModel(order)).collect(Collectors.toList());
            return GetOrderResult.builder()
                    .withOrders(orderModels)
                    .build();
        } else {
            String requestedId = getOrderRequest.getId();
            Order order = orderDao.getOrder(requestedId);
            OrderModel orderModel = new ModelConverterSPI().toOrderModel(order);
            return GetOrderResult.builder()
                    .withOrder(orderModel)
                    .build();
        }
    }
}