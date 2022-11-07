/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;


import entities.Departamento;
import entities.Empleado;
import jakarta.persistence.*;
import java.util.*;


public class Run {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "UnidadDePersistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        // Creamos los repositorios
        EmpleadoRepository empleadoRepository = new EmpleadoRepository(entityManager);
        DepartamentoRepository departamentoRepositorio = new DepartamentoRepository(entityManager);

        // Creamos un departamento y 3 empleados       
        Departamento d1 = new Departamento((short)50);
        d1.setDnombre("PRODUCCION");
        d1.setLoc("VALENCIA");
        Collection<Empleado> listaEmple = new ArrayList();
        listaEmple.add(new Empleado((short)1));
        listaEmple.add(new Empleado((short)2));
        listaEmple.add(new Empleado((short)3));
        d1.setEmpleCollection(listaEmple);
        
        Optional <Departamento> savedDepartamento  = departamentoRepositorio.save(d1);
        System.out.println("Almacenado departamento: "+ savedDepartamento.get());

        // Mostrar todos los departamentos
        List <Departamento> departamentos = departamentoRepositorio.findAll();
        System.out.println("Departamentos:");
        departamentos.forEach(System.out::println);
        
        // Buscar departamento por nombre
        Optional <Departamento> departamentoByName = departamentoRepositorio.findByName( "PRODUCCION");
        System.out.println( "Buscando un departamento por nombre {}: ");
        departamentoByName.ifPresent(System.out::println);
        
        // Buscando empleado por ID
        Optional <Empleado> buscarEmpleado= empleadoRepository.findById((short)7521);
        buscarEmpleado.ifPresent(System.out::println);
        
        // Buscando un empleado por id inexistente
        Optional <Empleado> notExistenteDepartamento  = empleadoRepository.findById((short)99);
        notExistenteDepartamento.ifPresent(System.out::println);
        
        // Lista todos los Empleados
        List <Empleado> empleados = empleadoRepository.findAll();
        System.out.println( "Empleado existentes en la BD: ");
        empleados.forEach(System.out::println);
        
        // Buscar empleado por nombre
        Optional <Empleado> queryEmpleado = empleadoRepository.findByName( "JIMENEZ" );
        System.out.println( "Mostrado Empleado JIMENEZ {}:");        
        queryEmpleado.ifPresent(System.out::println);
        
        // Buscar un empleado por nombre usando name query
        Optional <Empleado> queryEmpleado2 = empleadoRepository.findByNameNamedQuery("MARTIN");
        System.out.println( "Mostrando Empleado 2 {}:");
        queryEmpleado2.ifPresent(System.out::println);
        
        //Añadir un Empleado al departamanto
        Optional <Departamento>  daux = departamentoRepositorio.findById((short)20);        
        daux.ifPresent(a ->
        {
            Empleado aux = new Empleado((short)4444);
            aux.setApellido("GUTIERREZ");
            a.getEmpleCollection().add (aux);
            System.out.println("Guardando  departamento: "+departamentoRepositorio.save(a));}
        );
        
        // Modificar un empleado
        Empleado nuevo = new Empleado((short)999);
        nuevo.setApellido("SAAVEDRA");
        nuevo.setOficio("DELINEANTE");
        nuevo.setSalario((float)1500.20);
        Optional <Empleado> emp = empleadoRepository.update((short)1, nuevo);
        emp.ifPresent(System.out::println);
        
        // Borrar un empleado
        Optional <Empleado> emp_borrado = empleadoRepository.delete ((short)3);
        emp_borrado.ifPresent(System.out::println);
                       
        //Mostrar resultados
        departamentoRepositorio.showJPQL();
        departamentoRepositorio.showCriteria();
        departamentoRepositorio.showNative();
        
        // Cerramos los objetos
        entityManager.close();
        entityManagerFactory.close();
    }
}
