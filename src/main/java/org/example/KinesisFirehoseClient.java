package org.example;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;

public class KinesisFirehoseClient {
    public static final String AWS_ACCESS_KEY_ID = "aws.accessKeyId";
    public static final String AWS_SECRET_KEY = "aws.secretKey";

    static {
        System.setProperty(AWS_ACCESS_KEY_ID, System.getenv("access_key"));
        System.setProperty(AWS_SECRET_KEY, System.getenv("secret_access_key"));
    }

    public static AmazonKinesisFirehose getFirehoseClient(){
        return AmazonKinesisFirehoseClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_2)
                .build();
    }
}