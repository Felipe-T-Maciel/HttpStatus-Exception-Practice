package senai.att.att.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import senai.att.att.model.Fabricante;
import senai.att.att.model.Produto;
import senai.att.att.repository.FabricanteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FabricanteService {
    FabricanteRepository fabricanteRepository;

    public List<Fabricante> buscarTodos() {
        return fabricanteRepository.findAll();
    }

    public Fabricante buscarUm(Integer id) {
        return fabricanteRepository.findById(id).get();
    }

    public void salvar(Fabricante fabricante) {
        fabricanteRepository.save(fabricante);
    }

    public void update(Fabricante fabricante, Integer id) {
        Fabricante fabricante1 = fabricanteRepository.findById(id).get();
        fabricante.setId(fabricante1.getId());
        fabricanteRepository.deleteById(id);
        salvar(fabricante);
    }

    public void delete(Integer id) {
        fabricanteRepository.deleteById(id);
    }
}
