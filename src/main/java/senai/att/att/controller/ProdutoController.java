package senai.att.att.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import senai.att.att.model.Produto;
import senai.att.att.service.ProdutoService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {
    ProdutoService produtoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Produto>> buscarTodos(){
        try{
            return new ResponseEntity<>(produtoService.buscarTodos(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Produto> buscarUm(@RequestParam Integer id){
        try{
            return new ResponseEntity<>(produtoService.buscarUm(id), HttpStatus.OK);
        }
        catch (NoSuchElementException a){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Produto produto){
        try {
            produtoService.salvar(produto);
            return ResponseEntity.status(HttpStatus.OK).body("Criado com sucesso!");
        }
        catch (NoSuchElementException a){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        catch (HttpClientErrorException b){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto já existe!");
        }
        catch (IllegalArgumentException c){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos ou falta de informações!");
        }
        catch (Exception d){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.!");
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Produto produto, @RequestParam Integer id){
        try {
            produtoService.update(produto, id);
            return ResponseEntity.status(HttpStatus.OK).body(produto.toString());
        }catch (NoSuchElementException a){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        catch (HttpClientErrorException b){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto já existe!");
        }
        catch (IllegalArgumentException c){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos ou falta de informações!");
        }
        catch (Exception d){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.!");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Integer id){
        try {
            produtoService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deletado Com Sucesso!");
        }
        catch (Exception d){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.!");
        }
    }
}
