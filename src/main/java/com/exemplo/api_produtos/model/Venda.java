package com.exemplo.api_produtos.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date data;

    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "tb_produto_venda",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "venda_id")        
    )
    private Set<Produto> produtos;

    public Venda() {}

    public Venda(Cliente cliente, BigDecimal preco){
        this.cliente = cliente;
        this.preco = preco;
        this.data = new Date();
    }

    public Long getId() { return id; }
    public void setId(Long id) {this.id= id;}
    public BigDecimal getPreco() {return preco;}
    public void setPreco(BigDecimal preco) {this.preco = preco;}
    public Cliente getCliente() { return cliente;}
    public void setCliente(Cliente cliente) { this.cliente = cliente;}
    public Date getData () {return data; }
    public void setData (Date data){ this.data = data; }
    public Set<Produto> getProdutos () {return produtos; }
    public void setProdutos (Set<Produto> produtos ) {this.produtos = produtos; }
}
