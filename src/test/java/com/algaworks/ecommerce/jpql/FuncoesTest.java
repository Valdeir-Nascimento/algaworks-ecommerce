package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.sql.Time;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.assertFalse;

public class FuncoesTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoString() {
        String jpql = "select c.nome, length(c.nome) from Categoria c where length(c.nome) > 10";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(i -> System.out.println(i[0] + " - " + i[1]));
    }

    @Test
    public void aplicarFuncaoData() {
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String jpql = "select current_date, current_time, current_timestamp from Pedido p where p.dataCriacao < current_date ";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(i -> System.out.println(i[0] + " | " + i[1] + " | " + i[2]));
    }

}
