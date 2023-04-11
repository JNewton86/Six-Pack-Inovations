package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.nashss.se.musicplaylistservice.converters.AlbumTrackLinkedListConverter;
import com.nashss.se.musicplaylistservice.models.OrderModel;

import java.math.BigDecimal;
import java.util.*;

import static com.nashss.se.musicplaylistservice.utils.CollectionUtils.copyToList;
@DynamoDBTable(tableName = "orders")
public class Order {

    private String orderId;
    private String clientId;
    private List<OrderItem> orderItems;
    private BigDecimal totalCost;
    private boolean orderProcessed;

    @DynamoDBHashKey(attributeName = "orderId")
    public String getId() {
        return orderId;
    }

    public void setId(String orderId) {
        this.orderId = orderId;
    }

    @DynamoDBAttribute(attributeName = "clientId")
    public String getClientIdd() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    /**
     * Returns the list of orderItems associated with this Order, null if there are none.
     *
     * @return Set of tags for this playlist
     */
    @DynamoDBAttribute(attributeName = "orderItems")
    public List<OrderItem> getOrderItems() {
        // normally, we would prefer to return an empty Set if there are no
        // tags, but DynamoDB doesn't represent empty Sets...needs to be null
        // instead
        if (null == orderItems) {
            return null;
        }
        return new ArrayList<>(orderItems);
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        if (null == orderItems) {
            this.orderItems = null;
        } else {
            this.orderItems = new ArrayList<>(orderItems);
        }
        this.orderItems = orderItems;
    }

    @DynamoDBAttribute(attributeName = "totalCost")
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @DynamoDBAttribute(attributeName = "orderProcessed")
    public boolean getOrderProcessed() {
        return orderProcessed;
    }

    public void setOrderProcessed(boolean orderProcessed) {
        this.orderProcessed = orderProcessed;
    }

    // PARTICIPANTS: You do not need to modify the songList getters/setters or annotations
//    @DynamoDBTypeConverted(converter = .class)
//    @DynamoDBAttribute(attributeName = "songList")
//    public List<AlbumTrack> getSongList() {
//        return songList;
//    }
//
//    public void setSongList(List<AlbumTrack> songList) {
//        this.songList = songList;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order that = (Order) o;

        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(orderItems, that.orderItems) &&
                Objects.equals(totalCost, that.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, orderItems, totalCost);
    }
}
