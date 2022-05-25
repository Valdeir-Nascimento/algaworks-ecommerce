package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Test;

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

}
