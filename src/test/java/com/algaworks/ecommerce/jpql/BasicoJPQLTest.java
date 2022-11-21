package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.*;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void usarDistinct() {
        String jpql = "select distinct p from Pedido p join p.itens i join i.produto pro where pro.id in (1, 2, 3, 4)";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> pedidos = typedQuery.getResultList();
        assertFalse(pedidos.isEmpty());
        System.out.println(pedidos.size());
    }

    @Test
    public void buscarPorIdentificador() {
        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p where p.id = 1", Pedido.class);
        Pedido pedido = typedQuery.getSingleResult();
        assertNotNull(pedido);
    }

    @Test
    public void mostrarDiferencaQueries() {
        String jpql = "select p from Pedido p where p.id = 1";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        Pedido pedido = typedQuery.getSingleResult();
        assertNotNull(pedido);

        Query query = entityManager.createQuery(jpql);
        Pedido pedidoAtual = (Pedido) query.getSingleResult();
        assertNotNull(pedidoAtual);
    }

    @Test
    public void selecionarUmAtributoParaRetorno() {
        String jpql = "select p.nome from Produto p";
        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
        List<String> produtos = typedQuery.getResultList();
        assertTrue(String.class.equals(produtos.get(0).getClass()));

        String jpqlCliente = "select p.cliente from Pedido p";
        TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);
        List<Cliente> clientes = typedQueryCliente.getResultList();
        assertTrue(Cliente.class.equals(clientes.get(0).getClass()));
    }

    @Test
    public void projetarResultado() {
        String jpql = "select id, nome from Produto";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resposta = typedQuery.getResultList();
        assertEquals(2, resposta.get(0).length);
        resposta.forEach(item -> System.out.println(item[0] + ", " + item[1]));
    }

    @Test
    public void projetarDTO() {
        String jpql = "select new com.algaworks.ecommerce.dto.ProdutoDTO(id, nome) from Produto";
        TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(jpql, ProdutoDTO.class);
        List<ProdutoDTO> produtos = typedQuery.getResultList();
        assertFalse(produtos.isEmpty());
        produtos.forEach(produto -> System.out.println(produto.getNome()));
    }

    @Test
    public void ordenarResultados() {
        String jpql = "select c from Cliente c order by c.nome desc";
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        List<Cliente> resposta = typedQuery.getResultList();
        assertFalse(resposta.isEmpty());
    }

}
