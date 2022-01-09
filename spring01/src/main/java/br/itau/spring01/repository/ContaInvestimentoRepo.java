package br.itau.spring01.repository;

import org.springframework.data.repository.CrudRepository;

import br.itau.spring01.model.ContaInvestimento;

public interface ContaInvestimentoRepo extends CrudRepository <ContaInvestimento, Long>{}
