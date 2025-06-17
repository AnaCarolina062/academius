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
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Atributo id

    private String nome; // Atributo nome
    private String descricao; // Atributo descricao

    @ManyToOne // Relacionamento N:1 com Curso
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToMany // Relacionamento N:N com Professor
    @JoinTable( // Define a tabela de junção
            name = "disciplina_professor",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private Set<Professor> professores;
}