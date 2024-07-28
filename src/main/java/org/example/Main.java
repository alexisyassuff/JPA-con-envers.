package org.example;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("en marcha Alberto");

        try {
            // Persistir una nueva entidad Person
            entityManager.getTransaction().begin();

            Persona person = new Persona("Pepe", 25);


            person.setName("María");
            person.setAge(60);

            entityManager.persist(person);

            entityManager.getTransaction().commit();


            // Consultar y mostrar la entidad persistida
            Persona retrievedPerson = entityManager.find(Persona.class, person.getId());
            System.out.println("Retrieved Persona: " + retrievedPerson.getName());

        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Persona");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}