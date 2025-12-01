package com.exemplo.api_produtos.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.exemplo.api_produtos.model.Estoque;
import com.exemplo.api_produtos.model.Produto;
import com.exemplo.api_produtos.model.ProdutoVenda;
import com.exemplo.api_produtos.model.Venda;
import com.exemplo.api_produtos.repository.ProdutoRepository;
import com.exemplo.api_produtos.repository.VendaRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
public class VendaController {
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    @GetMapping
    public List<Venda> getAllVendas() {
        return vendaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getVendaById(@PathVariable Long id) {
        return vendaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venda createVenda(@RequestBody Venda venda) {

        BigDecimal total = BigDecimal.ZERO;

        for (ProdutoVenda produtoVenda : venda.getProdutosVendidos()) {

            // Pegamos o produto REAL do banco:
            Produto produto = produtoRepository
                    .findById(produtoVenda.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            Estoque estoque = produto.getEstoque();

            if (estoque.getQuantidade() < produtoVenda.getQuantidade()) {
            throw new IllegalArgumentException(
                "Estoque insuficiente para o produto: " + produtoVenda.getProduto().getNome()
            );
        }

            // Atualiza estoque
            estoque.setQuantidade(estoque.getQuantidade() - produtoVenda.getQuantidade());

            // Conecta a relação corretamente
            produtoVenda.setProduto(produto);
            produtoVenda.setVenda(venda);

            // Calcula subtotal
            total = total.add(
                    produto.getPreco().multiply(
                            BigDecimal.valueOf(produtoVenda.getQuantidade())));
        }

        venda.setPreco(total);

        return vendaRepository.save(venda); // Cascade automaticamente salva ProdutoVenda
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(
            @PathVariable Long id, @RequestBody Venda vendaDetails) {
        return vendaRepository.findById(id)
                .map(venda -> {
                    venda.setCliente(vendaDetails.getCliente());
                    venda.setProdutosVendidos(vendaDetails.getProdutosVendidos());
                    return ResponseEntity.ok(vendaRepository.save(venda));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable Long id) {
        if (!vendaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vendaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
