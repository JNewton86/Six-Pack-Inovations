package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetOrderRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetOrderResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetOrderLambda extends LambdaActivityRunner<GetOrderRequest, GetOrderResult>
        implements RequestHandler<LambdaRequest<GetOrderRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetOrderRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetOrderRequest.builder()
                                .withId(path.get("orderId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetOrderActivity().handleRequest(request)
        );
    }
}