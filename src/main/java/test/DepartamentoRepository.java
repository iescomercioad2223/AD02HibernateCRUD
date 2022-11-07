package test;

import entities.Departamento;
import entities.Empleado;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
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

    public void showNative() {
        List<Departamento> depar = entityManager.createNativeQuery(
                "SELECT dept_no, dnombre, loc FROM DEPART", Departamento.class)
                .getResultList();

        for (Departamento d : depar) {
            String id = d.getDeptNo() + "";
            String name = d.getDnombre();
            System.out.println("Dept_no: " + id + " Nombre:" + name);
        }
    }

    public void showJPQL() {
        Query q = entityManager.createQuery("from Departamento as d where d.deptNo in (10,20,30) OR loc LIKE 'VAL%'");
        List<Departamento> depar = q.getResultList();
        for (Departamento d : depar) {
            String id = d.getDeptNo() + "";
            String name = d.getDnombre();
            System.out.println("Dept_no: " + id + " Nombre:" + name);
        }
    }

    public void showCriteria() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Departamento> criteria = builder.createQuery(Departamento.class);
        Root<Departamento> root = criteria.from(Departamento.class);
        criteria.select(root);
        criteria.where(builder.between(root.get("deptNo"), 10, 30)); // deptNo between (10,30)

        List<Departamento> depar = entityManager.createQuery(criteria).getResultList();
        for (Departamento d : depar) {
            String id = d.getDeptNo() + "";
            String name = d.getDnombre();
            System.out.println("Dept_no: " + id + " Nombre:" + name);
        }
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
