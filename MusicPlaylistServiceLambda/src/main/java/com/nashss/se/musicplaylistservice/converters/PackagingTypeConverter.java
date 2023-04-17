//package com.nashss.se.musicplaylistservice.converters;
//
//
//import com.google.gson.Gson;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class PackagingTypeConverter implements DynamoDBTypeConverter<String, PackagingType> {
//
//    private static final Gson GSON = new Gson();
//    private final Logger log = LogManager.getLogger();
//    @Override
//    public String convert(PackagingType object) {
//        return GSON.toJson(object);
//    }
//
//    @Override
//    public PackagingType unconvert(String object) {
//        return GSON.fromJson(object, PackagingType.class);
//    }
//}
