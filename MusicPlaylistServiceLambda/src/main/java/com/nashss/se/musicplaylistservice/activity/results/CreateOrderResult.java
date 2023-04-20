package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.OrderModel;

public class CreateOrderResult {
    private final OrderModel order;

    public CreateOrderResult(OrderModel order) {
        this.order = order;
    }
    //this method below seems like it will be used for just the test
    public OrderModel getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "CreateOrderResult{" +
                "order=" + order +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderModel order;

        public Builder withOrder(OrderModel order) {
            this.order = order;
            return this;
        }

        public CreateOrderResult build() {
            return new CreateOrderResult(order);
        }
    }
}
