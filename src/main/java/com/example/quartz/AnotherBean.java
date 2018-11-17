package com.example.quartz;

import com.example.Util;
import org.springframework.stereotype.Component;

@Component("anotherBean")
public class AnotherBean {
    public void printAnotherMessage() {
        System.out.println("AnotherBean Executes: " + Util.formatNow());
    }
}