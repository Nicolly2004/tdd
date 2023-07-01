package com.academiadodesenvolvedor.tdd.services.contratos;

import com.academiadodesenvolvedor.tdd.models.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CarroServiceContrato {

     Carro salvar(Carro carro);

     List<Carro> listarCarros();

     Page<Carro> listarCarros(Pageable page);

    List<Carro> listarCarrosPorMarca(String marca);

    Carro buscaPorId(long id);

    Carro atualizaCarro(Carro carro);

    void apagarCarro(long id);
}
