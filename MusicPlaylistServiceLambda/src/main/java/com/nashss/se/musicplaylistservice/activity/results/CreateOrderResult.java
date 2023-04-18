package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.OrderModel;
import com.nashss.se.musicplaylistservice.models.PlaylistModel;

public class CreateOrderResult {
    private final OrderModel orderModel;

    public CreateOrderResult(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    @Override
    public String toString() {
        return "CreateOrderResult{" +
                "orderModel=" + orderModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderModel order;

        public Builder withOrder(OrderModel Order) {
            this.order = order;
            return this;
        }

        public CreateOrderResult build() {
            return new CreateOrderResult(order);
        }
    }
}
