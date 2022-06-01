package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class HerancaTest extends EntityManagerTest {

	@Test
	public void salvarCliente() {

		Cliente cliente = new Cliente();
		cliente.setNome("Fernanda Moraes");

		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Cliente verificado = entityManager.find(Cliente.class, cliente.getId());
		assertNotNull(verificado.getId());

	}

	@Test
	public void buscarPagamentos() {
		var pagamentos = entityManager
				.createQuery("select p from Pagamento p")
				.getResultList();

		assertFalse(pagamentos.isEmpty());
	}

	@Test
	public void incluirPagamentoPedido() {
		Pedido pedido = entityManager.find(Pedido.class, 1);

		PagamentoCartao pagamentoCartao = new PagamentoCartao();
		pagamentoCartao.setPedido(pedido);
		pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoCartao.setNumeroCartao("123");

		entityManager.getTransaction().begin();
		entityManager.persist(pagamentoCartao);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido verificado = entityManager.find(Pedido.class, pedido.getId());
		assertNotNull(verificado.getPagamento());
	}

}
