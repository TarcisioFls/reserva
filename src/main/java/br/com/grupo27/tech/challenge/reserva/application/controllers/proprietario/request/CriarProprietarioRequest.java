package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request;

import br.com.grupo27.tech.challenge.reserva.application.controllers.request.UsuarioRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CriarProprietarioRequest extends UsuarioRequest {}
