package br.itau.spring01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itau.spring01.model.Cliente;
import br.itau.spring01.model.Emprestimo;
import br.itau.spring01.model.dto.SolicitarEmprestimo;
import br.itau.spring01.repository.ClienteRepo;
import br.itau.spring01.repository.EmprestimoRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired 

    private EmprestimoRepo repoemprestimo;
    
    @Autowired
    private ClienteRepo repocliente;


    @GetMapping
    public List<Emprestimo> listarTodos(){
        List<Emprestimo> lista = (List<Emprestimo>) repoemprestimo.findAll();

        return lista;
        
    }

  
    /*@PostMapping ("/solicitarEmprestimo/{emprestimo}")
    public Boolean emprestimo (@PathVariable long solicitarEmprestimo){
        double valorSolicitado = 3000;
        double valorTotalEmpréstimo = valorSolicitado + valorSolicitado * 0.2;
        int parcelas = 18;
        double valorParcelas = 200;
        Emprestimo cliente = repo.findByEmail(String email).orElse(null);
        return Cartao.transacaoCompra(valorCompra, cartao);

    }*/ 

    @PostMapping("/solicitaremprestimo")
    public ResponseEntity<Emprestimo> solicitarEmprestimo(@RequestBody SolicitarEmprestimo novoEmprestimo) {
        Cliente clienteEncontrado = repocliente.findByCpf(novoEmprestimo.cpf);

        if (clienteEncontrado != null) { // achou o cliente no BD
            //return ResponseEntity.ok(clienteEncontrado); // retorna o cliente com status 200

            Emprestimo emprestimo = new Emprestimo(novoEmprestimo, clienteEncontrado);
            emprestimo.aprovacaoEmprestimo(emprestimo);
            repoemprestimo.save(emprestimo);

             return ResponseEntity.ok(emprestimo);
        }

        return ResponseEntity.notFound().build();
    }

    }




   

    
    
