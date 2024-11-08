package com.br.IaraGames.beans;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_titular", length = 100, nullable = false)
    private String nomeTitular;

    @Column(name = "numero_cartao", length = 16, nullable = false)
    private String numeroCartao;

    @Column(name = "data_expiracao", length = 5, nullable = false)
    private String dataExpiracao;

    @Column(name = "codigo_seguranca", length = 3, nullable = false)
    private String codigoSeguranca;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(name = "data_compra", nullable = false)
    private LocalDate dataCompra;

    // Relacionamento com a tabela Jogos
    @ManyToOne
    @JoinColumn(name = "jogo_id", nullable = false)
    private Jogos jogo;

    // Getters e Setters

    public Integer getId() { 
        return id; 
    }

    public void setId(Integer id) { 
        this.id = id; 
    }

    public String getNomeTitular() { 
        return nomeTitular; 
    }

    public void setNomeTitular(String nomeTitular) { 
        this.nomeTitular = nomeTitular; 
    }

    public String getNumeroCartao() { 
        return numeroCartao; 
    }

    public void setNumeroCartao(String numeroCartao) { 
        this.numeroCartao = numeroCartao; 
    }

    public String getDataExpiracao() { 
        return dataExpiracao; 
    }

    public void setDataExpiracao(String dataExpiracao) { 
        this.dataExpiracao = dataExpiracao; 
    }

    public String getCodigoSeguranca() { 
        return codigoSeguranca; 
    }

    public void setCodigoSeguranca(String codigoSeguranca) { 
        this.codigoSeguranca = codigoSeguranca; 
    }

    public BigDecimal getValor() { 
        return valor; 
    }

    public void setValor(BigDecimal valor) { 
        this.valor = valor; 
    }

    public LocalDate getDataCompra() { 
        return dataCompra; 
    }

    public void setDataCompra(LocalDate dataCompra) { 
        this.dataCompra = dataCompra; 
    }

    public Jogos getJogo() {
        return jogo;
    }

    public void setJogo(Jogos jogo) {
        this.jogo = jogo;
    }
}

