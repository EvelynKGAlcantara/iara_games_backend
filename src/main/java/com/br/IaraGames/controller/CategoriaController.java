package com.br.IaraGames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.IaraGames.beans.Categoria;
import com.br.IaraGames.dao.CategoriaDao;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaDao categoriaDao;

    // Método POST para criar uma nova categoria
    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaDao.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    // Método GET para listar todas as categorias
    @GetMapping
    public Iterable<Categoria> listarCategorias() {
        return categoriaDao.findAll();
    }

    // Método GET para buscar uma categoria pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
        return categoriaDao.findById(id)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Método PUT para atualizar uma categoria existente
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        // Verifica se a categoria com o ID informado existe
        if (categoriaDao.existsById(id)) {
            categoria.setId(id); // Define o ID para garantir que estamos atualizando a categoria correta
            Categoria categoriaAtualizada = categoriaDao.save(categoria);
            return ResponseEntity.ok(categoriaAtualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se a categoria não for encontrada
        }
    }

    // Método DELETE para excluir uma categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
        // Verifica se a categoria com o ID informado existe
        if (categoriaDao.existsById(id)) {
            categoriaDao.deleteById(id); // Deleta a categoria
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Retorna 204 (No Content) após excluir
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se a categoria não for encontrada
        }
    }
}
