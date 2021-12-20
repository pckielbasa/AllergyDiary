package com.example.praca_inz_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnackDTO {

    private String snackName;
    private String commentAlertAllergies;
    private String type = "snack";
    private Boolean favourite;

}