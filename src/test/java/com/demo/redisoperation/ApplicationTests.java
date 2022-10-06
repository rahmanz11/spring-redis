package com.demo.redisoperation;

import com.demo.redisoperation.model.Values;
import com.demo.redisoperation.repository.RedisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
@ComponentScan("com.demo.redisoperation.repository")
public class ApplicationTests {

    final String KEY = "Peter";
    @Autowired
    RedisRepository redisRepository;

    @Test
    public void testSave() {
        String value1 = "APPL";
        String value2 = "TISL";
        redisRepository.add(KEY, value1);
        redisRepository.add(KEY, value2);
        Values values = redisRepository.get(KEY);
        assertTrue(values.getValues().size() == 2);
    }

    @Test
    public void testGet() {
        Values values = redisRepository.get(KEY);
        assertTrue(values.getValues().contains("APPL"));
    }

    @Test
    public void testDeleteOneValue() {
        redisRepository.deleteValueByKey(KEY, "APPL");
        Values values = redisRepository.get(KEY);
        assertTrue(values != null && values.getValues() != null && !values.getValues().contains("APPL"));
    }

    @Test
    public void testDeleteKey() {
        redisRepository.deleteKey(KEY);
        Values values = redisRepository.get(KEY);
        assertTrue(values == null || values.getValues() == null);
    }
}
