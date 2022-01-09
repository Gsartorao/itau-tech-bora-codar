package br.itau.spring01.repository;

import org.springframework.data.repository.CrudRepository;

import br.itau.spring01.model.ContaEspecial;

public interface ContaEspecialRepo extends CrudRepository <ContaEspecial, Long>{}
