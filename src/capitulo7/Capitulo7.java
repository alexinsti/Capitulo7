/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo7;

import capitulo7.clase.*;
import capitulo7.clase.Utils.Criterio;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author franmatias
 */
public class Capitulo7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Clase clase = new Clase();
        System.out.println("-------------------- Clase -------------------------");
        System.out.println(clase);
        System.out.println("-------------------- Conjuntos -------------------------");
        clase.conjuntos();
        System.out.println("\n-------------------- Listas -----------------------------");
        clase.listas();
        System.out.println("-------------------- Mapas -----------------------------");
        clase.mapas();

        System.out.println("Uso de los patrones de diseño estrategia y consumidor");
        System.out.println("El patrón estrategia me permite implementar un gran número de"
                + " criterios de busqueda para una clase y de este modo ahorrarnos escribir"
                + " un método por cada busqueda, además si la búsqueda cambia tenemos que "
                + " hacer cambios en el código\n"
                + " Para implementar el patrón Estrategia debemos crear una interfaz con un"
                + " único método: boolean test(Tipo tipo)");
        System.out.println("-----------------------------------------");
        System.out.println("Patrón consumidor: Permite asociar una acción a un criterio de "
                + " búsqueda para poder realizar cualquier acción sobre esos datos."
                + " Este patrón mejora al anterior por que nos permite definir acciones distintas"
                + "para conjunto de datos que cumplan un determinado criterio.");
        clase.alumnosRangoEdad(10, 20);
        List<Alumno> misAlumnos = new LinkedList<>();
        misAlumnos.addAll(Arrays.asList(getDatosClase()));
        Utils.imprimirAlumnos(misAlumnos, (Alumno alumno) -> alumno.getEdad() >= 18 && alumno.getEdad() < 35);

        /*
        Collections.sort(clase.claseLista, new Comparator<Alumno>() {
            @Override
            public int compare(Alumno a1, Alumno a2) {
                return Double.compare(a1.getNotaMedia(), a2.getNotaMedia());
            }
        });
        */
        Collections.sort(clase.claseLista, (Alumno a1, Alumno a2) -> Double.compare(a1.getNotaMedia(), a2.getNotaMedia()));

    }

    public final static Alumno[] getDatosClase() {
        //Array de objetos
        Alumno[] datos = {
            new Alumno("24908113L", "Pepe Martin", LocalDate.of(2000, 2, 20), 8.5f),
            new Alumno("64908113D", "Susana Lopez", LocalDate.of(1988, 3, 15), 6.5f),
            new Alumno("14908113X", "Lola Casas", LocalDate.of(1980, 1, 10), 7.5f),
            new Alumno("94908113R", "Roberto Robles", LocalDate.of(1995, 11, 20), 8.3f),
            new Alumno("12345672Q", "Zoe Valdez", LocalDate.of(1978, 11, 20), 3.3f)
        };

        return datos;
    }

}
