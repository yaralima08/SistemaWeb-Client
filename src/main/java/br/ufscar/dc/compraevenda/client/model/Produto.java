package br.ufscar.dc.compraevenda.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private Long id;
    private String nome;
    private String categoria;
    private String tamanho;
    private String cor;
    private String descricao;
    private Integer quantidadeEstoque;
    private BigDecimal preco;
    private List<String> imagens;
    private Boolean ativo;
}
