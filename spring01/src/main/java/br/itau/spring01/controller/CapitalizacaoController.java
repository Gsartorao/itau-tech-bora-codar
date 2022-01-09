package br.itau.spring01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itau.spring01.model.Capitalizacao;
import br.itau.spring01.model.Cliente;
import br.itau.spring01.model.dto.SolicitaCapitalizacao;
import br.itau.spring01.repository.CapitalizacaoRepo;
import br.itau.spring01.repository.ClienteRepo;


@RestController
@CrossOrigin("*")
@RequestMapping("/Capitalizacao")
public class CapitalizacaoController {

    @Autowired
    private CapitalizacaoRepo repoCapitalizacao;
    
    @Autowired
    private ClienteRepo repocliente;
   
    @GetMapping //("/all")
    public List<Capitalizacao> listarVeiculos(){
         List<Capitalizacao> list = (List<Capitalizacao>) repoCapitalizacao.findAll();
    return list ;

    }
    
    
    @PostMapping("/solicitarCapitalizacao")
    public ResponseEntity<Capitalizacao> solicitarCapitalizacao(@RequestBody SolicitaCapitalizacao novaCap) {
        
        Cliente clienteEncontrado = repocliente.findByCpf(novaCap.cpf);

        if (clienteEncontrado != null) { // achou o cliente no BD
            //return ResponseEntity.ok(clienteEncontrado); // retorna o cliente com status 200

            Capitalizacao capitalizacao = new Capitalizacao(novaCap, clienteEncontrado);     
            repoCapitalizacao.save(capitalizacao); 

             return ResponseEntity.ok(capitalizacao);
        }

        return ResponseEntity.notFound().build();
    }


   /* @PostMapping("/aporte")
    public ResponseEntity<Capitalizacao> aporte(@RequestBody SolicitaCapitalizacao novaCap) {
        
      Capitalizacao  CapitalizacaoEncontrada = repoCapitalizacao.findBynumeroTitulo(novaCap.nu);

        if (CapitalizacaoEncontrada != null) { // achou o cliente no BD
            //return ResponseEntity.ok(clienteEncontrado); // retorna o cliente com status 200

            Capitalizacao capitalizacao = new Capitalizacao(novaCap, clienteEncontrado);     
           

             return ResponseEntity.ok(capitalizacao);
        }

        return ResponseEntity.notFound().build();
    }*/
}
   
