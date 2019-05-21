package es.uca.iw;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class VehiculoTipo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(unique = true)
    private String tipo = "";
    @ManyToOne
    private VehiculoMarca marca;

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public VehiculoMarca getMarca() {
        return marca;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMarca(VehiculoMarca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}