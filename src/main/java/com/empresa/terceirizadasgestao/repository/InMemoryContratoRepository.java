package com.empresa.terceirizadasgestao.repository;

import com.empresa.terceirizadasgestao.model.Contrato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryContratoRepository implements ContratoRepository {
    private final List<Contrato> store = new CopyOnWriteArrayList<>();

    @Override
    public Contrato save(Contrato c) {
        store.add(c);
        return c;
    }

    @Override
    public List<Contrato> findByTerceirizadaId(String terceirizadaId) {
        List<Contrato> out = new ArrayList<>();
        for (Contrato c : store) {
            if (c.getTerceirizadaId().equals(terceirizadaId)) {
                out.add(c);
            }
        }
        return out;
    }

    @Override
    public List<Contrato> findAll() {
        return Collections.unmodifiableList(store);
    }

    @Override
    public void deleteById(String id) {
        store.removeIf(c -> c.getId().equals(id));
    }
}