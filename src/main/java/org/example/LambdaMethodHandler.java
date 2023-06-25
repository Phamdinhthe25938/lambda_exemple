package org.example;

import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;

public class LambdaMethodHandler implements RequestHandler<S3Event, String> {

    @Override
    public String handleRequest(S3Event input, Context context) {
        context.getLogger().log("Start run test deploy");
        for (S3EventNotification.S3EventNotificationRecord record : input.getRecords()) {
            String bucketName = record.getS3().getBucket().getName();
            String objectKey = record.getS3().getObject().getKey();
            String eventType = record.getEventName();

            // do something with the uploaded S3 object
            System.out.format("S3 %s event received for object %s in bucket %s", eventType, objectKey, bucketName);
        }
        return "success";

    }
}
