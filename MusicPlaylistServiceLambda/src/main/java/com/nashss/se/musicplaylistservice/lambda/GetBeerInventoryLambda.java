package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetBeerInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetBeerInventoryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetBeerInventoryLambda extends LambdaActivityRunner<GetBeerInventoryRequest, GetBeerInventoryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetBeerInventoryRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetBeerInventoryRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    GetBeerInventoryRequest unauthenticatedRequest = input.fromBody(GetBeerInventoryRequest.class);
                    return input.fromUserClaims(claims ->
                            GetBeerInventoryRequest.builder()
                                    .withBeerType(unauthenticatedRequest.getBeerType())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetBeerInventoryRequest().handleRequest(request)
        );

    }
}
