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

@Entity
@Table(name = "emprestimo")
public class Emprestimo {
    public static double limiteEmprestimo = 0.35;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cod;
    

    @Column(name = "numero_do_emprestimo", length = 10, nullable = false)
    private int numeroEmprestimo;

    @Column(name = "renda", length = 10)
    private double renda;
    
    @Column(name = "valor_disponibilizado", length = 10)
    private double valorDispinobilizado;
    
    @Column(name = "total_emprestimo", length = 10)
    private double totalEmprestimo;
    
    @Column(name = "juros", length =  5)
    private double juros;
    
    @Column(name = "limite_parcela", length = 10)
    private double limiteParcela;
    
    @Column(name = "prazo", length = 2)
    private int prazo;
    
    @Column(name = "aprovado", length = 1)
    private boolean aprovado;
    
    @Column(name = "parcela", length = 10)
    private double parcela;
    
    @Column(name = "saldo_devedor", length = 10)
    private double saldoDevedor;


    @ManyToOne // N:1 =  cada veiculo de um proprietario, mas um proprietario pode ter vários veículos
    @JoinColumn(name = "cod_cliente")
    @JsonIgnoreProperties("emprestimo") // para cada proprietario, não traga a lista de veículos
    private Cliente owner; // owner = proprietario


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
        return valorDispinobilizado;
    }


    public void setValorDispinobilizado(double valorDispinobilizado) {
        this.valorDispinobilizado = valorDispinobilizado;
    }


    public double getTotalEmprestimo() {
        return totalEmprestimo;
    }


    public void setTotalEmprestimo(double totalEmprestimo) {
        this.totalEmprestimo = totalEmprestimo;
    }


    public double getJuros() {
        return juros;
    }


    public void setJuros(double juros) {
        this.juros = juros;
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


    public boolean isAprovado() {
        return aprovado;
    }


    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
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

        public double valorMaximoParcela(){
            return renda * limiteEmprestimo;
        }

    
    


    public boolean limiteParcela(double valor) {
        if(valor <= valorMaximoParcela()) {
            return true;
        }
        return false;
    }    
}    

