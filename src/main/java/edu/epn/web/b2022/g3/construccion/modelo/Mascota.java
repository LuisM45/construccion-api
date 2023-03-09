package edu.epn.web.b2022.g3.construccion.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mascota")
public class Mascota implements Serializable {
    public enum Genero{MACHO,HEMBRA};
    public enum Raza{Pura,Runa,Indecente};
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "raza")
    private String raza;
    
    @Column(name = "genero")
    private Genero genero;

    public Mascota() {
    }

    public Mascota(String nombre, String raza, Genero genero) {
        this.nombre = nombre;
        this.raza = raza;
        this.genero = genero;
    }

    public Mascota(Integer id, String nombre, String raza, Genero genero) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.genero = genero;
    }

    
    
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    
}
