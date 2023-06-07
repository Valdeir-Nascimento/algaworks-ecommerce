package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pagamento;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

import static org.junit.Assert.*;

public class JoinCriteriaTest extends EntityManagerTest {

    @Test
    public void buscarPedidosComProdutoEspecifico() {
        //select p from Pedido p join p.itens i where i.id.produtoId = 1
    }

    @Test
    public void usarJoinFetch() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        root.fetch("itens");
        root.fetch("notaFiscal", JoinType.LEFT);
        root.fetch("pagamento", JoinType.LEFT);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        Pedido pedido = typedQuery.getSingleResult();
        assertNotNull(pedido);
        assertFalse(pedido.getItens().isEmpty());
    }

    @Test
    public void fazerLeftOuterJoin() {
        //select p from Pedido p left join p.pagamento pag on pag.status = 'PROCESSANDO'
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        Join<Pedido, Pagamento> joinPagamento = root.join("pagamento", JoinType.LEFT);
        joinPagamento.on(criteriaBuilder.equal(joinPagamento.get("status"), StatusPagamento.PROCESSANDO));

        criteriaQuery.select(root);
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> lista = typedQuery.getResultList();
        assertEquals(lista.size(), 5);
    }


    @Test
    public void fazerJoinComOn() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");
        joinPagamento.on(criteriaBuilder.equal(joinPagamento.get("status"), StatusPagamento.PROCESSANDO));

        criteriaQuery.select(root);
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> lista = typedQuery.getResultList();
        assertEquals(lista.size(), 2);
    }

    @Test
    public void fazerJoin() {
        //select p from Pedido o join p.pagamento pag
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");
        criteriaQuery.select(root);
//        criteriaQuery.select(joinPagamento);
//        criteriaQuery.where(criteriaBuilder.equal(joinPagamento.get("status"), StatusPagamento.PROCESSANDO));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> lista = typedQuery.getResultList();
        assertEquals(lista.size(), 4);

    }

}
