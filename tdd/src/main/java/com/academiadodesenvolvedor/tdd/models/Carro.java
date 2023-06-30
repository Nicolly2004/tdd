package com.academiadodesenvolvedor.tdd.models;

import com.academiadodesenvolvedor.tdd.api.dtos.CarroDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carros")
public class Carro {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @NonNull
     private String marca;
     @NonNull
     private String modelo;
     @NonNull
     private int ano;
     @NonNull
     private String combustivel;
     @NonNull
     private String cor;

     public CarroDTO toDTO(){
          return new CarroDTO(
                  this.getId(),
                  this.getMarca(),
                  this.getModelo(),
                  this.getAno(),
                  this.getCombustivel(),
                  this.getCor()
          );
     }
}
