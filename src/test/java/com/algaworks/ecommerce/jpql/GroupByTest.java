package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class GroupByTest extends EntityManagerTest {

    @Test
    public void agruparResultado() {
        // Quantidade de produtos por categoria: select c.nome, count(c.id) from Categoria c join c.produtos p group by c.id

        // Total de vendas por mês: select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) from Pedido p group by year(p.dataCriacao), month(p.dataCriacao)

        // Total de vendas por categoria: select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.produto pro join pro.categorias c group by c.id

        // Total de vendas por cliente: select c.nome, sum(p.total) from ItemPedido ip join ip.pedido p join p.cliente c group by c.id

        // Total de vendas por dia e por categoria:

        String jpql = "select " +
                "concat(year(p.dataCriacao), '/', month(p.dataCriacao), '/', day(p.dataCriacao)), " +
                "concat(c.nome, ': ', sum(ip.precoProduto)) " +
                "from ItemPedido ip " +
                "join ip.pedido p " +
                "join ip.produto pro " +
                "join pro.categorias c " +
                "group by year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao), c.id " +
                "order by p.dataCriacao, c.nome";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(item -> System.out.println(item[0] + " - " + item[1]));

    }

}
