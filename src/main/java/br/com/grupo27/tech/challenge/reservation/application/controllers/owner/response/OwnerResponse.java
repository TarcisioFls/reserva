package br.com.grupo27.tech.challenge.reservation.application.controllers.owner.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerResponse {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;

}
