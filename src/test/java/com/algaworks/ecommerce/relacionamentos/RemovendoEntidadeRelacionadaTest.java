package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RemovendoEntidadeRelacionadaTest extends EntityManagerTest {

    @Test
    public void removerEntidadeRelacionda(){
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().forEach(i -> entityManager.remove(i));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificado = entityManager.find(Pedido.class, 1);
        Assert.assertNull(pedidoVerificado);

    }

}
