package br.com.grupo27.tech.challenge.reservation.domain.entity;

import br.com.grupo27.tech.challenge.reservation.infra.config.exception.ErrorCode;
import br.com.grupo27.tech.challenge.reservation.infra.config.exception.ExceptionAdvice;

public class Owner {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;

    public Owner() {}

    public Owner(String id, String name, String email, String password, String phone) {

        if (email.isBlank()) {
            throw new ExceptionAdvice(ErrorCode.EMAIL_IS_REQUIRED);
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public Owner setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Owner setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Owner setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Owner setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Owner setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
