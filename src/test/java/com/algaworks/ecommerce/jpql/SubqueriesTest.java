package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class SubqueriesTest extends EntityManagerTest {

    @Test
    public void pesquisarComIN() {
        String jpql = "select p from Pedido p where p.id " +
                " in (select ped.id from ItemPedido item " +
                "       join item.pedido ped " +
                "       join item.produto pro " +
                "       where pro.preco > 100)";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(item -> System.out.println("ID: " + item.getId()));
    }

    @Test
    public void pesquisarSubqueries() {
//        Bons clientes. Versão 1.
//        String jpql = "select c from Cliente c where 500 < (select sum(p.total) from c.pedidos p)";

//        Bons clientes. Versão 2.
        String jpql = "select c from Cliente c where 500 < (select sum(p.total) from Pedido p where p.cliente = c)";

//        Todos os pedidos acima da média de vendas
//        String jpql = "select p from Pedido p where p.total > (select avg(total) from Pedido)";

//        O produto ou os produtos mais caros da base.
//        String jpql = "select p from Produto p where p.preco = (select max(preco) from Produto)";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        List<Cliente> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(item -> System.out.println("ID: " + item.getId() + ", Nome: " + item.getNome()));
    }

}
