package br.com.grupo27.tech.challenge.reservation.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOwnerInput {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;

}
