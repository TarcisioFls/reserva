package br.com.grupo27.tech.challenge.reservation.infra.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "owners")
public class OwnerModel {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;

}
