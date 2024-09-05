package com.br.IaraGames.controller;

import com.br.IaraGames.beans.Jogos;
import com.br.IaraGames.beans.Fabricante;
import com.br.IaraGames.beans.Categoria;
import com.br.IaraGames.beans.Usuario;
import com.br.IaraGames.dao.JogosDao;
import com.br.IaraGames.dao.FabricanteDao;
import com.br.IaraGames.dao.CategoriaDao;
import com.br.IaraGames.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/jogos")
@CrossOrigin("*")
public class JogosController {
    @Autowired
    private JogosDao jogosDao;

    @Autowired
    private FabricanteDao fabricanteDao;

    @Autowired
    private CategoriaDao categoriaDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @GetMapping
    public List<Jogos> getAllJogos() {
        Iterable<Jogos> jogosIterable = jogosDao.findAll();
        return StreamSupport.stream(jogosIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/fabricantes")
    public List<Fabricante> getFabricantes() {
        return fabricanteDao.findAll();
    }

    @GetMapping("/categorias")
    public List<Categoria> getCategorias() {
        return categoriaDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogos> getJogosById(@PathVariable Integer id) {
        Optional<Jogos> jogo = jogosDao.findById(id);
        return jogo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Jogos> criarJogos(@RequestBody Jogos jogos) {
        // Verifica se o fabricante, a categoria e o usuário existem
        Optional<Fabricante> fabricante = fabricanteDao.findById(jogos.getFabricante().getId());
        Optional<Categoria> categoria = categoriaDao.findById(jogos.getCategoria().getId());

        if (fabricante.isPresent() && categoria.isPresent()) {
            jogos.setFabricante(fabricante.get());
            jogos.setCategoria(categoria.get());
            Jogos savedJogos = jogosDao.save(jogos);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedJogos);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogos> atualizarJogos(@PathVariable Integer id, @RequestBody Jogos jogos) {
        if (jogosDao.existsById(id)) {
            // Verifica se o fabricante, a categoria e o usuário existem
            Optional<Fabricante> fabricante = fabricanteDao.findById(jogos.getFabricante().getId());
            Optional<Categoria> categoria = categoriaDao.findById(jogos.getCategoria().getId());

            if (fabricante.isPresent() && categoria.isPresent()) {
                jogos.setId(id);
                jogos.setFabricante(fabricante.get());
                jogos.setCategoria(categoria.get());
                Jogos updatedJogos = jogosDao.save(jogos);
                return ResponseEntity.ok(updatedJogos);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogos(@PathVariable Integer id) {
        if (jogosDao.existsById(id)) {
            jogosDao.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

