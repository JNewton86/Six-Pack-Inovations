package com.nashss.se.musicplaylistservice.converters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//public class BeerTypeConverter implements DynamoDBTypeConverter<String, BeerType> {
//    private static final Gson GSON = new Gson();
//    private final Logger log = LogManager.getLogger();
//
//    @Override
//    public String convert(BeerType object) {
//        return GSON.toJson(object);
//    }
//
//    @Override
//    public BeerType unconvert(String object) {
//        return GSON.fromJson(object, BeerType.class);
//    }
//
//}
