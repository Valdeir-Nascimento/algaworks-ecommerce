package com.algaworks.ecommerce.conhecendoentitymanager;


import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

public class ContextoDePeristenciaTest extends EntityManagerTest {

    @Test
    public void usarContextoPersistencia() {
        Produto produto = entityManager.find(Produto.class, 1);
        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
    }

}
