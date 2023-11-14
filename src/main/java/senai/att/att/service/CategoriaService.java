package senai.att.att.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import senai.att.att.model.Categoria;
import senai.att.att.model.Fabricante;
import senai.att.att.repository.CategoriaRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {
    CategoriaRepository categoriaRepository;


    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarUm(Integer id) {
        return categoriaRepository.findById(id).get();
    }

    public void salvar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void update(Categoria categoria, Integer id) {
        Categoria categoria1 = categoriaRepository.findById(id).get();
        categoria.setId(categoria1.getId());
        categoriaRepository.deleteById(id);
        salvar(categoria);
    }

    public void delete(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
