package com.br.IaraGames.dao;

import com.br.IaraGames.beans.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteDao extends JpaRepository<Fabricante, Integer> {
}