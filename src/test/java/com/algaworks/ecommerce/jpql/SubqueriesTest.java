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
    public void pesquisarComAllExercicio() {
        // Todos os produtos que sempre foram vendidos pelo mesmo preço.
        String jpql = "select distinct p from ItemPedido ip join ip.produto p where " +
                " ip.precoProduto = ALL " +
                " (select precoProduto from ItemPedido where produto = p and id <> ip.id)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
        resposta.forEach(item -> System.out.println("ID: " + item.getId()));
    }

    @Test
    public void perquisarComAny() {
//        Todos os produtos que já foram vendidos, pelo menos uma vez pelo preço atual
//        String jpql = "select p from Produto p where p.preco = ANY (select precoProduto from ItemPedido where produto = p)";

//        Todos os produtos que já foram vendidos por um preco diferente do atual
        String jpql = "select p from Produto p where p.preco <> ANY (select precoProduto from ItemPedido where produto = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
        List<Produto> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
        lista.forEach(item -> System.out.println("ID: " + item.getId()));
    }


    @Test
    public void perquisarComAll() {
//        Todos os produtos que sempre foram vendidos pelo preço atual
//        String jpql = "select p from Produto p where p.preco = ALL (select precoProduto from ItemPedido where produto = p)";

//        Todos os produtos que não foram vendidos mais depois que encerraram
        String jpql = "select p from Produto p where p.preco > ALL (select precoProduto from ItemPedido where produto = p) ";
        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
        List<Produto> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
        lista.forEach(item -> System.out.println("ID: " + item.getId()));
    }

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
