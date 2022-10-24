
package test;

import entities.Departamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Run {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Departamento d = new Departamento();
        d.setDeptNo((short)20);
        d.setDnombre("PRODUCCION");
        d.setLoc("SEVILLA");
       
        entityManager.persist(d);

        Departamento depart = entityManager.createQuery("select d from Departamento d where deptNo=10", Departamento.class)
                .getSingleResult();
        System.out.println(depart.toString());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
