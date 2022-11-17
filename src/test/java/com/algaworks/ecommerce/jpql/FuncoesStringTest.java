package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class FuncoesStringTest extends EntityManagerTest {

    @Test
    public void aplicarFuncao() {
        String jpql = "select c.nome, length(c.nome) from Categoria c where length(c.nome) > 10";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(i -> System.out.println(i[0] + " - " + i[1]));
    }

}
