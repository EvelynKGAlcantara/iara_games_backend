package com.br.IaraGames.controller;

import com.br.IaraGames.beans.Compra;
import com.br.IaraGames.beans.Jogos;
import com.br.IaraGames.dao.CompraDao;
import com.br.IaraGames.dao.JogosDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraDao compraDao; 

    @Autowired
    private JogosDao jogosDao;

    @PostMapping
    public ResponseEntity<Map<String, String>> processarCompra(@RequestBody Compra compra) {
        Jogos jogo = jogosDao.findById(compra.getJogo().getId()).orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        compra.setValor(jogo.getPreco());

        compra.setDataCompra(LocalDate.now());

        compraDao.save(compra);

        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Compra realizada com sucesso!");

        return ResponseEntity.ok(response);
    }
}
