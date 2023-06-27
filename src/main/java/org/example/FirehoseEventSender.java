package org.example;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.firehose.model.Record;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.firehose.FirehoseClient;
import software.amazon.awssdk.services.firehose.model.PutRecordRequest;
import software.amazon.awssdk.services.firehose.model.PutRecordResponse;

import java.util.logging.Logger;

public class FirehoseEventSender {
  private final static Logger logger =
      Logger.getLogger(FirehoseEventSender.class.getName());

  public static void main(String[] args) {
    new FirehoseEventSender().sendEvent();
  }

  public void sendEvent() {
    String deliveryStreamName = "Kinesis-load-data";

    String data = "Test data" + "\n";

    // Create a record for sending to Firehose Delivery Stream
    Record record = Record
        .builder()
        .data(SdkBytes
            .fromByteArray(data.getBytes()))
        .build();

    // Prepare the request for putRecord operation
    PutRecordRequest putRecordRequest =
        PutRecordRequest
            .builder()
            .deliveryStreamName(deliveryStreamName)
            .record(record)
            .build();

    FirehoseClient firehoseClient = getFirehoseClient();

    // Put record into the DeliveryStream
    PutRecordResponse putRecordResponse =
        firehoseClient.putRecord(putRecordRequest);

    logger.info("record ID:: " + putRecordResponse.recordId());

    firehoseClient.close();
  }

  // Create the FirehoseClient with the AWS credentials
  private static FirehoseClient getFirehoseClient() {
    AwsCredentialsProvider credentialsProvider =
        ProfileCredentialsProvider
            .create("the-vti");

    return FirehoseClient
        .builder()
        .credentialsProvider(credentialsProvider)
        .region(Region.EU_WEST_2).build();
  }
}