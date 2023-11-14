package senai.att.att.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import senai.att.att.model.Fabricante;
import senai.att.att.model.Produto;
import senai.att.att.service.FabricanteService;
import senai.att.att.service.ProdutoService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/fabricante")
@AllArgsConstructor
public class FabricanteController {
    FabricanteService fabricanteService;

    @GetMapping("/todos")
    public ResponseEntity<List<Fabricante>> buscarTodos(){
        try{
            return new ResponseEntity<>(fabricanteService.buscarTodos(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Fabricante> buscarUm(@RequestParam Integer id){
        try{
            return new ResponseEntity<>(fabricanteService.buscarUm(id), HttpStatus.OK);
        }
        catch (NoSuchElementException a){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Fabricante fabricante){
        try {
            fabricanteService.salvar(fabricante);
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
    public ResponseEntity<String> update(@RequestBody Fabricante fabricante, @RequestParam Integer id){
        try {
            fabricanteService.update(fabricante, id);
            return ResponseEntity.status(HttpStatus.OK).body(fabricante.toString());
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
            fabricanteService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deletado Com Sucesso!");
        }
        catch (Exception d){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.!");
        }
    }
}
