package com.example.praca_inz_api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDayScheduleDTO {
    private String username;
    private String dayDate;

}
