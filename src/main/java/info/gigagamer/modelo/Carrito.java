package info.gigagamer.modelo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Carrito")
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "Carrito_Producto",
            joinColumns = @JoinColumn(name = "id_carrito"),
            inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
    private List<Producto> productos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "Carrito_Producto", joinColumns = @JoinColumn(name = "id_carrito"))
    @Column(name = "cantidad")
    private List<Integer> cantidades = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) {
        if (productos.contains(producto)) {
            int index = productos.indexOf(producto);
            cantidades.set(index, cantidades.get(index) + cantidad);
        } else {
            productos.add(producto);
            cantidades.add(cantidad);
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Integer> getCantidades() {
        return cantidades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void setCantidades(List<Integer> cantidades) {
        this.cantidades = cantidades;
    }

}