package br.itau.spring01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itau.spring01.model.Cliente;
import br.itau.spring01.model.dto.CadastroCliente;
import br.itau.spring01.repository.ClienteRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired // injeção de dependência = cria classe, escreve os métodos, e cria um objeto
               // para uso
    private ClienteRepo repo;

    @GetMapping("/todos")
    public List<Cliente> listarTodos() {
        List<Cliente> list = (List<Cliente>) repo.findAll(); // findAll retorna todos os itens no BD

        return list;
    }

    @GetMapping("/list")
    public Page<Cliente> listarTodosPaginado(Pageable pageable) {
        Page<Cliente> list = repo.findAll(pageable); // findAll retorna todos os itens no BD

        return list;
    }

    @GetMapping("/{codigo}") // {indica uma variável}
    public ResponseEntity<Cliente> buscarCliente(@PathVariable long codigo) {
        // busca um cliente, e se não encontrar retorna null
        Cliente clienteFound = repo.findById(codigo).orElse(null);

        if (clienteFound != null) { // achou o cliente no BD
            return ResponseEntity.ok(clienteFound); // retorna o cliente com status 200
        }

        return ResponseEntity.notFound().build(); // retorna status 404 sem corpo (cliente)
    }

    @PostMapping
    public ResponseEntity<Cliente> inserirCliente(@RequestBody CadastroCliente cliente) {
        Cliente newCliente = new Cliente (cliente);
         repo.save(newCliente); 

        return ResponseEntity.ok(newCliente);
    }

    @PutMapping
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente) {
        if(cliente.getCod() > 0){
            Cliente newCliente = repo.save(cliente);

            return ResponseEntity.ok(newCliente);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> apagarCliente(@PathVariable long codigo) {

        // antes de apagar, verifica se este cliente existe
        Cliente clienteEncontrado = repo.findById(codigo).orElse(null);

        if (clienteEncontrado != null) { // achou o cliente no BD
            try{
                repo.deleteById(codigo);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }

        

        return ResponseEntity.notFound().build();

    }

    @PostMapping("/email")
    public ResponseEntity<Cliente> buscarPorEmail(@RequestBody Cliente cliente) {
        Cliente clienteEncontrado = repo.findByEmail(cliente.getEmail());

        if (clienteEncontrado != null) { // achou o cliente no BD
            return ResponseEntity.ok(clienteEncontrado); // retorna o cliente com status 200
        }

        return ResponseEntity.notFound().build();
    }
    
}
