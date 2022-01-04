package br.itau.spring01.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity // indica que a classe sera armazenada na tabela
@Table(name = "cartao") //nome da tabela no banco de dados

public class Cartao {
    /*@Id // indica que este campo será chave primária (pk)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id será gerado sequencialmente de forma automatica
    private long cod;
*/
    @Column(name = "bandeira", length = 15, nullable = false)
    private String bandeira;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "numero_do_cartao", nullable = false, unique = true)
    private long numeroCartao;

    @Column(name = "nome_no_cartao", length = 200, nullable = false)
    private String nameNoCartao;
    
    @Column(name = "data_validade", length = 10, nullable = false)
    private Date dataValidade;

    @Column(name = "limite", nullable = false)
    private double limite;

    @Column(name = "saldo", nullable = false)
    private double saldo;


    @ManyToOne // N:1 =  cada cartão tem um proprietario, mas um proprietario pode ter vários cartoes
    @JoinColumn(name = "cod_cliente")
    @JsonIgnoreProperties("cartao") // para cada proprietario, não traga a lista de veículos
    private Cliente owner; // owner = proprietario
/*
    public long getCod() {  
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }
*/
    public Cliente getOwner() {
        return owner;
    }

    public void setOwner(Cliente owner) {
        this.owner = owner;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public long getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(long numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNameNoCartao() {
        return nameNoCartao;
    }

    public void setNameNoCartao(String nameNoCartao) {
        this.nameNoCartao = nameNoCartao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public static boolean transacaoCompra(double transacaoDebito, Cartao cartaoTransacao) {
        double limiteDisponivel = cartaoTransacao.getLimite() - cartaoTransacao.getSaldo();
        if (limiteDisponivel > transacaoDebito){
            cartaoTransacao.setSaldo(cartaoTransacao.getSaldo() + transacaoDebito); // criar no futuro função atualizar saldo banco
           // cartaoTransacao.saldo=cartaoTransacao.saldo + transacaoDebito; 
            return true;

        }
         
               

        return false;
    }

    /*

    public boolean pagamentoFatura(double transacaoDebito, double transacaoCompra) {
        if (limiteInicial > transacaoDebito){
        limiteInicial -= transacaoCompra;
        return true;
        }

        return false;
    }
    */
    
}
