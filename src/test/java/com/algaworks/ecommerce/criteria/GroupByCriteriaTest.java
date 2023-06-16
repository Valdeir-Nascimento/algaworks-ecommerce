package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class GroupByCriteriaTest extends EntityManagerTest {

    @Test
    public void agruparResultado02() {
//        Total de vendas por categoria
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
//                " join ip.produto pro " +
//                " join pro.categorias c " +
//                " group by c.id";
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);

        Join<ItemPedido, Produto>  itemPedidoProdutoJoin = root.join(ItemPedido_.produto);
        Join<Produto, Categoria> produtoCategoriaJoin = itemPedidoProdutoJoin.join(Produto_.categorias);

        criteriaQuery.multiselect(
            produtoCategoriaJoin.get(Categoria_.nome),
            criteriaBuilder.sum(root.get(ItemPedido_.precoProduto))
        );
        criteriaQuery.groupBy(produtoCategoriaJoin.get(Categoria_.id));
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();
        lista.forEach(arr -> System.out.println("Categoria: \n" + arr[0] + "Sum: " + arr[1]));


    }

    @Test
    public void agruparResultado01() {
//        Quantidade de produtos por categoria
//        String jpql = "select c.nome, count(p.id) from Categoria c join c.produtos p group by c.id";
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Categoria> root = criteriaQuery.from(Categoria.class);
        Join<Categoria, Produto> produtoJoin = root.join(Categoria_.produtos, JoinType.LEFT);
        criteriaQuery.multiselect(
            root.get(Categoria_.nome),
            criteriaBuilder.count(produtoJoin.get(Produto_.id)
            )
        );
        criteriaQuery.groupBy(root.get(Categoria_.id));
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();
        lista.forEach(arr -> System.out.println("Nome: \n" + arr[0] + "Count: " + arr[1]));
    }

}
