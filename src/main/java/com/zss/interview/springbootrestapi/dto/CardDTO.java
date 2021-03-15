package com.zss.interview.springbootrestapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CardDTO {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiry;
}
