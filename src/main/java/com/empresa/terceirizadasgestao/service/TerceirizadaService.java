package com.empresa.terceirizadasgestao.service;

import com.empresa.terceirizadasgestao.model.Contrato;
import com.empresa.terceirizadasgestao.model.Terceirizada;
import com.empresa.terceirizadasgestao.repository.ContratoRepository;
import com.empresa.terceirizadasgestao.repository.TerceirizadaRepository;
import com.empresa.terceirizadasgestao.util.ValidationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TerceirizadaService {
    private final TerceirizadaRepository terceirizadaRepo;
    private final ContratoRepository contratoRepo;

    public TerceirizadaService(TerceirizadaRepository terceirizadaRepo, ContratoRepository contratoRepo) {
        this.terceirizadaRepo = terceirizadaRepo;
        this.contratoRepo = contratoRepo;
    }

    public Terceirizada cadastrarTerceirizada(String nome, String cnpj) {
        validateCnpj(cnpj);
        Terceirizada t = new Terceirizada(nome, cnpj);
        return terceirizadaRepo.save(t);
    }

    public Contrato contratar(String terceirizadaId, LocalDate inicio, LocalDate fim, double valor) {
        if (valor <= 0) {
            throw new ValidationException("Valor do contrato deve ser maior que zero.");
        }
        Optional<Terceirizada> t = terceirizadaRepo.findById(terceirizadaId);
        if (t.isEmpty()) {
            throw new ValidationException("Terceirizada não encontrada: " + terceirizadaId);
        }
        if (fim.isBefore(inicio)) {
            throw new ValidationException("Data de fim anterior à data de início.");
        }
        Contrato c = new Contrato(terceirizadaId, inicio, fim, valor);
        return contratoRepo.save(c);
    }

    public List<Contrato> contratosAtivos(String terceirizadaId, LocalDate data) {
        return contratoRepo.findByTerceirizadaId(terceirizadaId)
                .stream()
                .filter(c -> c.ativoEm(data))
                .collect(Collectors.toList());
    }

    public double valorTotalContratos(String terceirizadaId) {
        return contratoRepo.findByTerceirizadaId(terceirizadaId)
                .stream()
                .mapToDouble(Contrato::getValor)
                .sum();
    }

    public List<Terceirizada> listarTerceirizadas() {
        return terceirizadaRepo.findAll();
    }

    private void validateCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new ValidationException("CNPJ inválido.");
        }
        // validação simples de formato (apenas exemplo)
        String digits = cnpj.replaceAll("\\D", "");
        if (digits.length() != 14) {
            throw new ValidationException("CNPJ deve ter 14 dígitos (apenas números).");
        }
    }
}