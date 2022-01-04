package br.itau.spring01.repository;

import org.springframework.data.repository.CrudRepository;

import br.itau.spring01.model.Emprestimo;

public interface EmprestimoRepo extends CrudRepository<Emprestimo, Long> {
    
}
