package senai.att.att.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senai.att.att.model.Produto;
import senai.att.att.repository.ProdutoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {
    ProdutoRepository produtoRepository;

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarUm(Integer id) {
        return produtoRepository.findById(id).get();
    }

    public ResponseEntity<?> salvar(Produto produto) {
        if(verificaProduto(produto) && verificaSeProdutoJaExiste(produto)){
            produtoRepository.save(produto);
            return ResponseEntity.status(HttpStatus.OK).body("Criado com sucesso!");
        }else if (!verificaSeProdutoJaExiste(produto)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto já existe!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos ou falta de informações!");
    }

    private boolean verificaProduto(Produto produto){
        return produto.getPesoProduto() > 0
                && produto.getMedidaProduto() > 0
                && produto.getCodigoDeBarra() != null
                && produto.getEstoque() > 0
                && produto.getPreco() > 0
                && produto.getNome() != null
                && produto.getDataValidade() != null;
    }

    private boolean verificaSeProdutoJaExiste(Produto produto) {
        for (Produto prod: produtoRepository.findAll()) {
            if(prod.getNome().equals(produto.getNome())){
                return false;
            }
        }
        return true;
    }

    public void update(Produto produto, Integer id) {
        Produto produtoAtt = produtoRepository.findById(id).get();
        produto.setId(produtoAtt.getId());
        if(verificaProduto(produto)){
            produtoRepository.deleteById(id);
            salvar(produto);
        }
    }

    public void delete(Integer id) {
        produtoRepository.deleteById(id);
    }
}
