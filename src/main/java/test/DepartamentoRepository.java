
package test;

import entities.Departamento;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

public class DepartamentoRepository {

    private EntityManager entityManager;

    public DepartamentoRepository(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public Optional<Departamento> findById(Short id) {

        Departamento departamento = entityManager.find(Departamento.class, id);
        return departamento != null ? Optional.of(departamento) : Optional.empty();
    }

    public List<Departamento> findAll() {

        return entityManager.createQuery("from Departamento").getResultList();
    }

    public Optional<Departamento> findByName(String name) {

        Departamento departamento = entityManager.createNamedQuery("Departamento.busquedaPorNombre", Departamento.class).setParameter("dnombre", name).getSingleResult();
        return departamento != null ? Optional.of(departamento) : Optional.empty();
    }

    public Optional<Departamento> save(Departamento departamento) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(departamento);
            entityManager.getTransaction().commit();
            return Optional.of(departamento);
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return Optional.empty();
    }
}
