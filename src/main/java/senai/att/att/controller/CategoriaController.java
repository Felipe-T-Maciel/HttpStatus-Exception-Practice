package senai.att.att.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import senai.att.att.model.Categoria;
import senai.att.att.model.Produto;
import senai.att.att.service.CategoriaService;
import senai.att.att.service.ProdutoService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/categoria")
@AllArgsConstructor
public class CategoriaController {
    CategoriaService categoriaService;

    @GetMapping("/todos")
    public ResponseEntity<List<Categoria>> buscarTodos(){
        try{
            return new ResponseEntity<>(categoriaService.buscarTodos(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Categoria> buscarUm(@RequestParam Integer id){
        try{
            return new ResponseEntity<>(categoriaService.buscarUm(id), HttpStatus.OK);
        }
        catch (NoSuchElementException a){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Categoria categoria){
        try {
            categoriaService.salvar(categoria);
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
    public ResponseEntity<String> update(@RequestBody Categoria categoria, @RequestParam Integer id){
        try {
            categoriaService.update(categoria, id);
            return ResponseEntity.status(HttpStatus.OK).body(categoria.toString());
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
            categoriaService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deletado Com Sucesso!");
        }
        catch (Exception d){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.!");
        }
    }
}
