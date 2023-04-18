package com.nashss.se.musicplaylistservice.models;

import com.nashss.se.musicplaylistservice.dynamodb.models.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.nashss.se.musicplaylistservice.utils.CollectionUtils.copyToList;

public class OrderModel {

    private final String orderId;
    private final String clientId;
    private final List<OrderItem> orderItems;
    private final Double totalCost;
    private boolean orderProcessed;

    public OrderModel(String id, String clientId, List<OrderItem> orderItems, Double totalCost) {
        this.orderId = id;
        this.clientId = clientId;
        this.orderItems = orderItems;
        this.totalCost = totalCost;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getClientId() {
        return clientId;
    }

    //change String to Beer object when merged
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public boolean isOrderProcessed() {
        return orderProcessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderModel that = (OrderModel) o;

        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(orderItems, that.orderItems) &&
                Objects.equals(totalCost, that.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, orderItems, totalCost);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String clientId;
        private List<OrderItem> orderItems;
        private Double totalCost;

        public Builder withOrderId(String id) {
            this.id = id;
            return this;
        }

        public Builder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withTotalCost(Double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public Builder withOrderItems(List<OrderItem> orderItems) {
            this.orderItems = copyToList(orderItems);
            return this;
        }

        public OrderModel build() {
            return new OrderModel(id, clientId, orderItems, totalCost);
        }
    }
}
