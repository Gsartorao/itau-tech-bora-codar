package br.itau.spring01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.itau.spring01.model.Cartao;
import br.itau.spring01.repository.CartaoRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired // injeção de dependência = cria classe, escreve os métodos, e cria um objeto para uso
    private CartaoRepo repo;

    @GetMapping
    public List<Cartao> listarTodos(){
        List<Cartao> lista = (List<Cartao>) repo.findAll(); // retorna todos os itens no BD.

        return lista;
        
    }

   /* @GetMapping ("/bandeira/{numeroCartao}")
    public String retornaBandeira(@PathVariable long numeroCartao) {
        String bandeira=(String) repo.findBandeira(numeroCartao);
        return bandeira;

    }*/

    @PostMapping ("/comprar/{numeroCartao}")
    public Boolean comprar (@PathVariable long numeroCartao){
        double valorCompra = 100;
        Cartao cartao = repo.findById(numeroCartao).orElse(null);
        return Cartao.transacaoCompra(valorCompra, cartao); 

    } 

    

    
       


    
}