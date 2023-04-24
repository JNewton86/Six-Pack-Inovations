package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.ProcessOrderRequest;
import com.nashss.se.musicplaylistservice.activity.results.ProcessOrderResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverterSPI;
import com.nashss.se.musicplaylistservice.dynamodb.OrderDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Order;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.musicplaylistservice.metrics.MetricsConstants;
import com.nashss.se.musicplaylistservice.metrics.MetricsPublisher;
import com.nashss.se.musicplaylistservice.models.OrderModel;
import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class ProcessOrderActivity {
    private final Logger log = LogManager.getLogger();
    private final OrderDao orderDao;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public ProcessOrderActivity(OrderDao orderDao, MetricsPublisher metricsPublisher) {
        this.orderDao = orderDao;
        this.metricsPublisher = metricsPublisher;
    }

    public ProcessOrderResult handleRequest(final ProcessOrderRequest processOrderRequest) {
        log.info("Received ProcessOrderRequest {}", processOrderRequest);

        if (!MusicPlaylistServiceUtils.isValidString(processOrderRequest.getOrderId())) {
            throw new InvalidAttributeValueException("Invalid orderId [" + processOrderRequest.getOrderId() + "]");
        }

        Order order = orderDao.getRequestedOrder(processOrderRequest.getOrderId());
        order.setOrderProcessed(processOrderRequest.isOrderProcessed());
        order = orderDao.saveOrder(order);

        return ProcessOrderResult.builder()
                .withOrder(new ModelConverterSPI().toOrderModel(order))
                .build();
    }
}
