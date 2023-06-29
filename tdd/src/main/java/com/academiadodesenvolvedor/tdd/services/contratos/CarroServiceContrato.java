package com.academiadodesenvolvedor.tdd.services.contratos;

import com.academiadodesenvolvedor.tdd.models.Carro;

import java.util.List;
import java.util.Optional;

public interface CarroServiceContrato {

    public Carro salvar(Carro carro);

    public List<Carro> listarCarros();

    List<Carro> listarCarrosPorMarca(String marca);

    Carro buscaPorId(long id);

    Carro atualizaCarro(Carro carro);
}
