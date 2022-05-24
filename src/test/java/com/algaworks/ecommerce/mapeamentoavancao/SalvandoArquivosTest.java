//package com.algaworks.ecommerce.mapeamentoavancao;
//
//import com.algaworks.ecommerce.EntityManagerTest;
//import com.algaworks.ecommerce.model.NotaFiscal;
//import com.algaworks.ecommerce.model.Pedido;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.Date;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//public class SalvandoArquivosTest  extends EntityManagerTest {
//
//	@Test
//	public void salvarXmlNota() {
//		Pedido pedido = entityManager.find(Pedido.class, 1);
//
//		NotaFiscal notaFiscal = new NotaFiscal();
//		notaFiscal.setPedido(pedido);
//		notaFiscal.setDataEmissao(new Date());
//		notaFiscal.setXml(carregarNotaFiscal());
//
//		entityManager.getTransaction().begin();
//		entityManager.persist(notaFiscal);
//		entityManager.getTransaction().commit();
//
//		NotaFiscal notaFiscalVerificada = entityManager.find(NotaFiscal.class, notaFiscal.getId());
//		assertNotNull(notaFiscalVerificada.getXml());
//		assertTrue(notaFiscalVerificada.getXml().length > 0);
//	}
//
//	public static byte[] carregarNotaFiscal() {
//		try {
//			return SalvandoArquivosTest.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//}
