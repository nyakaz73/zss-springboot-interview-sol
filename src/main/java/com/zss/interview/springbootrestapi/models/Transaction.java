package com.zss.interview.springbootrestapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zss.interview.springbootrestapi.dto.AdditionalDataDTO;
import com.zss.interview.springbootrestapi.dto.CardDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String type;
    private String extendedType;
    private BigDecimal amount;
    //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private String created;

    private String reference;
    private String narration;
    private Map<String, AdditionalDataDTO> additionalData;
    private CardDTO card;
}
