package com.pr;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class LocalDateTimeViewer {
    @JsonFormat(pattern = "yyyy:MM:dd'##':HH:mm:ss:SSS")
    private LocalDateTime dateTime;

    public LocalDateTimeViewer(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String format() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(this);
    }
}
