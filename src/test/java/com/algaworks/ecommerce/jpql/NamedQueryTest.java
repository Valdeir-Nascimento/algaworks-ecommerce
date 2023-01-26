package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class NamedQueryTest extends EntityManagerTest {


    @Test
    public void executarConsultaArquivoXMLEspecifico() {
        TypedQuery<Pedido> typedQuery = entityManager.createNamedQuery("Pedido.todos", Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

    @Test
    public void executarConsultaArquivoXML() {
        TypedQuery<Pedido> typedQuery = entityManager.createNamedQuery("Pedido.listar", Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

    @Test
    public void executarConsultar() {
        TypedQuery<Produto> typedQuery = entityManager.createNamedQuery("Produto.listar", Produto.class);
        List<Produto> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

}
