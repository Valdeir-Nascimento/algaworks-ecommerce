package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "pedido_id")
//    @JoinTable(name = "pedido_nota_fiscal",
//            joinColumns = @JoinColumn(name = "nota_fiscal_id", unique = true),
//            inverseJoinColumns = @JoinColumn(name = "pedido_id", unique = true)
//            )
    private Pedido pedido;

    @Column(name = "data_emissao")
    private Date dataEmissao;

    private String xml;

}
