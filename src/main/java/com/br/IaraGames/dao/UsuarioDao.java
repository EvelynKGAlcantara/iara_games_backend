package com.br.IaraGames.dao;

import com.br.IaraGames.beans.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
    Optional<Usuario> findByNome(String nome);
    Optional<Usuario> findByEmail(String email);
}