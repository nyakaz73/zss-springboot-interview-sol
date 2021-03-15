package com.zss.interview.springbootrestapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponseDTO {

    //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private String updated;

    private String responseCode;
    private String responseDescription;
    private String reference;
    private String debitReference;
}
