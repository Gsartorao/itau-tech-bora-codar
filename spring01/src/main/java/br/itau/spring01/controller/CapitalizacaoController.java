package br.itau.spring01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itau.spring01.model.Capitalizacao;
import br.itau.spring01.repository.CapitalizacaoRepo;


@RestController
@CrossOrigin("*")
@RequestMapping("/Capitalizacao")
public class CapitalizacaoController {

    @Autowired
    private CapitalizacaoRepo repo;
   
    @GetMapping //("/all")
    public List<Capitalizacao> listarVeiculos(){
         List<Capitalizacao> list = (List<Capitalizacao>)repo.findAll();
    return list ;

    } 
}
   
