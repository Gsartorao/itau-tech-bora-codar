package br.itau.spring01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itau.spring01.model.Cliente;
import br.itau.spring01.model.ContaBancaria;
import br.itau.spring01.model.ContaCorrente;
import br.itau.spring01.model.ContaEspecial;
import br.itau.spring01.model.ContaInvestimento;

import br.itau.spring01.model.dto.AbrirConta;
import br.itau.spring01.model.dto.Movimento;
import br.itau.spring01.repository.ClienteRepo;
import br.itau.spring01.repository.ContaBancariaRepo;
import br.itau.spring01.repository.ContaCorrenteRepo;
import br.itau.spring01.repository.ContaEspecialRepo;
import br.itau.spring01.repository.ContaInvestimentoRepo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("*")
@RequestMapping("/contaBancaria")

public class ContaBancariaController {
    @Autowired
    private ClienteRepo repoCliente;

    @Autowired
    private ContaBancariaRepo repoContaBancaria;

    @Autowired
    private ContaCorrenteRepo repoContaCorrente;

    @Autowired
    private ContaEspecialRepo repoContaEspecial;

    @Autowired
    private ContaInvestimentoRepo repoContaInvestimento;
   
    @GetMapping("/todos")
    public List<ContaBancaria> listarTodos() {
        var list = (List<ContaBancaria>) repoContaBancaria.findAll(); // findAll retorna todos os itens no BD

        return list;
    }


@PostMapping("/depositar")//nome do endpoint
    // no response colocamos o que vamos retornar. -nome do metodo "Depositar". -no Request colocamos o que se espera receber no endpoint
    public ResponseEntity<ContaBancaria> depositar(@RequestBody Movimento deposito) {
      // vamos implementar a busca da conta a partir do código, vamos utilizar a seguinte sequencia: nomeclasse 
      // nome da classe    nome do objeto --  nome do repositorio --nome da funcao -parametro  
      ContaBancaria        conta =            repoContaBancaria    .findByCodigo    (deposito.codConta);

        if (conta != null){
            conta.deposito(deposito.valor);
            repoContaBancaria.save(conta);

        } return ResponseEntity.notFound().build();
    }

    @PostMapping("/sacar")//nome do endpoint
    // no response colocamos o que vamos retornar. -nome do metodo "Depositar". -no Request colocamos o que se espera receber no endpoint
    public ResponseEntity<ContaBancaria> sacar(@RequestBody Movimento movimento) {
      // vamos implementar a busca da conta a partir do código, vamos utilizar a seguinte sequencia: nomeclasse 
      // nome da classe    nome do objeto --  nome do repositorio --nome da funcao -parametro  
      ContaBancaria        conta =            repoContaBancaria    .findByCodigo    (movimento.codConta);

        if (conta != null){
            conta.saque(movimento.valor);
            repoContaBancaria.save(conta);

        } return ResponseEntity.notFound().build();
    }


@PostMapping("/abrirconta")
    public  ResponseEntity<ContaBancaria> abrirconta (@RequestBody AbrirConta novaConta){

        Cliente clienteEncontrado = repoCliente.findByCpf(novaConta.cpf);

        if (clienteEncontrado != null) { 
            
        ContaBancaria conta;   
        switch (novaConta.tipoConta){
            case 0:
            conta = new ContaCorrente(novaConta, clienteEncontrado);
            repoContaCorrente.save((ContaCorrente) conta); 
              return ResponseEntity.ok(conta);
               
            case 1:
                conta = new ContaEspecial(novaConta, clienteEncontrado);
                repoContaEspecial.save((ContaEspecial) conta); 
                return ResponseEntity.ok(conta);
                
            case 2:
                conta = new ContaInvestimento(novaConta, clienteEncontrado);
                repoContaInvestimento.save((ContaInvestimento) conta); 
                return ResponseEntity.ok(conta);
               
           default: return ResponseEntity.notFound().build();
        }
            

        
           
        }

        return ResponseEntity.notFound().build();
    }

}
