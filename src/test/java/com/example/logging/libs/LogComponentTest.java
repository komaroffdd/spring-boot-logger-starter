package com.example.logging.libs;

import com.example.logging.libs.test.SimpleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogComponentTest {
    @Autowired
    private SimpleService simpleService;


    @Test
    void test_log_with_correct_string_the_log_before_and_after_execute(){
        String string = simpleService.tryLog("string");
        Assertions.assertEquals("ring",string);
    }
}
