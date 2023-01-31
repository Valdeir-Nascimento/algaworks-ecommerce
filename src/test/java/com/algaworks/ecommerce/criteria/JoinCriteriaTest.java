package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pagamento;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPagamento;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.junit.Assert.*;

public class JoinCriteriaTest extends EntityManagerTest {

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
