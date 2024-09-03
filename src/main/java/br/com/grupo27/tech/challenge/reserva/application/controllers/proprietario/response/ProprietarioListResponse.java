package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response;

import br.com.grupo27.tech.challenge.reserva.application.controllers.response.PaginacaoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProprietarioListResponse extends PaginacaoResponse {

    private List<ProprietarioResponse> conteudo;

}
