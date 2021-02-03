package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoManyToOneTest extends EntityManagerTest {
    @Test
    public void verificarRelacionemento(){
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);

        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getCliente());

    }

    @Test
    public void verificarRelacionamentoItemPedido(){
        Cliente cliente =  entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setTotal(new BigDecimal(1800));
        pedido.setCliente(cliente);

        ItemPedido item = new ItemPedido();
        item.setPrecoProduto(produto.getPreco());
        item.setQuantidade(100);
        item.setPedido(pedido);
        item.setProduto(produto);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificado = entityManager.find(ItemPedido.class, item.getId());
        Assert.assertNotNull(itemPedidoVerificado.getPedido());
        Assert.assertNotNull(itemPedidoVerificado.getProduto());


    }

}
