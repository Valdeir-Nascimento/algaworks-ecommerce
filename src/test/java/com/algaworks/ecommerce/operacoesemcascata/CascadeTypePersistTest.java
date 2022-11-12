package com.algaworks.ecommerce.operacoesemcascata;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class CascadeTypePersistTest extends EntityManagerTest {

    @Test
    public void persistirPedidoComItens() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);
        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(produto.getPreco());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido item = new ItemPedido();
        item.setId(new ItemPedidoId());
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(1);
        item.setPrecoProduto(produto.getPreco());

        pedido.setItens(List.of(item)); //CascadeType.PERSIST

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificado = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificado);
        assertFalse(pedido.getItens().isEmpty());
    }

    @Test
    public void persistirItemPedidoComPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);
        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente); //CascadeType.PERSIST
        pedido.setTotal(produto.getPreco());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido item = new ItemPedido();
        item.setId(new ItemPedidoId());
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(1);
        item.setPrecoProduto(produto.getPreco());

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
        assertNotNull(clienteVerificado);
    }

    @Test
    public void persistirPedidoComCliente() {
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(1980, 1, 1));
        cliente.setSexo(SexoCliente.MASCULINO);
        cliente.setNome("José Carlos");
        cliente.setCpf("01895374266");

        Pedido pedido = new Pedido();
        pedido.setDataConclusao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(BigDecimal.ZERO);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
        assertNotNull(clienteVerificado);
    }

}
