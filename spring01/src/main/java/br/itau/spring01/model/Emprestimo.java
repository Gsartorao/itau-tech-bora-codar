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
    
     
    @Column(name = "quantidade_parcelas", length = 2, nullable = false)
    private int quantidadeParcelas;
    
    @Column(name = "aprovado", length = 1, nullable = false)
    private boolean aprovado;
    
    @Column(name = "parcela", length = 10, nullable = false)
    private double parcela;
    
    @Column(name = "saldo_devedor", length = 10, nullable = false)
    private double saldoDevedor;

    @Column(name = "parcelas_pagas", length = 2, nullable = false)
    private double parcelasPagas;

    @Column(name = "juros", length = 4, nullable = false)
    private double juros;


    @ManyToOne 
    @JoinColumn(name = "cod_cliente")
    @JsonIgnoreProperties("emprestimo") 
    private Cliente owner; 

    public Emprestimo(){
        
    }

    public Emprestimo(SolicitarEmprestimo novoEmprestimo, Cliente cliente) {
        this.renda = novoEmprestimo.renda;
        this.valorDisponibilizado = novoEmprestimo.valorSolicitado;
        this.quantidadeParcelas = novoEmprestimo.quantidadeParcela;
        this.owner = cliente;
        this.juros = 1.5;
    }

  

    public long getCod() {
        return cod;
    }


    public void setCod(long cod) {
        this.cod = cod;
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
  


    /*public boolean getAprovado() {
        return aprovado;
    }


    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }*/


    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

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
        emprestimo.totalEmprestimo = valorTotalEmprestimo(emprestimo.valorDisponibilizado, emprestimo.juros);
        emprestimo.parcela = valorParcela(emprestimo.totalEmprestimo, emprestimo.quantidadeParcelas);
        if(emprestimo.parcela < limiteParcela() && emprestimo.valorDisponibilizado < emprestimo.limiteEmprestimo){
        emprestimo.aprovado = true;
        emprestimo.saldoDevedor = emprestimo.totalEmprestimo;   
        } 
        else{
            emprestimo.aprovado = false;
            emprestimo.saldoDevedor = 0;
        }
        return emprestimo;
        } 
       
        public double limiteParcela(){
            return renda * 0.30;
        }

        public double valorParcela(double valorTotalEmprestimo, int quantidadeParcelas ){
           return valorTotalEmprestimo / quantidadeParcelas;
        }

        public double valorTotalEmprestimo(double valorSolicitado, double juros){
            return valorSolicitado * juros;
        }

        public static Emprestimo pagamentoParcela(Emprestimo emprestimo, double valor){
            emprestimo.saldoDevedor = emprestimo.saldoDevedor - valor;
            emprestimo.parcelasPagas += 1;
            return emprestimo;

        }


              
                
    }

   


