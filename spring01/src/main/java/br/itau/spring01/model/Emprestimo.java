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

import br.itau.spring01.model.dto.SolicitarEmprestimo;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {
   private double limiteEmprestimo = 5000;
    
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cod;
    
    @Column(name = "renda", length = 10, nullable = false)
    private double renda;
    
    @Column(name = "valor_disponibilizado", length = 10, nullable = false )
    private double valorDisponibilizado;
    
    @Column(name = "total_emprestimo", length = 10, nullable = false)
    private double totalEmprestimo = valorDisponibilizado * 0.2;
    
    @Column(name = "limite_parcela", length = 10, nullable = false)
    private double limiteParcela;
    
    @Column(name = "prazo", length = 2, nullable = false)
    private int prazo;
    
    @Column(name = "aprovado", length = 1, nullable = false)
    private boolean aprovado;
    
    @Column(name = "parcela", length = 10, nullable = false)
    private double parcela;
    
    @Column(name = "saldo_devedor", length = 10, nullable = false)
    private double saldoDevedor;


    @ManyToOne 
    @JoinColumn(name = "cod_cliente")
    @JsonIgnoreProperties("emprestimo") 
    private Cliente owner; 

    public Emprestimo(){
        
    }

    public Emprestimo(SolicitarEmprestimo novoEmprestimo, Cliente cliente) {
        this.renda = novoEmprestimo.renda;
        this.valorDisponibilizado = novoEmprestimo.valorSolicitado;
        this.prazo = novoEmprestimo.quantidadeParcela;
        this.owner = cliente;
    }


    public long getCod() {
        return cod;
    }


    public void setCod(long cod) {
        this.cod = cod;
    }


    public int getNumeroEmprestimo() {
        return numeroEmprestimo;
    }


    public void setNumeroEmprestimo(int numeroEmprestimo) {
        this.numeroEmprestimo = numeroEmprestimo;
    }


    public double getRenda() {
        return renda;
    }


    public void setRenda(double renda) {
        this.renda = renda;
    }


    public double getValorDispinobilizado() {
        return valorDisponibilizado;
    }


    public void setValorDispinobilizado(double valorDispinobilizado) {
        this.valorDisponibilizado = valorDispinobilizado;
    }


    public double getTotalEmprestimo() {
        return totalEmprestimo;
    }


    public void setTotalEmprestimo(double totalEmprestimo) {
        this.totalEmprestimo = totalEmprestimo;
    }



    public double getLimiteParcela() {
        return limiteParcela;
    }


    public void setLimiteParcela(double limiteParcela) {
        this.limiteParcela = limiteParcela;
    }


    public int getPrazo() {
        return prazo;
    }


    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }


    /*public boolean getAprovado() {
        return aprovado;
    }


    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }*/


    public double getParcela() {
        return parcela;
    }


    public void setParcela(double parcela) {
        this.parcela = parcela;
    }


    public double getSaldoDevedor() {
        return saldoDevedor;
    }


    public void setSaldoDevedor(double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }


    public Cliente getOwner() {
        return owner;
    }


    public void setOwner(Cliente owner) {
        this.owner = owner;
    }

    public Emprestimo aprovacaoEmprestimo(Emprestimo emprestimo) {
        //construir lógica 
        emprestimo.parcela = valorParcela(valorTotalEmprestimo(emprestimo.valorDisponibilizado), emprestimo.prazo);
        if(emprestimo.parcela < limiteParcela())
        emprestimo.aprovado = true;
        return emprestimo;
        }
       
        public double limiteParcela(){
            return renda * 0.30;
        }

        public double valorParcela(double valorTotalEmprestimo, int quantidadeParcelas ){
           return valorTotalEmprestimo / quantidadeParcelas;
        }

        public double valorTotalEmprestimo(double valorSolicitado){
            return valorSolicitado * 1.5;
        }
        
    }










    /*public void valorDispinobilizado(double valorDisponibilizado, double saldoConta){
        
        saldoConta = saldoConta + valorDisponibilizado;
            

    }

    public void valorTotalEmprestimo(double emprestimoTotal){
        get.valorDisponibilizado();
    
    }

    public double saldoDevedor(double saldo, double totalEmprestimo, double parcelasPagas){
    return saldo = totalEmprestimo - parcelasPagas;
    }

    public double limiteParcela(){
            return renda * 0.30;
    }

    public double valorParcela(double valor, double valorTotalEmprestimo){
        valor = valorTotalEmprestimo / parcela;
    }*/

    




