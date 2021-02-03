package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {
    @Test
    public void analisarMapeamentoObjetoEmbutido(){
        EnderecoEntregaPedido enderecoEntregaPedido = new EnderecoEntregaPedido();
        enderecoEntregaPedido.setCep("66825-585");
        enderecoEntregaPedido.setLogradouro("Rio Volga");
        enderecoEntregaPedido.setBairro("Tapanã");
        enderecoEntregaPedido.setCidade("Belém");
        enderecoEntregaPedido.setEstado("PA");

        Pedido pedido = new Pedido();
//        pedido.setId(1);
        pedido.setDataConclusao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setEnderecoEntrega(enderecoEntregaPedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificado = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificado);
        Assert.assertNotNull(pedidoVerificado.getEnderecoEntrega());

        System.out.println(pedidoVerificado.toString());


    }

}
