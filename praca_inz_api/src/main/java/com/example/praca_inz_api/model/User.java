package com.example.praca_inz_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User {

    @Id
    private String _id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;

    @DBRef
    private List<DaySchedule> dayScheduleList = new ArrayList<>();

    @DBRef
    private List<Food> myFood = new ArrayList<>();

    @DBRef
    private List<Contact> myContact = new ArrayList<>();

    @DBRef
    private List<Allergies> myAllergies = new ArrayList<>();

}
