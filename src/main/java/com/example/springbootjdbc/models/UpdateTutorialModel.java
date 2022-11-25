package com.example.springbootjdbc.models;

import lombok.Data;

@Data
public class UpdateTutorialModel {
    private String title;
    private String description;
    private boolean published;
}
