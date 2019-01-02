package com.zxks.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;


public class JsonTool {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 对象转json
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转集合对象
     * @param json
     * @param collectionClazz 具体的集合类，如：ArrayList.class
     * @param clazz 集合内存放对象的class
     * @param <T>
     * @return
     */
    public static <T> Collection<T> fromJson(String json,Class<? extends Collection> collectionClazz,Class<T> clazz){
        if(json == null){
            return null;
        }
        Collection<T> collection = null;
        try {
            collection = mapper.readValue(json, getCollectionType(collectionClazz,clazz));
            return collection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
