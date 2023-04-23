package com.nashss.se.musicplaylistservice.dynamodb;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import com.nashss.se.musicplaylistservice.dynamodb.models.Order;
import com.nashss.se.musicplaylistservice.dynamodb.models.OrderItem;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.musicplaylistservice.exceptions.OrderNotFoundException;

import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;
import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 Accesses data for an order using @/Order to represent the model in DynamoDb.
  **/
@Singleton
public class OrderDao {
    private final DynamoDBMapper dynamoDbMapper;
    //private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a OrderDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the playlists table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public OrderDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        //this.metricsPublisher = metricsPublisher;
    }

    public Order getOrder(String orderId) {
        Order requestedOrder = dynamoDbMapper.load(Order.class, orderId);
        if (null == orderId) {
            //metricsPublisher.addCount(MetricsConstants.GETORDER_ORDERNOTFOUND_COUNT, 1);
            throw new OrderNotFoundException(String.format("Could not find an Order with OrderId '%s' ", orderId));
        }
        return requestedOrder;
    }
    public Order getRequestedOrder(String orderId) {
        if (!MusicPlaylistServiceUtils.isValidString(orderId)) {
            throw new InvalidAttributeValueException("Invalid orderId [" + orderId + "]");
        }

        Order requestedOrder = dynamoDbMapper.load(Order.class, orderId);
        if (requestedOrder == null) {
            throw new OrderNotFoundException(String.format("Could not find an Order with OrderId '%s' ", orderId));
        }

        return requestedOrder;
    }


    public List<Order> getAllOrders(){
        List<Order> orders = dynamoDbMapper.scan(Order.class, new DynamoDBScanExpression());
        return orders;
    }

    /**
    * Saves (creates or updates) the given order.
    *
    * @param newOrder The playlist to save
    * @return The Order object that was saved
    */
    public Order saveOrder(Order newOrder) {
        this.dynamoDbMapper.save(newOrder);
        return newOrder;
    }

    public void updateOrderProcessedState(String orderId, boolean orderProcessed) {
        Order order = getOrder(orderId);
//        if (order != null) {
            order.setOrderProcessed(orderProcessed);
            saveOrder(order);
//        } else {
//            // TODO: Throw an exception or handle the error in some other way
//        }
    }
}
