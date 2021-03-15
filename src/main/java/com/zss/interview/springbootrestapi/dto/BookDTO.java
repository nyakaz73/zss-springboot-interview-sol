package com.zss.interview.springbootrestapi.dto;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Long categoryId;
}
