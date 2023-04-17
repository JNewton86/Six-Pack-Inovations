package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.UpdateInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdateInventoryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateInventoryLambda
    extends LambdaActivityRunner<UpdateInventoryRequest, UpdateInventoryResult>
    implements RequestHandler<AuthenticatedLambdaRequest<UpdateInventoryRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    //Todo add logger
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateInventoryRequest> input, Context context) {
        log.info("entered lambda for update");
        return super.runActivity(
            () -> {
                log.info("is this where it breaks? no body knows '{}", input.toString());
                UpdateInventoryRequest authenticatedRequest = input.fromBody(UpdateInventoryRequest.class);
                return input.fromUserClaims(claims -> {
                    log.info("request body in lambda : '{}'", authenticatedRequest.toString());
                          return   UpdateInventoryRequest.builder()
                                .withId(authenticatedRequest.getBeerId())
                                .withPackagingType(authenticatedRequest.getPackagingType())
                                .withAvailableUnits(authenticatedRequest.getAvailableUnits())
                                .withReservedUnits(authenticatedRequest.getReservedUnits())
                                .withUnitPrice(authenticatedRequest.getUnitPrice())
                                .build();}

                );

            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateInventoryActivity().handleRequest(request)
    );
    }
}
