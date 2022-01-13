package com.example.praca_inz_api.dto;

import com.example.praca_inz_api.model.Contact;
import com.example.praca_inz_api.model.DaySchedule;
import com.example.praca_inz_api.model.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String username;
    private String password;
    private String email;
    private Long phoneNumber;

}
