package com.example;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.CosmosDBTrigger;
import com.microsoft.azure.functions.annotation.EventGridOutput;
import com.microsoft.azure.functions.annotation.EventGridTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("OrderAPI")
    public HttpResponseMessage run (
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        final String query = request.getQueryParameters().get("name");
        final String name = request.getBody().orElse(query);

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name).build();
        }
    }

    @FunctionName("PayOrder")
    public void PayOrder (
           @CosmosDBTrigger(
               name = "orders",
               databaseName = "orders",
               collectionName = "orders",
               connectionStringSetting = "CosmosDBConnection",
               createLeaseCollectionIfNotExists = true,
               leaseCollectionName = "leases",
               leaseCollectionPrefix = "lease-") String[] items,
            @EventGridOutput(
                name = "outputEvent", 
                topicEndpointUri = "MyEventGridTopicUriSetting", 
                topicKeySetting = "MyEventGridTopicKeySetting") OutputBinding<String> outputEvent,

            final ExecutionContext context) {

        context.getLogger().info("Java CosmosDB trigger processed a request.");

        
    }

    @FunctionName("ShipOrder")
    public void ShipOrder (
           @EventGridTrigger( name = "orders") String content,
            final ExecutionContext context) {

        context.getLogger().info("Java Event Grid trigger processed a request.");

        
    }
}
