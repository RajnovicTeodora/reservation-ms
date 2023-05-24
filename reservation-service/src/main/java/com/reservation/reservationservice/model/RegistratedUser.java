package com.reservation.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "registrated_user")
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Data
public class RegistratedUser extends UnregistratedUser{

    private String username;
    private String password;
    private Boolean isDeleted;
    private Boolean notificationOn;
}
