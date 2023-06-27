//package org.example;
//
//import com.amazonaws.services.kinesis.model.GetRecordsRequest;
//import com.amazonaws.services.kinesis.model.GetRecordsResult;
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        GetRecordsRequest recordsRequest = new GetRecordsRequest();
//        recordsRequest.setShardIterator(shardIterator.getShardIterator());
//        recordsRequest.setLimit(25);
//
//        GetRecordsResult recordsResult = kinesis.getRecords(recordsRequest);
//        while (!recordsResult.getRecords().isEmpty()) {
//            recordsResult.getRecords().stream()
//                .map(record -> new String(record.getData().array()))
//                .forEach(System.out::println);
//
//            recordsRequest.setShardIterator(recordsResult.getNextShardIterator());
//            recordsResult = kinesis.getRecords(recordsRequest);
//    }
//}