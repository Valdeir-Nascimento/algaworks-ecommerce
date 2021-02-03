package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "pedido_id")
    private Integer PedidoId;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;


}
