package com.empresa.terceirizadasgestao.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Contrato {
    private final String id;
    private final String terceirizadaId;
    private final LocalDate inicio;
    private final LocalDate fim;
    private final double valor;

    public Contrato(String terceirizadaId, LocalDate inicio, LocalDate fim, double valor) {
        this.id = UUID.randomUUID().toString();
        this.terceirizadaId = terceirizadaId;
        this.inicio = inicio;
        this.fim = fim;
        this.valor = valor;
    }

    public String getId() { return id; }
    public String getTerceirizadaId() { return terceirizadaId; }
    public LocalDate getInicio() { return inicio; }
    public LocalDate getFim() { return fim; }
    public double getValor() { return valor; }

    public boolean ativoEm(LocalDate data) {
        return (inicio.isEqual(data) || inicio.isBefore(data)) &&
               (fim.isEqual(data) || fim.isAfter(data));
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id='" + id + '\'' +
                ", terceirizadaId='" + terceirizadaId + '\'' +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", valor=" + valor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contrato contrato = (Contrato) o;
        return Objects.equals(id, contrato.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
