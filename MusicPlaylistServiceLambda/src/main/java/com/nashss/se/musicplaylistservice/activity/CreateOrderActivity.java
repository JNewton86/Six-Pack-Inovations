package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.CreateOrderRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreatePlaylistResult;
import com.nashss.se.musicplaylistservice.converters.BeerToBeerModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.OrderDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Order;
import com.nashss.se.musicplaylistservice.models.OrderModel;

import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CreateOrderActivity {
    private final Logger log = LogManager.getLogger();
    private final OrderDao orderDao;


    public CreateOrderActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public CreatePlaylistResult handleRequest(final CreateOrderRequest createOrderRequest) {
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
        newOrder.setTotalCost(createOrderRequest.getTotalCost());
        newOrder.setOrderProcessed(createOrderRequest.isOrderProcessed());

        orderDao.saveOrder(newOrder);
//ToDo this part below shouldn't have errors once I make a CreateOrderResult
        OrderModel orderModel = new BeerToBeerModelConverter().toOrderModel(newOrder);
        return CreatePlaylistResult.builder()
                .withPlaylist(playlistModel)
                .build();
    }
}
