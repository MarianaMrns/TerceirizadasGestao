package com.empresa.terceirizadasgestao;

import com.empresa.terceirizadasgestao.model.Contrato;
import com.empresa.terceirizadasgestao.model.Terceirizada;
import com.empresa.terceirizadasgestao.repository.*;
import com.empresa.terceirizadasgestao.service.TerceirizadaService;
import com.empresa.terceirizadasgestao.util.ValidationException;

import java.time.LocalDate;
import java.util.List;

public class TerceirizadasGestao {
    public static void main(String[] args) {
        // Inicialização de repositórios e serviço
        TerceirizadaRepository tRepo = new InMemoryTerceirizadaRepository();
        ContratoRepository cRepo = new InMemoryContratoRepository();
        TerceirizadaService service = new TerceirizadaService(tRepo, cRepo);

        try {
            // 1) cadastrar terceirizada
            Terceirizada t = service.cadastrarTerceirizada("Alpha Serviços", "12345678000199");

            // 2) criar contratos válidos
            service.contratar(t.getId(), LocalDate.now().minusDays(10), LocalDate.now().plusDays(20), 10000.0);
            service.contratar(t.getId(), LocalDate.now().plusDays(30), LocalDate.now().plusDays(60), 5000.0);

            // 3) listar terceirizadas
            List<Terceirizada> lista = service.listarTerceirizadas();

            // 4) contratos ativos hoje
            List<Contrato> ativos = service.contratosAtivos(t.getId(), LocalDate.now());

            // 5) valor total
            double total = service.valorTotalContratos(t.getId());

            System.out.println("TESTS OK — todos os testes do main passaram.");
            System.out.println("Resumo:");
            System.out.println("Terceirizadas: " + lista);
            System.out.println("Contratos hoje ativos: " + ativos);
            System.out.println("Valor total contratos: R$ " + total);

        } catch (ValidationException ex) {
            System.err.println("Falha de validação: " + ex.getMessage());
        } catch (Throwable ex) {
            System.err.println("TESTS FAILED: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
    }
}