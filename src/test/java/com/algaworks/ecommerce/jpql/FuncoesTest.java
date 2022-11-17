package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

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

    @Test
    public void aplicarFuncaoNumero() {
        String jpql = "select abs(-10), mod(3,2), sqrt(9) from Pedido ";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(i -> System.out.println(i[0] + " | " + i[1] + " | " + i[2]));
    }

    @Test
    public void aplicarFuncaoColecao() {
        String jpql = "select size(p.itens) from Pedido p where size(p.itens) > 1";
        TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);
        List<Integer> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(System.out::println);
    }

    @Test
    public void aplicarFuncaoNativa() {
        String jpql = "select p from Pedido p where function('acima_media_faturamento', p.total) = 1";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(System.out::println);
    }

    @Test
    public void aplicarFuncaoAgregacao() {
        // avg, count, min, max, sum
        String jpql = "select avg(p.total) from Pedido p";
        TypedQuery<Number> typedQuery = entityManager.createQuery(jpql, Number.class);
        List<Number> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(System.out::println);
    }

}
