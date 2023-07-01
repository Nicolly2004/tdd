package com.academiadodesenvolvedor.tdd.services;

import com.academiadodesenvolvedor.tdd.exceptions.NotFoundException;
import com.academiadodesenvolvedor.tdd.models.Carro;
import com.academiadodesenvolvedor.tdd.repositories.CarroRepository;
import com.academiadodesenvolvedor.tdd.services.contratos.CarroServiceContrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService implements CarroServiceContrato {

    @Autowired
    private CarroRepository carroRepository;
    @Override
    public Carro salvar(Carro carro){
        return  carroRepository.save(carro);
    }

    @Override
    public List<Carro> listarCarros(){
        return carroRepository.findAll();
    }

    @Override
    public List<Carro> listarCarrosPorMarca(String marca){
        return carroRepository.findAllByMarca(marca);
    }

    @Override
    public Page<Carro> listarCarros(Pageable page){
        return carroRepository.findAll(page);
    }

    @Override
    public Carro buscaPorId(long id){
        Optional<Carro> carro = carroRepository.findById(id);

        if (carro.isEmpty()){
            throw new NotFoundException("Não foi possível encontrar o carro com o ID: '"+id+"'");
        }
        return carro.get();
    }

    @Override
    public  Carro atualizaCarro(Carro carro){
        return  this.carroRepository.save(carro);
    }

    @Override
    public void apagarCarro(long id){
        Carro carro = buscaPorId(id);

        carroRepository.delete(carro);
    }
}
