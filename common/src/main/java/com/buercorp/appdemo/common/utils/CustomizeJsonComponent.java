package com.buercorp.appdemo.common.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.IOException;

/**
 * @description Jackson 工具类
 *
 * @author tanghx
 * @date 2023/12/26 17:18
 */
@JsonComponent
public class CustomizeJsonComponent {

    /**
     * 序列化
     */
    public static class T extends JsonObjectSerializer<T> {
        @Override
        protected void serializeObject(T value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

        }
    }

    /**
     * 反序列化
     */
    public static class Deserializer extends JsonObjectDeserializer<T> {

        @Override
        protected T deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
            return null;
        }
    }
}
