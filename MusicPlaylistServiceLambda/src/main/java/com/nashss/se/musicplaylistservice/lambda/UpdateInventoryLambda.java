package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.UpdateInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdateInventoryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.logging.Logger;

public class UpdateInventoryLambda
    extends LambdaActivityRunner<UpdateInventoryRequest, UpdateInventoryResult>
    implements RequestHandler<AuthenticatedLambdaRequest<UpdateInventoryRequest>, LambdaResponse> {

    //Todo add logger
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateInventoryRequest> input, Context context) {
        return super.runActivity(
            () -> {

                UpdateInventoryRequest authenticatedRequest = input.fromBody(UpdateInventoryRequest.class);
                return input.fromUserClaims(claims ->
                        UpdateInventoryRequest.builder()
                                .withId(authenticatedRequest.getbeerId())
                                .withPackagingType(authenticatedRequest.getPackagingType())
                                .withAvailableUnits(authenticatedRequest.getavailableUnits())
                                .withReservedUnits(authenticatedRequest.getavreservedUnits())
                                .withUnitPrice(authenticatedRequest.getUnitPrice())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateInventoryActivity().handleRequest(request)
    );
    }
}
