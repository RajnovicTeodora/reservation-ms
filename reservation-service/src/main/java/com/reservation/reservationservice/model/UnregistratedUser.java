package com.reservation.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "unregistrated_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UnregistratedUser {

    private String email;
    private String name;
    private String lastNema;
}
