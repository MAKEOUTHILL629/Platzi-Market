package com.platzi.market.persistance.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "compras_productos")
@Data
public class ComprasProducto {
    @EmbeddedId//Se usa cuando la llave primaria es compuesta y esta dada por otra clase
    private ComprasProductoPK id;

    private Integer cantidad;

    private Double total;

    private Boolean estado;

    @ManyToOne
    @MapsId("idCompra")//Cuando se guarden en cascada, se sabe a que clave primeria pertenece
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;


    public ComprasProductoPK getId() {
        return id;
    }

    public void setId(ComprasProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
