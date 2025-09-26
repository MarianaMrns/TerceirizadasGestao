package com.empresa.terceirizadasgestao.repository;

import com.empresa.terceirizadasgestao.model.Contrato;

import java.util.List;

public interface ContratoRepository {
    Contrato save(Contrato c);
    List<Contrato> findByTerceirizadaId(String terceirizadaId);
    List<Contrato> findAll();
    void deleteById(String id);
}