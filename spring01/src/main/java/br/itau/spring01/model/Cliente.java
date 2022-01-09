package br.itau.spring01.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.itau.spring01.model.dto.CadastroCliente;

@Entity // indica que a classe sera armazenada na tabela
@Table(name = "cliente") //nome da tabela no banco de dados
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id será gerado sequencialmente de forma automatica
    private long cod;

        
    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 15, nullable = false, unique = true)
    private int cpf;

    @Column(name = "email", length = 50, nullable = false)
    private String email ;

    @Column(name = "fone", length = 50, nullable = false)
    private String telefone ;

    @OneToMany(mappedBy = "owner")  // Um Cliente pode ter vários veiculos 
    // owner = atributo do veiculo que faz o relacionamento
    @JsonIgnoreProperties("owner")  // quando buscar os veículos, não traga os proprietários
    
   

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return telefone;
    }

    public void setFone(String fone) {
        this.telefone = fone;
    }

    public Cliente (){}
    public Cliente (CadastroCliente cliente){
        cpf = cliente.cpf;
        telefone = cliente.telefone;
        nome = cliente.nome;
        email = cliente.email;        
        
    }
    

    
    

}