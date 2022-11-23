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
    public void perquisarComExistsExercicio() {
        String jpql = "select p from Produto p " +
                " where exists " +
                " (select 1 from ItemPedido where produto = p and precoProduto <> p.preco)";
        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
        List<Produto> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
        lista.forEach(item -> System.out.println("ID: " + item.getId()));
    }

    @Test
    public void pesquisarComSubqueryExericio() {
        String jpql = "select c from Cliente c where " +
                " (select count(cliente) from Pedido where cliente = c) >= 2";
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        List<Cliente> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(item -> System.out.println("ID: " + item.getId()));
    }

    @Test
    public void pesquisarComINExercicio() {
        String jpql = "select p from Pedido p where p.id in " +
                " (select ped.id from ItemPedido ip " +
                " join ip.pedido ped " +
                " join ip.produto pro " +
                " join pro.categorias c " +
                " where c.id = 2)";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(item -> System.out.println("ID: " + item.getId()));
    }

    @Test
    public void pesquisarComExists() {
        String jpql = "select p from Produto p where exists " +
                " (select 1 from ItemPedido ip join ip.produto pro where pro = p)";
        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
        List<Produto> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(item -> System.out.println("ID: " + item.getId()));
    }

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
