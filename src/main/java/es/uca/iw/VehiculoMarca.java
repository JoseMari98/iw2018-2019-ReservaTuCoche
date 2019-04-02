package es.uca.iw;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VehiculoMarca { //habria que cambiar por clase
    //Audi, BMW, Mercedes, Renault, SEAT, Citroen, Chevrolet, Ford, Hyundai, KIA, OPEL, Peugeot, Toyota, Volkswagen, Volvo
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String marca = "";

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
