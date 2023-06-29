package com.academiadodesenvolvedor.tdd.services;


import com.academiadodesenvolvedor.tdd.models.Carro;
import com.academiadodesenvolvedor.tdd.services.contratos.CarroServiceContrato;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CarroServiceTest {
  @Autowired
    private CarroServiceContrato carroService;

    @Test
    @DisplayName("Testa se a service de carro está salvando corretamente")
    public void SalvarCarroTest(){
        Carro carro = new Carro("Chevrolet","Onix",2023,"Flex","Cinza");

        carro = carroService.salvar(carro);

        Assertions.assertNotNull(carro.getId());
        Assertions.assertNotEquals(0L,carro.getId());
    }

    @Test
    @DisplayName("Testa se a service de carro está listando corretamente")
    public void listarCarrosTest(){
        List<Carro> listagem = carroService.listarCarros();

        Assertions.assertTrue(listagem.size() != 0);
        Carro carro = listagem.get(0);
        Assertions.assertNotNull(carro.getId());
        Assertions.assertNotEquals(0L,carro.getId());
    }
    @Test
    @DisplayName("Testa se a service de carro esrá listando por modelo")
    public void listaCarroPorModeloTest(){
        List<Carro> listagem = carroService.listarCarrosPorMarca("Chevrolet");

        Assertions.assertTrue(listagem.size() > 0);
    }

    @Test
    @DisplayName("Testa se a service de carro está buscando por Id")
    public void buscaCarroPorIdTest(){
        Carro carro = carroService.buscaPorId(1L);

        Assertions.assertNotNull(carro);
        Assertions.assertNotEquals(0L,carro.getId());
    }

    @Test
    @DisplayName("Testa se a service de carro está atualizando")
    public void atualizaCarroTest(){
        Carro carro = carroService.buscaPorId(1L);

        carro.setCor("amarelo");

        carro = carroService.atualizaCarro(carro);

        Assertions.assertNotEquals("Cinza",carro.getCor());
    }
     @BeforeEach
    public void CadastraCarro(){
         Carro carro = new Carro("Chevrolet","Onix",2023,"Flex","Cinza");

         carroService.salvar(carro);

     }
}
