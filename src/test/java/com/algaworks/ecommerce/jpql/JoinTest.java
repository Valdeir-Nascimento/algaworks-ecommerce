package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class JoinTest extends EntityManagerTest {

    @Test
    public void fazerJoin() {
        String jpql = "select p, pag from Pedido p join p.pagamento pag";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> pedidos = typedQuery.getResultList();
        assertEquals(1 , pedidos.size());
    }

    @Test
    public void fazerLeftJoin() {
        String jpql = "select p from Pedido p left join p.pagamento pag on pag.status = 'PROCESSANDO'";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> pedidos = typedQuery.getResultList();
        assertFalse(pedidos.isEmpty());
    }

    @Test
    public void usarJoinFetch() {
        String jpql = "select p from Pedido p left join fetch p.pagamento " +
                "join fetch p.cliente " +
                "left join fetch p.notaFiscal ";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> pedidos = typedQuery.getResultList();
        assertFalse(pedidos.isEmpty());
    }

}
