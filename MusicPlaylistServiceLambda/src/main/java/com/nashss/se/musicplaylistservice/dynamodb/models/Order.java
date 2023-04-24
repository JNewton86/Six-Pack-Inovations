package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "orders")
public class Order {
    private String orderId;
    private String clientId;
    private List<OrderItem> orderItems;
    private Double totalCost;
    private Boolean orderProcessed;

    @DynamoDBHashKey(attributeName = "orderId")
    public String getId() {
        return orderId;
    }

    public void setId(String orderId) {
        this.orderId = orderId ;
    }

    @DynamoDBAttribute(attributeName = "clientId")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Returns the list of orderItems associated with this Order, null if there are
     * none.
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
    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    // @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "orderProcessed")
    public Boolean getOrderProcessed() {
        return orderProcessed;
    }

    public void setOrderProcessed(Boolean orderProcessed) {
        this.orderProcessed = orderProcessed;
    }

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

