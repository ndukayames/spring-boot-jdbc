package com.example.springbootjdbc.models;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTutorialModel {
    @Size(min = 2, message = "Tutorial title should be atleast 2 characters long.")
    private String title;
    private String description;
}
