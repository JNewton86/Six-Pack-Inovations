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

/**
 * Implementation of the GetPlaylistActivity for the MusicPlaylistService's GetPlaylist API.
 *
 * This API allows the customer to get one of their saved playlists.
 */
public class GetOrderActivity {
    private final Logger log = LogManager.getLogger();
    private final OrderDao orderDao;

    /**
     * Instantiates a new GetPlaylistActivity object.
     *
     * @param orderDao PlaylistDao to access the playlist table.
     */
    @Inject
    public GetOrderActivity(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist from the database.
     * <p>
     * It then returns the playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param getPlaylistRequest request object containing the playlist ID
     * @return getPlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    public GetOrderResult handleRequest(final GetOrderRequest getPlaylistRequest) {
        log.info("Received GetPlaylistRequest {}", getPlaylistRequest);
        String requestedId = getPlaylistRequest.getId();
        Order order = orderDao.getOrder(requestedId);
        OrderModel orderModel = new ModelConverterSPI().toOrderModel(order);

        return GetOrderResult.builder()
                .withOrder(orderModel)
                .build();
    }
}