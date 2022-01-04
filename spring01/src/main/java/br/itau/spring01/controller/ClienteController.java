package br.itau.spring01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import br.itau.spring01.model.Cliente;
import br.itau.spring01.repository.ClienteRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired // injeção de dependência = cria classe, escreve os métodos, e cria um objeto para uso
    private ClienteRepo repo;

    @GetMapping("/all")
    public List<Cliente> listarTodos(){
        List<Cliente> lista= (List<Cliente>) repo.findAll(); // retorna todos os itens no BD.

        return lista;
        
    }
    
}