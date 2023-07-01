//package org.example;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
//import com.amazonaws.services.kinesis.model.GetRecordsRequest;
//import com.amazonaws.services.kinesis.model.GetRecordsResult;
//import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
//import com.amazonaws.services.lambda.runtime.*;
//import com.amazonaws.services.lambda.runtime.events.S3Event;
//import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
//
//public class LambdaMethodHandler implements RequestHandler<S3Event, String> {
//
//    @Override
//    public String handleRequest(S3Event input, Context context) {
//        BasicAWSCredentials awsCredentials = new BasicAWSCredentials("", "/n22ou");
//        AmazonKinesisClientBuilder ob = AmazonKinesisClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .withRegion(Regions.EU_WEST_2);
//
//        GetRecordsRequest recordsRequest = new GetRecordsRequest();
//        recordsRequest.setShardIterator(shardIterator.getShardIterator());
//        recordsRequest.setLimit(25);
//
//        GetRecordsResult recordsResult = kinesis.getRecords(recordsRequest);
//        while (!recordsResult.getRecords().isEmpty()) {
//            recordsResult.getRecords().stream()
//                    .map(record -> new String(record.getData().array()))
//                    .forEach(System.out::println);
//
//            recordsRequest.setShardIterator(recordsResult.getNextShardIterator());
//            recordsResult = kinesis.getRecords(recordsRequest);
//        }
//
//
//        context.getLogger().log("Start run test deploy");
//        for (S3EventNotification.S3EventNotificationRecord record : input.getRecords()) {
//            String bucketName = record.getS3().getBucket().getName();
//            String objectKey = record.getS3().getObject().getKey();
//            String eventType = record.getEventName();
//
//            // do something with the uploaded S3 object
//            System.out.format("S3 %s event received for object %s in bucket %s", eventType, objectKey, bucketName);
//        }
//        return "success";
//
//    }
//}
