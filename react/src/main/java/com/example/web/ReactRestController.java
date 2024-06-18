package com.example.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
public class ReactRestController {

    @PostMapping(value="/testData")
    public Map<Integer, String> testData(@RequestBody List<String>params) {
        log.info("작동은 합니까?");
        Map<Integer,String> data = new HashMap<>();
        data.put(1, "사과");
        data.put(2, "포도");
        data.put(3, "바나나");

        int i =4;
        for(String param : params){
            data.put(i,param);
            i++;
        }

        return data;
    }
}
