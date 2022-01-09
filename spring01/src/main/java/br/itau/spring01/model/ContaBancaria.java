package br.itau.spring01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.itau.spring01.model.dto.AbrirConta;

@Entity
@Table( name = "conta_bancaria")

public abstract class ContaBancaria { // classe abstrata = modelo para outras classes, não pode instanciar objetos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cod;


    @Column(name = "numero")
    private int numero;
    @Column(name = "agencia")
    private int agencia;
    @Column(name = "saldo", nullable = false)
    private double saldo;

    @ManyToOne 
    @JoinColumn(name = "cod_cliente")
    @JsonIgnoreProperties("conta_bancaria") 
    private Cliente owner; 

    public ContaBancaria(){}
    public ContaBancaria(AbrirConta novaConta, Cliente clienteEncontrado) {
        
        this.agencia = novaConta.agencia;
        this.owner = clienteEncontrado;
        this.numero = novaConta.numeroConta;
       
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean deposito(double valor) {
        if (valor > 0) {
            saldo += valor; // saldo = saldo + valor;
            return true;
        }
        return false;
    }

    public boolean saque(double valor) {
        if (valor > 0) {
            saldo -= valor; // saldo = saldo - valor;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return numero + ": " + String.format("R$ %.2f", saldo);
    }
    
}
