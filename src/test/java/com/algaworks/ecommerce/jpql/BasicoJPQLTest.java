package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.*;

public class BasicoJPQLTest extends EntityManagerTest {

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
    public void fazerJoin() {
        String jpql = "select p, pag from Pedido p join p.pagamento pag";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> pedidos = typedQuery.getResultList();
        assertEquals(1 , pedidos.size());
    }

}
