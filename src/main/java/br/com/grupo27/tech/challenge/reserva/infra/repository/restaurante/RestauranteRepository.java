package br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante;

import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface RestauranteRepository extends MongoRepository<RestauranteModel, String> {

    @Query("{$or: [{nome: {$regex: ?0, $options: 'i'}}, {localizacao: {$regex: ?0, $options: 'i'}}, {tipoCozinhaList: {$regex: ?0, $options: 'i'}}]}")
    Optional<Page<RestauranteModel>> findByNomeOrLocalizacaoOrTipoCozinhaList(String seach, PageRequest pageRequest);


    void deleteByProprietarioId(String proprietarioId);
}
