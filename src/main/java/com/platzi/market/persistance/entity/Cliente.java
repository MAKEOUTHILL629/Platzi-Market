package com.platzi.market.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id
    private String id;

    private String nombre;

    private String apellidos;

    private String celular;

    private String direccion;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @OneToMany(mappedBy = "cliente")//esta relacion esta respaldada por id_cliente
    private List<Compra> compras;


}
