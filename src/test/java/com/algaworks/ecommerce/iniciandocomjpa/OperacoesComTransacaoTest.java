package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {
    @Test
    public void abrirFechaTransacao() {
        Produto produto = new Produto();
        entityManager.getTransaction().begin();
//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }

    @Test
    public void inserir() {
        Produto produto = new Produto();
//        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para as suas fotos");
        produto.setPreco(new BigDecimal(5000));
        //Abrindo a transação
        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
    }

    @Test
    public void remover() {
        //Buscando objeto na base de dados
        Produto produto = entityManager.find(Produto.class, 3);
        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();
        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVerificacao);
    }

    @Test
    public void update() {

        Produto produto = new Produto();
        produto.setNome("Novo Kindle pegou com o merge");
        produto.setDescricao("Conheça o novo kindle");
        produto.setPreco(new BigDecimal(399));
        entityManager.getTransaction().begin();
        produto = entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();
        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificado);
    }

    @Test
    public void atualizarObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("Kindle 2ª Geração ");

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Test
    public void inserirObjetoComMerge() {
        Produto produto = new Produto();
//        produto.setId(4);
        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("A melhor qualidade de som");
        produto.setPreco(new BigDecimal(5000));
        //Abrindo a transação
        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();
    }

    @Test
    public void mostrarDiferencaPersistMerge() {
        Produto produtoPersist = new Produto();
//        produtoPersist.setId(4);
        produtoPersist.setNome("Smart One Plus");
        produtoPersist.setDescricao("O processador mais rapido");
        produtoPersist.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist);
        produtoPersist.setNome("Smart Two Plus");
        entityManager.getTransaction().commit();


        Produto produtoMerge = new Produto();
        produtoMerge.setId(6);
        produtoMerge.setNome("Notebook dell");
        produtoMerge.setDescricao("O melhor do mercado");
        produtoMerge.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Notebook dell merge");
        entityManager.getTransaction().commit();
    }

    @Test
    public void impedirOperacaoComBancoDeDados() {
        Produto produto = entityManager.find(Produto.class, 1);
        entityManager.detach(produto);
        produto.setNome("Kindle 2ª Geração ");

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

}
