package com.nashss.se.musicplaylistservice.models;

import com.nashss.se.musicplaylistservice.dynamodb.models.OrderItem;

import java.util.List;
import java.util.Objects;

import static com.nashss.se.musicplaylistservice.utils.CollectionUtils.copyToList;

public class OrderModel {

    private final String id;
    private final String clientId;
    private final String orderItems;
    private Boolean orderProcessed;


    public OrderModel(String id, String clientId, String orderItems, Boolean orderProcessed) {
        this.id = id;
        this.clientId = clientId;
        this.orderItems = orderItems;
        this.orderProcessed = orderProcessed;
    }

    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    //change String to Beer object when merged
    public String getOrderItems() {
        return orderItems;
    }

    public Boolean isOrderProcessed() {
        return orderProcessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return orderProcessed == that.orderProcessed && Objects.equals(id, that.id) && Objects.equals(clientId, that.clientId) && Objects.equals(orderItems, that.orderItems);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, orderItems, orderProcessed);
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id;
        private String clientId;
        private String orderItems;
        private Boolean orderProcessed;


        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withOrderItems(String orderItems) {
            this.orderItems = orderItems;
            return this;
        }
        public Builder withOrderProcessed(Boolean orderProcessed) {
            this.orderProcessed = orderProcessed;
            return this;
        }


        public OrderModel build() {
            return new OrderModel(id, clientId, orderItems, orderProcessed);
        }
    }
}
