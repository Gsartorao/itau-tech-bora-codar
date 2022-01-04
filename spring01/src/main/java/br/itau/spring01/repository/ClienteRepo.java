package br.itau.spring01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import br.itau.spring01.model.Cliente;

// interface é "parecida" com uma classe abstrata, ou seja, é um modelo e não gera objetos.
// É usada para reduzir o forte acoplamento, que é gerado quando fazemos herança

// <Classe, ChavePrimária>
// CRUD = Create, Read, Update, Delete
public interface ClienteRepo extends CrudRepository<Cliente, Long> {
    public Cliente findByEmail(String email);
    public Page<Cliente> findAll(Pageable pageable); // modificação do findAll para ser paginado
}
