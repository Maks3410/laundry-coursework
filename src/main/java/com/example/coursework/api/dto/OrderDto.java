package com.example.coursework.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    @NonNull
    LocalDate date;
    @NonNull
    Double weight;
    @NonNull
    Boolean dry;
    @NonNull
    Double price;
}
