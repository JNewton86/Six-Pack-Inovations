package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetBeerInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.requests.GetPlaylistRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetBeerInventoryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class GetBeerInventoryLambda extends LambdaActivityRunner<GetBeerInventoryRequest, GetBeerInventoryResult>
        implements RequestHandler<LambdaRequest<GetBeerInventoryRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetBeerInventoryRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetBeerInventoryRequest.builder()
                                .withBeerType(path.get("beer"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetBeerInventoryActivity().handleRequest(request)
        );

    }
}
