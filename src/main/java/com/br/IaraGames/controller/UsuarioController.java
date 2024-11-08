// package com.br.IaraGames.controller;

// import com.br.IaraGames.beans.Usuario;
// import com.br.IaraGames.dao.UsuarioDao;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Optional;

// @RestController
// @RequestMapping("/usuario")
// @CrossOrigin("*")
// public class UsuarioController {

//     @Autowired
//     private UsuarioDao usuarioDao;

//     @PostMapping("/login")
//     public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
//         Optional<Usuario> foundUser = usuarioDao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
//         return foundUser.map(ResponseEntity::ok)
//                 .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//     }

//     @GetMapping
//     public Iterable<Usuario> getAllUsuarios() {
//         return usuarioDao.findAll();
//     }

//     @PostMapping
//     public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
//         Usuario savedUsuario = usuarioDao.save(usuario);
//         return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
//         if (usuarioDao.existsById(id)) {
//             usuario.setId(id); // Garante que o ID do usuário está correto
//             Usuario updatedUsuario = usuarioDao.save(usuario);
//             return ResponseEntity.ok(updatedUsuario);
//         } else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
//         if (usuarioDao.existsById(id)) {
//             usuarioDao.deleteById(id);
//             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//         } else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//         }
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
//         Optional<Usuario> usuario = usuarioDao.findById(id);
//         return usuario.map(ResponseEntity::ok)
//                 .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//     }
// }

///// -----------------------------------

package com.br.IaraGames.controller;

import com.br.IaraGames.beans.Usuario;
import com.br.IaraGames.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Usuario usuario) {
        // Busca pelo usuário com email e senha fornecidos
        Optional<Usuario> foundUser = usuarioDao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());

        // Resposta ao frontend em caso de login bem-sucedido ou falho
        if (foundUser.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Login realizado com sucesso!");
            response.put("usuario", foundUser.get()); // Retorna o objeto Usuario para uso no frontend
            
            return ResponseEntity.ok(response);
        } else {
            // Agora usando Map<String, Object> para evitar incompatibilidade
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", "Usuário ou senha inválidos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    // Outros métodos permanecem inalterados

    @GetMapping
    public Iterable<Usuario> getAllUsuarios() {
        return usuarioDao.findAll();
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioDao.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        if (usuarioDao.existsById(id)) {
            usuario.setId(id); // Garante que o ID do usuário está correto
            Usuario updatedUsuario = usuarioDao.save(usuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        if (usuarioDao.existsById(id)) {
            usuarioDao.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioDao.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
