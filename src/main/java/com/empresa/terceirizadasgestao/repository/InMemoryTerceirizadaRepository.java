package com.empresa.terceirizadasgestao.repository;

import com.empresa.terceirizadasgestao.model.Terceirizada;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTerceirizadaRepository implements TerceirizadaRepository {
    private final Map<String, Terceirizada> store = new ConcurrentHashMap<>();

    @Override
    public Terceirizada save(Terceirizada t) {
        store.put(t.getId(), t);
        return t;
    }

    @Override
    public Optional<Terceirizada> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Terceirizada> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }
}
