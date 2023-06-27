//package org.example;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.kinesis.AmazonKinesis;
//import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class Config {
//  BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIAYMAHNDIAJZUH3BYL", "uvoOCjyNFARxd7uKsejuHuqNlTucgfdedV/n22ou");
//  AmazonKinesisClientBuilder ob = AmazonKinesisClientBuilder.standard()
//      .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//      .withRegion(Regions.EU_WEST_2);
//
//  @Bean
//  public AmazonKinesis buildAmazonKinesis() {
//    BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIAYMAHNDIAJZUH3BYL", "uvoOCjyNFARxd7uKsejuHuqNlTucgfdedV/n22ou");
//    return AmazonKinesisClientBuilder.standard()
//        .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//        .withRegion(Regions.EU_WEST_2)
//        .build();
//  }
//}
