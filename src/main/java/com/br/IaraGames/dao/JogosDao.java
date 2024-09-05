package com.br.IaraGames.dao;

import com.br.IaraGames.beans.Jogos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JogosDao extends CrudRepository<Jogos, Integer> {
    Optional<Jogos> findByNome(String nome);
    Optional<Jogos> findByTipoMidia(String tipoMidia);
    Optional<Jogos> findByPlataforma(String plataforma);
    Optional<Jogos> findByFabricanteNome(String fabricanteNome);
}