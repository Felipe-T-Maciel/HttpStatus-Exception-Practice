package senai.att.att.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.att.att.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> { }
