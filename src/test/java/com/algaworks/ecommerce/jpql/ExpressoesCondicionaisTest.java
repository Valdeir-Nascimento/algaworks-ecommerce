package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    public void usarExpressaoCondicionalLike() {
        String jpql = "select c from Cliente c where c.nome like concat('%', :nome, '%') ";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        typedQuery.setParameter("nome", "a");
        List<Object[]> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
    }

    @Test
    public void usarIsNull() {
        String jpql = "select p from Produto p where p.foto is null ";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
    }

    @Test
    public void usarIsEmpty() {
        String jpql = "select p from Produto p where p.categorias is empty";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
    }


    @Test
    public void usarMaiorMenor() {
        String jpql = "select p from Produto p where p.preco >= :precoInicial and p.preco <= :precoFinal";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        typedQuery.setParameter("precoInicial", new BigDecimal(400));
        typedQuery.setParameter("precoFinal", new BigDecimal(1500));
        List<Object[]> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
    }


}
