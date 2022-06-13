package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@DiscriminatorValue("boleto")
@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends Pagamento {

	@Column(name = "codigo_barras", length = 100, nullable = false)
	private String codigoBarras;
}
