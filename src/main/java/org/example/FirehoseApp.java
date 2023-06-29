package org.example;

import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.model.PutRecordBatchRequest;
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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FirehoseApp {
  List<String> productList = new ArrayList<>();

  public static void main(String[] args) throws InterruptedException {
    FirehoseApp app = new FirehoseApp();
    app.sendData();
  }


  public void sendData() throws InterruptedException {
    //create client
    AmazonKinesisFirehose firehoseClient = KinesisFirehoseClient.getFirehoseClient();
    int PARTITION_SIZE = 20;
    List<Record> recordList = new ArrayList<>();
    for (long i = 0; i < 100; i++) {
      Customer customer = new Customer(i, "the " + 1, 20L + i, "nam dinh");
      customer.setCustomer_id(i);
      //create put record request
      recordList.add(new Record().withData(ByteBuffer.wrap(
          getBytesByObject(customer))));
    }
    PutRecordRequest batchRequest = new PutRecordRequest();
    batchRequest.setDeliveryStreamName("load-data-kinesis-test");

    recordList.forEach(e -> {
      batchRequest.setRecord(e);
      firehoseClient.putRecord(batchRequest);
    });
  }

  private static byte[] getBytesByObject(Object ob) {
    ObjectMapper objectMapper = new ObjectMapper();
    StringWriter sw = new StringWriter();
    // build data to json
    try {
      JsonFactory jsonFactory = new JsonFactory();
      JsonGenerator generator = jsonFactory.createGenerator(sw);
      objectMapper.writeValue(generator, ob);
    } catch (Exception e) {
      System.out.println("fail");
    }
    return sw.toString().getBytes(StandardCharsets.UTF_8);
  }
}