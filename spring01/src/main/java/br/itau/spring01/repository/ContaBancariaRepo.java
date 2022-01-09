package br.itau.spring01.repository;

import org.springframework.data.repository.CrudRepository;

import br.itau.spring01.model.ContaBancaria;

public interface ContaBancariaRepo extends CrudRepository <ContaBancaria, Long>{
    public ContaBancaria findByCodigo(int codigo);
}
    

