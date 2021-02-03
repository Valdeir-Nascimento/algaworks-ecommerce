package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
@Table(name = "categoria")
public class Categoria {

    @EqualsAndHashCode.Include
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
//    @SequenceGenerator(name = "seq", sequenceName = "sequencia_chave_primaria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;

    @OneToMany(mappedBy = "categoriaPai")
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;

    private String nome;


}
