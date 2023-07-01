package org.example;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest;
import com.amazonaws.services.kinesisfirehose.model.PutRecordResult;
import com.amazonaws.services.kinesisfirehose.model.Record;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class FirehoseApp {
    List<String> productList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        FirehoseApp app = new FirehoseApp();
        app.sendData();
    }


    public void sendData() throws InterruptedException {
        createCatalog();
        //create client
        AmazonKinesisFirehose firehoseClient = KinesisFirehoseClient.getFirehoseClient();
        for (int i = 0; i < 2; i++) {
            //create put record request
            PutRecordRequest putRecordRequest = new PutRecordRequest();
            putRecordRequest.setDeliveryStreamName("load-data-kinesis");
            putRecordRequest.setRecord(new Record().withData(ByteBuffer.wrap(orderList().getBytes())));
            //send data
            PutRecordResult result = firehoseClient.putRecord(putRecordRequest);
            System.out.println("Data upload :" + result.toString());
        }

    }

    private String orderList() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(Math.abs(random.nextInt())).append(",")
                    .append(productList.get(random.nextInt(productList.size()))).append(",")
                    .append(random.nextInt(100)).append("\n");
        }
        return sb.toString().trim();
    }


    private void createCatalog() {
        productList.add("shirt");
        productList.add("t-shirt");
        productList.add("tie");
        productList.add("shorts");
        productList.add("jeans");
        productList.add("flip-flops");
        productList.add("shoes");
    }


//    public static void main(String[] args) throws InterruptedException {
//        FirehoseApp app = new FirehoseApp();
//        app.sendData();
//    }
//
//
//    public void sendData() throws InterruptedException {
//        //create client
//        AmazonKinesis kinesisClient = KinesisFirehoseClient.getKinesisClient();
//        PutRecordsRequest recordsRequest = new PutRecordsRequest();
//        recordsRequest.setStreamName("load-data-kinesis");
//        recordsRequest.setRecords(getRecordsRequestList());
//        PutRecordsResult results = kinesisClient.putRecords(recordsRequest);
//
//        System.out.printf("DATA" + results.toString());
//
//    }
//
//    private List<PutRecordsRequestEntry> getRecordsRequestList() {
//        List<PutRecordsRequestEntry> putRecordsRequestEntries = new ArrayList<>();
//        for (long i = 0; i < 2; i++) {
//            Customer customer = new Customer(i, "the " + 1, 20L + i, "nam dinh");
//            PutRecordsRequestEntry requestEntry = new PutRecordsRequestEntry();
//            requestEntry.setData(ByteBuffer.wrap(
//                    getBytesByObject(customer)));
//            requestEntry.setPartitionKey(UUID.randomUUID().toString());
//            putRecordsRequestEntries.add(requestEntry);
//
//        }
//        return putRecordsRequestEntries;
//    }
//
//    private static byte[] getBytesByObject(Object ob) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        StringWriter sw = new StringWriter();
//        // build data to json
//        try {
//            JsonFactory jsonFactory = new JsonFactory();
//            JsonGenerator generator = jsonFactory.createGenerator(sw);
//            objectMapper.writeValue(generator, ob);
//        } catch (Exception e) {
//            System.out.println("fail");
//        }
//        return sw.toString().getBytes(StandardCharsets.UTF_8);
//    }
}