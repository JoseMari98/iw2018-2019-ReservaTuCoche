package es.uca.iw;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class VehiculoMarca { //habria que cambiar por clase
    //Audi, BMW, Mercedes, Renault, SEAT, Citroen, Chevrolet, Ford, Hyundai, KIA, OPEL, Peugeot, Toyota, Volkswagen, Volvo
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(unique = true)
    private String marca = "";
    @OneToMany(mappedBy = "marca")
    private Set<VehiculoTipo> tipo;

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public Set<VehiculoTipo> getTipo() {
        return tipo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setTipo(Set<VehiculoTipo> tipo) {
        this.tipo = tipo;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
