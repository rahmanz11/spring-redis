package com.demo.redisoperation.repository;

import com.demo.redisoperation.model.Values;
import org.springframework.stereotype.Repository;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.BinaryJedis;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Repository
public class RedisRepository {
    private BinaryJedis jedisInstance;

    public RedisRepository(BinaryJedis jedisInstance) {
        this.jedisInstance = jedisInstance;
    }

    private static byte[] serialize(Serializable obj) {
        return SerializationUtils.serialize(obj);
    }

    private static Object deserialize(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }

    private void add(final String key, final Values values) {
        byte[] bytes = serialize(values);
        jedisInstance.set(key.toUpperCase().getBytes(StandardCharsets.UTF_8), bytes);
    }

    public void add(final String key, final String value) {
        Values values = get(key);
        if (values != null && values.getValues() != null) {
            List<String> vals = values.getValues();
            vals.add(value);
            values.setValues(vals);
        } else {
            values = new Values();
            values.setValues(new ArrayList<>(Arrays.asList(value)));
        }
        add(key.toUpperCase(), values);
    }

    public Values get(final String key) {
        Object object = deserialize(jedisInstance.get(key.toUpperCase().getBytes(StandardCharsets.UTF_8)));
        Values values = (Values) object;
        return values;
    }

    private void update(final String key, final Values values) {
        jedisInstance.del(key.toUpperCase().getBytes(StandardCharsets.UTF_8));
        add(key, values);
    }

    public void deleteKey(final String key) {
        jedisInstance.del(key.toUpperCase().getBytes(StandardCharsets.UTF_8));
    }

    public void deleteValueByKey(final String key, final String value) {
        Values values = get(key);
        if (values != null && values.getValues() != null) {
            if (values.getValues().contains(value)) {
                final Set<String> uniqueStrSet = new HashSet<String>(values.getValues());
                uniqueStrSet.remove(value);
                values.setValues(new ArrayList<String>(uniqueStrSet));
            }
            if (values.getValues() != null
                    && values.getValues().size() > 0) {
                update(key, values);
            }
        }
    }
}
