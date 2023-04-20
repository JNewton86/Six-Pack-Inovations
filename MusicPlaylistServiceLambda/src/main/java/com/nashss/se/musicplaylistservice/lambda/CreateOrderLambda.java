package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.CreateOrderRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreateOrderResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateOrderLambda extends LambdaActivityRunner<CreateOrderRequest, CreateOrderResult>
implements RequestHandler<LambdaRequest<CreateOrderRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateOrderRequest>input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> input.fromBody(CreateOrderRequest.class),
            (request, serviceComponent) ->
        serviceComponent.provideCreateOrderActivity().handleRequest(request));
    }
}
