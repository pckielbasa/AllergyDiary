package com.example.praca_inz_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactListDTO {
    private String _id;
    private String contactName;
    private String composition;
    private String type;
    private Boolean favourite;
}
