package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Test;

public class CrudTest extends EntityManagerTest {
    @Test
    public void insert(){
        Cliente cliente = new Cliente();
//        cliente.setId(3);
        cliente.setNome("Natacha Nascimento");
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
    }

    @Test
    public void buscarCliente(){
        Cliente cliente = entityManager.find(Cliente.class, 1);
        System.out.println(cliente);
    }

    @Test
    public void update(){
        insert();
        Cliente cliente = entityManager.find(Cliente.class, 1);
        cliente.setNome("Maria Edna");
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
    }

    @Test
    public void excluir(){
        Cliente cliente = entityManager.find(Cliente.class, 2);
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
    }

}
