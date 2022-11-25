package com.example.springbootjdbc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWithDataModel<T> extends ResponseModel{
    private T result;

    public ResponseWithDataModel(Boolean success, T result) {
        super(success);
        this.result = result;
    }
}
