package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

import org.junit.Test;

import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class OperacocoesEmLoteTest extends EntityManagerTest {

    private final int LIMITE_INSERCOES = 4;

    @Test
    public void atualizarEmLote() {
        entityManager.getTransaction().begin();
        String jpql = "update Produto p set p.preco = p.preco + (p.preco * 0.1) " +
                " where exists (select 1 from p.categorias c2 where c2.id = :categoria)";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("categoria", 2);
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }

    @Test
    public void inserirEmLote() throws IOException {
        InputStream in = OperacocoesEmLoteTest.class.getClassLoader()
                .getResourceAsStream("produtos/importar.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        entityManager.getTransaction().begin();
        int contador = 0;
        for (String linha : reader.lines().collect(Collectors.toList())) {
            if (linha.isBlank()) {
                continue;
            }
            String[] produtoColuna = linha.split(";");
            Produto produto = new Produto();
            produto.setNome(produtoColuna[0]);
            produto.setDescricao(produtoColuna[1]);
            produto.setPreco(new BigDecimal(produtoColuna[2]));
            produto.setDataCriacao(LocalDateTime.now());
            entityManager.persist(produto);
            if (++contador == LIMITE_INSERCOES) {
                entityManager.flush();
                entityManager.clear();
                contador = 0;
                System.out.println("------------------------------------");
            }
        }
        entityManager.getTransaction().commit();
    }

}
