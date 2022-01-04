package br.itau.spring01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import br.itau.spring01.model.Cartao;
  
// <Classe, chavePrimaria>
// CRUD = create, read, update, delete
public interface CartaoRepo extends CrudRepository <Cartao, Long> {
    public Page<Cartao> findAll(Pageable pageable);

    //public String findBandeira(long numeroCartao);

    
}