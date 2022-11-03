package test;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import entities.Empleado;

public class EmpleadoRepository {

    private EntityManager entityManager;

    public EmpleadoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Busqueda por ID
    public Optional<Empleado> findById(Short id) {
        Empleado empleado = entityManager.find(Empleado.class, id);
        return empleado != null ? Optional.of(empleado) : Optional.empty();
    }

    // Busca todos
    public List<Empleado> findAll() {
        return entityManager.createQuery("from Empleado").getResultList();
    }

    // Crea una Query que busca por nombre
    public Optional<Empleado> findByName(String papellido) {
        Empleado empleado = entityManager.createQuery("SELECT b FROM Empleado b WHERE b.apellido = :apellido", Empleado.class).setParameter("apellido", papellido).getSingleResult();
        return empleado != null ? Optional.of(empleado) : Optional.empty();
    }

    // Utiliza una query predefinida Empleado.findByName
    public Optional<Empleado> findByNameNamedQuery(String papellido) {
        Empleado empleado = entityManager.createNamedQuery("Empleado.busquedaPorNombre", Empleado.class).setParameter("apellido", papellido).getSingleResult();

        return empleado != null ? Optional.of(empleado) : Optional.empty();
    }

    // Hace persistente el empleado
    public Optional<Empleado> save(Empleado empleado) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(empleado);
            entityManager.getTransaction().commit();
            return Optional.of(empleado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Modificación de empleado
    public Optional<Empleado> update(Short id, Empleado nuevo) {
        try {
            
            entityManager.getTransaction().begin();
            // Cargamos el empleado que queremos modificar
            Empleado old = entityManager.find(Empleado.class, id);
            if (old==null) 
                return null;
            old.setApellido(nuevo.getApellido());
            old.setOficio(nuevo.getOficio()); // etc.   
            old.setSalario(nuevo.getSalario());
            entityManager.getTransaction().commit();
            return Optional.of(old);
       
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return Optional.empty();
    }

    public Optional<Empleado> delete(short s) {
        try {
            entityManager.getTransaction().begin();
            Empleado e = entityManager.find(Empleado.class, (short) s);
            if (e == null) {
                return null;
            }
            entityManager.remove(e);
            entityManager.getTransaction().commit();

            return Optional.of(e);
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return Optional.empty();
    }
}
