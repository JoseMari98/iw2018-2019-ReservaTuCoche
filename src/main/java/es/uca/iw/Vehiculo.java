package es.uca.iw;

import java.util.ArrayList;
import java.util.Set;

public class Vehiculo {
    private int id, precio;
    private String matricula = "", marca = "", modelo = "";
    private ArrayList<Reserva> reservas;

    //Getters
    public int getId() {
        return id;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public int getPrecio() {
        return precio;
    }

    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    //Setters
    public void setId(int Id) {
        this.id = Id;
    }

    public void setReservas(ArrayList<Reserva> Reservas) {
        this.reservas = Reservas;
    }

    public void setPrecio(int Precio) {
        this.precio = Precio;
    }

    public void setMarca(String Marca) {
        this.marca = Marca;
    }

    public void setMatricula(String Matricula) {
        this.matricula = Matricula;
    }

    public void setModelo(String Modelo) {
        this.modelo = Modelo;
    }
}