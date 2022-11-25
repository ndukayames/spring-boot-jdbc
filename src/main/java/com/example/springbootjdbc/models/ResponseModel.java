package com.example.springbootjdbc.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
//@Builder
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {
    @NonNull
    private Boolean success;

    private String message;

    public ResponseModel(Boolean success) {
        this.success = success;
    }
}
