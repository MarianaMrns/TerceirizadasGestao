package com.empresa.terceirizadasgestao.model;

import java.util.Objects;
import java.util.UUID;

public class Terceirizada {
    private final String id;
    private String nome;
    private String cnpj;

    public Terceirizada(String nome, String cnpj) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public String getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    @Override
    public String toString() {
        return "Terceirizada{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Terceirizada that = (Terceirizada) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}