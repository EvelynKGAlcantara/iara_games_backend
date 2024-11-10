package com.br.IaraGames.beans;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_jogos")
public class Jogos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(name = "tipo_midia", length = 50, nullable = false)
    private String tipoMidia;

    @Column(length = 50, nullable = false)
    private String plataforma;

    @ManyToOne
    @JoinColumn(name = "fabricante_id", nullable = false)
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private boolean lancamento;

    @Column(nullable = false)
    private boolean popular;

    @Column(length = 255)
    private String imagem;

    @Column(length = 255)
    private String video;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(name = "data_lancamento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataLancamento;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    public Integer getId() { 
        return id; 
    }

    public void setId(Integer id) { 
        this.id = id; 
    }

    public String getNome() { 
        return nome; 
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }
    public String getTipoMidia() { 
        return tipoMidia; 
    }

    public void setTipoMidia(String tipoMidia) { 
        this.tipoMidia = tipoMidia; 
    }

    public String getPlataforma() { 
        return plataforma; 
    }

    public void setPlataforma(String plataforma) { 
        this.plataforma = plataforma; 
    }

    public Fabricante getFabricante() { 
        return fabricante; 
    }

    public void setFabricante(Fabricante fabricante) { 
        this.fabricante = fabricante; 
    }

    public Categoria getCategoria() { 
        return categoria; 
    }

    public void setCategoria(Categoria categoria) { 
        this.categoria = categoria; 
    }

    public boolean isLancamento() { 
        return lancamento; 
    }

    public void setLancamento(boolean lancamento) { 
        this.lancamento = lancamento; 
    }

    public boolean isPopular() { 
        return popular; 
    }

    public void setPopular(boolean popular) { 
        this.popular = popular; 
    }

    public String getImagem() { 
        return imagem; 
    }

    public void setImagem(String imagem) { 
        this.imagem = imagem; 
    }

    public String getVideo() { 
        return video; 
    }

    public void setVideo(String video) { 
        this.video = video; 
    }

    public BigDecimal getPreco() { 
        return preco; 
    }

    public void setPreco(BigDecimal preco) { 
        this.preco = preco; 
    }

    public LocalDate getDataLancamento() { 
        return dataLancamento; 
    }

    public void setDataLancamento(LocalDate dataLancamento) { 
        this.dataLancamento = dataLancamento; 
    }
    
    public String getDescricao() { 
        return descricao; 
    }

    public void setDescricao(String descricao) { 
        this.descricao = descricao; 
    }
}
