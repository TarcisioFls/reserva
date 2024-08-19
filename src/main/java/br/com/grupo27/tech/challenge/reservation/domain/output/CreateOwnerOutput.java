package br.com.grupo27.tech.challenge.reservation.domain.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOwnerOutput {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;

}
