package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class PaginacaoJPQLTest extends EntityManagerTest {

    @Test
    public void paginarResultados() {
        String jpql = "select c from Categoria c order by c.nome";
        TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);
        // FIRST_RESULT = MAX_RESULT * (pagina - 1)
        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(2);
        List<Categoria> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(categoria -> System.out.println(categoria.getId() + ", " + categoria.getNome()));
    }

}
