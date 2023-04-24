package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.OrderModel;
import com.nashss.se.musicplaylistservice.models.PlaylistModel;

public class ProcessOrderResult {
    private final OrderModel order;

    private ProcessOrderResult(OrderModel order) {
        this.order = order;
    }

    public OrderModel getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "ProcessOrderResult{" +
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

        public ProcessOrderResult build() {
            return new ProcessOrderResult(order);
        }
    }
}
