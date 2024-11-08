package com.br.IaraGames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.IaraGames.beans.Fabricante;
import com.br.IaraGames.dao.FabricanteDao;

@RestController
@RequestMapping("/fabricantes")
public class FabricanteController {
    @Autowired
    private FabricanteDao fabricanteDao;

    @PostMapping
    public ResponseEntity<Fabricante> criarFabricante(@RequestBody Fabricante fabricante) {
        Fabricante novoFabricante = fabricanteDao.save(fabricante);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFabricante);
    }

    @GetMapping
public List<Fabricante> listarFabricantes() {
    return fabricanteDao.findAll();
}

@GetMapping("/{id}")
public ResponseEntity<Fabricante> getFabricanteById(@PathVariable Integer id) {
    Optional<Fabricante> fabricante = fabricanteDao.findById(id);
    return fabricante.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}

@PutMapping("/{id}")
public ResponseEntity<Fabricante> atualizarFabricante(@PathVariable Integer id, @RequestBody Fabricante fabricante) {
    // Verifica se o fabricante com o ID informado existe
    if (fabricanteDao.existsById(id)) {
        fabricante.setId(id); // Define o ID para garantir que estamos atualizando o fabricante correto
        Fabricante fabricanteAtualizado = fabricanteDao.save(fabricante);
        return ResponseEntity.ok(fabricanteAtualizado);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se o fabricante não for encontrado
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletarFabricante(@PathVariable Integer id) {
    // Verifica se o fabricante com o ID informado existe
    if (fabricanteDao.existsById(id)) {
        fabricanteDao.deleteById(id); // Deleta o fabricante
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Retorna 204 (No Content) após excluir
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se o fabricante não for encontrado
    }
}

}

