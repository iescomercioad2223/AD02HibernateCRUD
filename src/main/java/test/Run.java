/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;


import entities.Departamento;
import entities.Empleado;
import jakarta.persistence.*;
import java.util.*;
import java.util.Optional;

public class Run {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "Books");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        // Creamos los repositorios
        EmpleadoRepository empleadoRepository = new EmpleadoRepository(entityManager);
        DepartamentoRepository departamentoRepositorio = new DepartamentoRepository(entityManager);

        // Creamos un departamento y 3 empleados
        Collection<Empleado> listaEmple = new ArrayList();
        Departamento d1 = new Departamento((short)10);
        d1.setDnombre("PRODUCCION");
        listaEmple.add(new Empleado((short)1));
        listaEmple.add(new Empleado((short)2));
        listaEmple.add(new Empleado((short)3));
        
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
        
        // Buscando Departamentos por ID
        Optional <Empleado> buscarEmpleado= empleadoRepository.findById(2);
        buscarEmpleado.ifPresent(System.out::println);
        
        // Buscando un departamento por id inexistente
        Optional <Empleado> notExistenteDepartamento  = empleadoRepository.findById(99);
        notExistenteDepartamento.ifPresent(System.out::println);
        
        // Lista todos los Empleados
        List <Empleado> empleados = empleadoRepository.findAll();
        System.out.println( "Empleado existentes en la BD: ");
        empleados.forEach(System.out::println);
        
        // Buscar empleado por nombre
        Optional <Empleado> queryEmpleado = empleadoRepository.findByName( "1" );
        System.out.println( "Mostrado Empleado 1 {}:");        
        queryEmpleado.ifPresent(System.out::println);
        
        // Buscar un empleado por nombre usando name query
        Optional <Empleado> queryEmpleado2 = empleadoRepository.findByNameNamedQuery("2");
        System.out.println( "Mostrando Empleado 2 {}:");
        queryEmpleado2.ifPresent(System.out::println);
        
        // Añadir un Empleado al departamanto
        Optional <Departamento>  daux = departamentoRepositorio.findById(20);        
        daux.ifPresent(a ->
        {
            a.getEmpleCollection().add (new Empleado((short)4));
            System.out.println("Guardando  departamento: "+departamentoRepositorio.save(a));}
        );
        
        // Cerramos los objetos
        entityManager.close();
        entityManagerFactory.close();
    }
}
