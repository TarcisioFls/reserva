package br.com.grupo27.tech.challenge.reservation.application.controllers.owner.request;

import br.com.grupo27.tech.challenge.reservation.application.controllers.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateOwnerRequest extends UserResponse {}
