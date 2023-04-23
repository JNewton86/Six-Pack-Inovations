package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.ProcessOrderRequest;
import com.nashss.se.musicplaylistservice.activity.results.ProcessOrderResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ProcessOrderLambda
        extends LambdaActivityRunner<ProcessOrderRequest, ProcessOrderResult>
        implements RequestHandler<AuthenticatedLambdaRequest<ProcessOrderRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<ProcessOrderRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    ProcessOrderRequest unauthenticatedRequest = input.fromBody(ProcessOrderRequest.class);
                    return input.fromUserClaims(claims ->
                            ProcessOrderRequest.builder()
                                    .withOrderId(unauthenticatedRequest.getOrderId())
                                    .withOrderProcessed(unauthenticatedRequest.isOrderProcessed())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideProcessOrderActivity().handleRequest(request));

    }
}
