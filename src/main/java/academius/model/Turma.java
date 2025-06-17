package academius.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera getters, setters, toString, etc.
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@Entity // Define a classe como uma entidade JPA
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Atributo id

    private String semestre; // Atributo semestre
    private String horario; // Atributo horario

    @ManyToOne // Relacionamento N:1 com Disciplina
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina; // Atributo disciplina

    @ManyToOne // Relacionamento N:1 com Professor
    @JoinColumn(name = "professor_id")
    private Professor professor; // Atributo professor
}