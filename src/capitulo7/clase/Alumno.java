/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo7.clase;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author franmatias
 */
public class Alumno implements Comparable<Alumno> {
    private String dni;
    private String nombre;
    private LocalDate fechaNacimiento;
    private float notaMedia;

    public Alumno(String dni, String nombre, LocalDate fechaNacimiento, float notaMedia) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.notaMedia = notaMedia;
    }

    /**
     * @return the dni
     */
    public String getDNIAlumno() {
        return dni;
    }

    /**
     * @param dniAlumno
     */
    public void setIdAlumno(String dniAlumno) {
        this.dni = dniAlumno;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the notaMedia
     */
    public float getNotaMedia() {
        return notaMedia;
    }

    /**
     * @param notaMedia the notaMedia to set
     */
    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
        return String.format("Alumno{dni='%-9s' nombre='%-20s'\t, edad=%s\t, notaMedia=%.2f}", dni, nombre, fechaNacimiento, notaMedia);
        
    }
    
    @Override
    public boolean equals(Object otro){
    if (otro == this) {
            return true;
        }

        if (otro == null) {
            return false;
        }

        if (!Alumno.class.isAssignableFrom(otro.getClass())) {
            return false;
        }

        final Alumno otra = (Alumno) otro;

        return ((this.dni.equalsIgnoreCase(otra.dni)));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.dni);
        return hash;
    }


    @Override
    public int compareTo(Alumno o) {
        return dni.compareTo(o.dni);
    }
    
    /**
     * Ordenar por Nombre
     */
    public static class AlumnoOrdenPorNombre implements Comparator<Alumno> {

        @Override
        public int compare(Alumno o1, Alumno o2) {
            return o1.getNombre().compareToIgnoreCase(o2.getNombre());
        }
    }

    /**
     * Ordenar por edad
     */
    public static class AlumnoOrdenPorEdad implements Comparator<Alumno> {

        @Override
        public int compare(Alumno o1, Alumno o2) {
            return o1.getFechaNacimiento().compareTo(o2.getFechaNacimiento());
        }
    }

    /**
     * Ordenar por nota
     */
    public static class AlumnoOrdenPorNota implements Comparator<Alumno> {

        @Override
        public int compare(Alumno o1, Alumno o2) {
            return Float.compare(o1.getNotaMedia(), o2.getNotaMedia());
        }
    }
    
    /**
     * 
     * @return 
     */
    public int getEdad(){
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }
    
    
}
