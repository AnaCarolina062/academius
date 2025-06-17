package academius.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe utilitária para gerenciar o EntityManagerFactory do JPA.
 * Garante que uma única instância do EntityManagerFactory seja criada.
 */
public class JPAUtil {

    // O nome da unidade de persistência definido no arquivo persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "crud-sistema-academico";

    // O objeto EntityManagerFactory que será compartilhado por toda a aplicação.
    // 'static' garante que seja uma única instância para a classe inteira.
    private static EntityManagerFactory factory;

    /**
     * Retorna a instância compartilhada do EntityManagerFactory.
     * Se a factory ainda não foi criada, ela é inicializada.
     * Este método garante que a operação pesada de criação da factory
     * ocorra apenas uma vez.
     *
     * @return A instância do EntityManagerFactory.
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        // Verifica se a factory ainda não foi inicializada (é nula)
        if (factory == null) {
            // Se for nula, cria a factory usando o nome da nossa unidade de persistência
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        // Retorna a factory (seja a recém-criada ou a que já existia)
        return factory;
    }

    /**
     * Fecha o EntityManagerFactory para liberar os recursos do banco de dados.
     * Este método deve ser chamado quando a aplicação for encerrada.
     */
    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}