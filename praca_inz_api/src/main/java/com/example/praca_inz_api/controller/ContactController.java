package com.example.praca_inz_api.controller;


import com.example.praca_inz_api.converter.ContactConverter;
import com.example.praca_inz_api.dto.AddContactDTO;
import com.example.praca_inz_api.dto.ContactDTO;
import com.example.praca_inz_api.model.Contact;
import com.example.praca_inz_api.repository.ContactRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    @Autowired
    private ContactRepo contactRepo;


    @GetMapping("/type")
    public List<AddContactDTO> getFoodType(@RequestParam(value = "type") String type){
        return contactRepo.getAllType(type).stream()
                .map(ContactConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddContactDTO> getFoodById(@PathVariable String id){
        return ResponseEntity.ok().body(ContactConverter.toDTO(contactRepo.getContactById(id)));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<AddContactDTO> addContactToUser(@RequestBody ContactDTO contactDTO){
        Contact contact = contactRepo.addContactToUser(contactDTO);
        if (contact==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok().body(ContactConverter.toDTO(contact));
    }

    @DeleteMapping("/delete")
    public void deleteContactByIdFromUser(@RequestParam(value = "contactId") String contactId,
                                                   @RequestParam(value = "username") String username){
        contactRepo.deleteContactById(contactId, username);
    }
}
