package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.OrderModel;

import java.util.List;

public class GetOrderResult {
    private final OrderModel order;
    private final List<OrderModel> orders;

    private GetOrderResult(OrderModel order, List<OrderModel> orders) {
        this.order = order;
        this.orders = orders;
    }

    public OrderModel getOrder() {
        return order;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "GetOrderResult{" +
                "order=" + order +
                ", orders=" + orders +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderModel order;
        private List<OrderModel> orders;

        public Builder withOrder(OrderModel order) {
            this.order = order;
            return this;
        }

        public Builder withOrders(List<OrderModel> orders) {
            this.orders = orders;
            return this;
        }

        public GetOrderResult build() {
            return new GetOrderResult(order, orders);
        }
    }
}
