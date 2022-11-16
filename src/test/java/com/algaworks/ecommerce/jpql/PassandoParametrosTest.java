package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PassandoParametrosTest extends EntityManagerTest {

    @Test
    public void passarParametro() {
        String jpql = "select p from Pedido p join p.pagamento pag where p.id = :pedidoId and pag.status = :statusPagamento";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("pedidoId", 2);
        typedQuery.setParameter("statusPagamento", StatusPagamento.PROCESSANDO);
        List<Pedido> pedidos = typedQuery.getResultList();
        assertEquals(1 , pedidos.size());
    }

    @Test
    public void passarParametroDate() {
        String jpql = "select nf from NotaFiscal nf where nf.dataEmissao <= ?1";
        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
        typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);
        List<NotaFiscal> notas = typedQuery.getResultList();
        assertEquals(1, notas.size());
    }


}
