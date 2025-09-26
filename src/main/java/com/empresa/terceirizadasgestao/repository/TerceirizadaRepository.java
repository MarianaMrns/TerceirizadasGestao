package com.empresa.terceirizadasgestao.repository;

import com.empresa.terceirizadasgestao.model.Terceirizada;

import java.util.List;
import java.util.Optional;

public interface TerceirizadaRepository {
    Terceirizada save(Terceirizada t);
    Optional<Terceirizada> findById(String id);
    List<Terceirizada> findAll();
    void deleteById(String id);
}