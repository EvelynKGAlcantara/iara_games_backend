package com.br.IaraGames.dao;

import com.br.IaraGames.beans.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraDao extends JpaRepository<Compra, Integer> {
}
