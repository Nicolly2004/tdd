package com.academiadodesenvolvedor.tdd.services;


import com.academiadodesenvolvedor.tdd.exceptions.NotFoundException;
import com.academiadodesenvolvedor.tdd.models.Carro;
import com.academiadodesenvolvedor.tdd.repositories.CarroRepository;
import com.academiadodesenvolvedor.tdd.services.contratos.CarroServiceContrato;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CarroServiceTest {

  @SpyBean
    private CarroServiceContrato carroService;

  @MockBean
  private CarroRepository repository;
    @Test
    @DisplayName("Testa se a service de carro está salvando corretamente")
    public void SalvarCarroTest(){

        Carro carro = this.criaCarro();
        Carro carroSaved = this.criaCarro();
        carroSaved.setId(1L);
        Mockito.when(this.repository.save(carro)).thenReturn(carroSaved);
         carro = carroService.salvar(carro);

        Assertions.assertNotNull(carro.getId());
        Assertions.assertNotEquals(0L,carro.getId());
    }

    @Test
    @DisplayName("Testa se a service de carro está listando corretamente")
    public void listarCarrosTest(){
        Carro carroSaved = this.criaCarro();
        carroSaved.setId(1L);
        Mockito.when(this.repository.findAll()).thenReturn(Arrays.asList(carroSaved));
        List<Carro> listagem = carroService.listarCarros();

        Assertions.assertTrue(listagem.size() != 0);

        Carro carro = listagem.get(0);
        Assertions.assertNotNull(carro.getId());
        Assertions.assertNotEquals(0L,carro.getId());
    }
    @Test
    @DisplayName("Testa se a service de carro esrá listando por modelo")
    public void listaCarroPorModeloTest(){
        Mockito.when(this.repository.findAllByMarca("Chevrolet")).thenReturn(Arrays.asList(this.criaCarro()));
        List<Carro> listagem = carroService.listarCarrosPorMarca("Chevrolet");

        Assertions.assertTrue(listagem.size() > 0);
    }

    @Test
    @DisplayName("Testa se a service de carro está buscando por Id")
    public void buscaCarroPorIdTest(){
        Optional<Carro> carroOptional = Optional.of(this.criaCarro());
        Mockito.when(this.repository.findById(1L)).thenReturn(carroOptional);
        Carro carro = carroService.buscaPorId(1L);

        Assertions.assertNotNull(carro);
        Assertions.assertNotEquals(0L,carro.getId());
    }

    @Test
    @DisplayName("Testa se a service de carro está atualizando")
    public void atualizaCarroTest(){

        Carro carroSaved = this.criaCarro();
        carroSaved.setId(1L);
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(carroSaved));

        Carro carro = carroService.buscaPorId(1L);
        carro.setCor("amarelo");
        Mockito.when(this.repository.save(carro)).thenReturn(carro);
        carro = carroService.atualizaCarro(carro);
        Mockito.verify(this.repository,Mockito.times(1)).save(carro);

        Assertions.assertNotEquals("Cinza",carro.getCor());
    }

    @Test
    @DisplayName("Testa se a service está apagando corretamente")
    public void apagaCarroTest(){
        Carro carroSaved = this.criaCarro();
        carroSaved.setId(1L);
        Mockito.when(this.repository.findById(1L))
                .thenReturn(Optional.of(carroSaved));

        carroService.apagarCarro(1L);

        Mockito.when(this.repository.findById(1L)).thenReturn(
                Optional.empty() );
        Assertions.assertThrows(NotFoundException.class,() ->{
            carroService.buscaPorId(1L);

        });
    }



    @Test
    @DisplayName("Testa se a sobrecarga do Listar Carro está funcionando corretamente")
    public void ListarCarroPaginadoTest(){
        Pageable page = PageRequest.of(0,15);
        Mockito.when(this.repository.findAll(page)).thenReturn(new PageImpl<>(Arrays.asList(this.criaCarro())));

        Page<Carro> carroPage = carroService.listarCarros(page);

        Assertions.assertTrue(carroPage.hasContent());
    }

    @Test
    @DisplayName("Testa se a busca por Id lança Exceção")
    public void deveLancarExcecao(){

        Assertions.assertThrows(NotFoundException.class, () ->{
            this.carroService.buscaPorId(0L);

        });

    }



     @BeforeEach
    public void CadastraCarro(){
         Carro carro = new Carro("Chevrolet","Onix",2023,"Flex","Cinza");

         carroService.salvar(carro);

     }

     private Carro criaCarro(){
        return  new Carro("Chevrolet","Onix",2023,"Flex","Cinza");
     }
}
