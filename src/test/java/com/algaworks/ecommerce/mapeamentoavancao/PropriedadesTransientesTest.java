package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropriedadesTransientesTest extends EntityManagerTest {

	@Test
	public void validarPrimeroNome() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		assertEquals("Fernando", cliente.getPrimeiroNome());
	}

}
