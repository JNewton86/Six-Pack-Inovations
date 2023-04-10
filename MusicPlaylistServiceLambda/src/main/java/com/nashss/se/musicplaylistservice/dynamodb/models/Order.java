package com.nashss.se.musicplaylistservice.dynamodb.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.nashss.se.musicplaylistservice.utils.CollectionUtils.copyToList;

public class Order {

    private final String id;
    private final String clientId;
    private final List<String> orderItems;
    private final BigDecimal totalCost;
    private boolean orderProcessed;

    public Order(String id, String clientId, List<String> orderItems, BigDecimal totalCost) {
        this.id = id;
        this.clientId = clientId;
        this.orderItems = orderItems;
        this.totalCost = totalCost;
    }

    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

//change String to Beer object when merged
    public List<String> getOrderItems() {
        return orderItems;
    }

    public BigDecimal getTotalCost() {
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

        Order that = (Order) o;

        return Objects.equals(id, that.id) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(orderItems, that.orderItems) &&
                Objects.equals(totalCost, that.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, orderItems, totalCost);
    }

    //CHECKSTYLE:OFF:Builder
    public static Order.Builder builder() {
        return new Order.Builder();
    }

    public static class Builder {
        private String id;
        private String clientId;
        private List<String> orderItems;
        private BigDecimal totalCost;

        public Order.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Order.Builder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Order.Builder withTotalCost(BigDecimal totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public Order.Builder withOrderItems(List<String> orderItems) {
            this.orderItems = copyToList(orderItems);
            return this;
        }

        public Order build() {
            return new Order(id, clientId, orderItems, totalCost);
        }
    }
}
