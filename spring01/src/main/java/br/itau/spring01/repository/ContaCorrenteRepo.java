package br.itau.spring01.repository;

import org.springframework.data.repository.CrudRepository;

import br.itau.spring01.model.ContaCorrente;

public interface ContaCorrenteRepo extends CrudRepository <ContaCorrente, Long>{}
