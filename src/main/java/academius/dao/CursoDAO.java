package academius.dao;

import jakarta.persistence.EntityManager;
import academius.model.Curso;
import academius.utils.JPAUtil;
import java.util.List;

public class CursoDAO {

    public void create(Curso curso) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Curso> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Curso> cursos = null;
        try {
            cursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
        } finally {
            em.close();
        }
        return cursos;
    }

    public Curso findById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Curso curso = null;
        try {
            // Find a Curso object by its primary key (id)
            curso = em.find(Curso.class, id);
        } finally {
            em.close();
        }
        return curso;
    }

    public void update(Curso curso) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            // The merge operation attaches a detached entity or updates a managed entity
            em.merge(curso);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            // Find the entity first before removing it
            Curso curso = em.find(Curso.class, id);
            if (curso != null) {
                em.remove(curso);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}