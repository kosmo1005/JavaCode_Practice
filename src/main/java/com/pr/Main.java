package com.pr;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        LocalDateTimeViewer localDateTimeViewer = new LocalDateTimeViewer(LocalDateTime.of
                (2025,2,20,17,8,34, 3_000_000));
        System.out.println(localDateTimeViewer.format());

    }
}

