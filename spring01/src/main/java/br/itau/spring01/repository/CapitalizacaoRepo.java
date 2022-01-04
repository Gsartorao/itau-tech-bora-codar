package br.itau.spring01.repository;

import org.springframework.data.repository.CrudRepository;

import br.itau.spring01.model.Capitalizacao;

public interface CapitalizacaoRepo extends CrudRepository <Capitalizacao, Long> {
    
}