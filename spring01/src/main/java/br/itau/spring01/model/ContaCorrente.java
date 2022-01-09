package br.itau.spring01.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.itau.spring01.model.dto.AbrirConta;

@Entity
public class ContaCorrente extends ContaBancaria {
    private final double TAXA_DEPOSITO = 0.1;
   /* @ManyToOne 
    @JoinColumn(name = "numero")
    @JsonIgnoreProperties("conta_corrente") 
    private ContaBancaria conta; */
    public ContaCorrente (){}

    
    public ContaCorrente(AbrirConta novaConta, Cliente clienteEncontrado) {
      super(novaConta, clienteEncontrado);
       
        
    }
    
    


    @Override
    public boolean saque(double valor) {
        if (valor <= getSaldo()) {
            return super.saque(valor);
        }
        return false; // não tem saldo suficiente na conta
    }

    @Override
    public boolean deposito(double valor) {
        return super.deposito(valor - TAXA_DEPOSITO);
    }

    @Override
    public String toString() {
        return "CC: " + super.toString();
    }

}
