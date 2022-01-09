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

import br.itau.spring01.model.dto.SolicitaCapitalizacao;

@Entity
@Table(name = "Capitalizacao")
public class Capitalizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cod;

    @Column(name = "numero_titulo", length = 100, nullable = false)
    private String numeroTitulo;

    @ManyToOne
    @JoinColumn(name = "cod_cliente")
    private Cliente cliente;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    @Column(name = "dia_debito")
    private int diaDebito;

    @Column(name = "valor_total_pago")
    private double valorTotalPago;

    @Column(name = "valor_aporte_mes")
    private double valorAporteMes;

    @Column(name = "numero_sorte", length = 5, nullable = false, unique = true)
    private String numeroSorte;

    @Column(name = "correcao")
    private double correcao;

    @Column(name = "valor_multa")
    private double valorMulta;

    public Capitalizacao(SolicitaCapitalizacao capitalizacao, Cliente cliente) {

        numeroTitulo = "1255";
        this.cliente = cliente;     
        dataInicio = capitalizacao.dataInicio;
        dataFim = capitalizacao.dataFim;
        diaDebito = capitalizacao.diaDebito;
        valorTotalPago = capitalizacao.valorAporteMes;
        valorAporteMes = capitalizacao.valorAporteMes;
        numeroSorte = "158";
        correcao = 0.1;
        valorMulta = 0.80;

    }

    public Capitalizacao Resgate(Capitalizacao cap)
    {
        //if( dataFim )

        return cap;
    }


    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getNumeroTitulo() {
        return numeroTitulo;
    }

    public void setNumeroTitulo(String numeroTitulo) {
        numeroTitulo = numeroTitulo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getDiaDebito() {
        return diaDebito;
    }

    public void setDiaDebito(int diaDebito) {

        if (diaDebito > 27) {
            diaDebito = 1;
        }

        this.diaDebito = diaDebito;
    }

    public double getValorTotalPago() {
        return valorTotalPago;
    }

    public void setValorTotalPago(double valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }

    public double getValorAporteMes() {
        return valorAporteMes;
    }

    public void setValorAporteMes(double valorAporteMes) {
        this.valorAporteMes = valorAporteMes;
    }

    public String getNumeroSorte() {
        return numeroSorte;
    }

    public void setNumeroSorte(String numeroSorte) {
        this.numeroSorte = numeroSorte;
    }

    public double getCorrecao() {
        return correcao;
    }

    public void setCorrecao(double correcao) {
        this.correcao = correcao;
    }

    public double getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(double valorMulta) {
        this.valorMulta = valorMulta;
    }

}
