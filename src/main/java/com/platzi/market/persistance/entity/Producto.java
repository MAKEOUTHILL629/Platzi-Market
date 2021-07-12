package com.platzi.market.persistance.entity;


import lombok.Data;

import javax.persistence.*;

@Entity//Esta anotacion es para dar a entender a java que esta clase mapea una tabla en la base de datos
@Table(name = "productos")//como la tabla se llama diferente en la base de datos, cuando se hable de producto se referencia a la tabla productos
//@Data//Utilizando lombok
public class Producto {
    @Id//Como es la clave primaria y es sencilla
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Permite que java genere el valor de la llave primaria de manera automatica
    @Column(name = "id_producto")//como se le cambio el nombre de la columna en la base de datos, hacemos referencia con la anotacion
    private Integer idProducto;

    private String nombre;//como se llama en la tabla de la misma manera, no es necesario anotarlo

    @Column(name = "id_categoria")//nombre de la columna en la base datos
    private Integer idCategoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;//como se llama en la tabla de la misma manera, no es necesario anotarlo

    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)//Los atributos que se colocaron se refiere a que desde producto no se va a crear una nueva categoria, se debe crear desde categoria
    private Categoria categoria;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
