package com.example.logging.libs.test;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    public String tryLog(String inputString){
        return inputString.substring(2);
    }
}
