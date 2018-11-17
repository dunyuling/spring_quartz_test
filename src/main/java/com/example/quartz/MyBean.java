package com.example.quartz;

import com.example.Util;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    public void printMessage() {
        System.out.println("MyBean Executes: " + Util.formatNow());
    }
}
