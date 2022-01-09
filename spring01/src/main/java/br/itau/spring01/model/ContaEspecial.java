package br.itau.spring01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.itau.spring01.model.dto.AbrirConta;
@Entity
public class ContaEspecial extends ContaBancaria{
    @Column ( name = "limite", nullable = true)
    private double limite;
    
    public ContaEspecial(){}
    public ContaEspecial(AbrirConta novaConta, Cliente clienteEncontrado) {
        super(novaConta, clienteEncontrado);
        this.limite = novaConta.limite;
    }
    /*@ManyToOne 
    @JoinColumn(name = "numero")
    @JsonIgnoreProperties("conta_especial") 
    private ContaBancaria conta;*/
    
    
    @Override
    public boolean saque(double valor) {
        if(valor <= getSaldo() + limite) {
            return super.saque(valor);
        }
        return false; // não tem saldo+limite suficiente
    }

    @Override
    public String toString() {
        return "CE: " + super.toString() + " limite: " + limite;
    }
}

