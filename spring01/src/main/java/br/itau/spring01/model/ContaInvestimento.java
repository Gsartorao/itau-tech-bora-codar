package br.itau.spring01.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.itau.spring01.model.dto.AbrirConta;
@Entity
public class ContaInvestimento extends ContaBancaria {
    public ContaInvestimento (){}

    private static double taxaOperacao = 0.2; 
    /*@ManyToOne 
    @JoinColumn(name = "numero")
    @JsonIgnoreProperties("conta_investimento")*/ 

    public ContaInvestimento(AbrirConta novaConta, Cliente clienteEncontrado) {
        super(novaConta, clienteEncontrado);
        }




    @Override
    public String toString() {
        return "CP: " + super.toString() + " taxa: " + taxaOperacao;
    }

}
