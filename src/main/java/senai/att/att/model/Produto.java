package senai.att.att.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Min(0)
    private Double preco;
    @Min(0)
    private Integer estoque;
    @Column(nullable = false)
    private String dataValidade;
    private String descricao;
    @Column(unique = true, nullable = false)
    private String codigoDeBarra;
    @Min(0)
    private Double pesoProduto;
    @Min(0)
    private Double medidaProduto;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Fabricante fabricante;
    @Column(nullable = false)
    @ManyToMany(mappedBy = "produtos", cascade = CascadeType.PERSIST)
    private List<Categoria> categoria;
}
