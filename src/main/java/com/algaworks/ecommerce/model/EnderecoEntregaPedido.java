package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
//Objeto embutido
@Embeddable
@Getter
@Setter
public class EnderecoEntregaPedido {
    private String cep;
    private String logradouro;
    private String complemento;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
}
