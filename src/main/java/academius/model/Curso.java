package academius.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data // Gera getters, setters, toString, etc.
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@Entity // Define a classe como uma entidade JPA
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Atributo id

    private String nome; // Atributo nome
    private int cargaHoraria; // Atributo cargaHoraria

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relacionamento 1:N com Disciplina
    private Set<Disciplina> disciplinas;
}