/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo7.clase;

import java.util.Iterator;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * @author franmatias
 */
public class Utils {

    /**
     *
     * @param alumnos
     * @param edad
     */
    public static void imprimirAlumnosMayoresDe(Collection<Alumno> alumnos, int edad) {
        Iterator<Alumno> it = alumnos.iterator();
        while (it.hasNext()) {
            Alumno alumno = it.next();
            if (alumno.getEdad() >= edad) {
                System.out.println(alumno);
            }
        }
    }

    /**
     *
     * @param alumnos
     * @param edadMin
     * @param edadMax
     */
    public static void imprimirAlumnosEnRangoEdad(Collection<Alumno> alumnos, int edadMin, int edadMax) {
        Iterator<Alumno> it = alumnos.iterator();
        while (it.hasNext()) {
            Alumno alumno = it.next();
            if (alumno.getEdad() >= edadMin && alumno.getEdad() <= edadMax) {
                System.out.println(alumno);
            }
        }

    }

    /**
     *
     * @param alumnos
     * @param criterio
     */
    public static void imprimirAlumnos(Collection<Alumno> alumnos, Criterio criterio) {
        Iterator<Alumno> it = alumnos.iterator();
        while (it.hasNext()) {
            Alumno alumno = it.next();
            if (criterio.test(alumno)) {
                System.out.println(alumno);
            }
        }

    }

    /**
     * Criterio de busqueda para alumno
     */
    public interface Criterio {

        boolean test(Alumno alumno);
    }

    public class AlumnosJovenes implements Criterio {

        @Override
        public boolean test(Alumno alumno) {
            return alumno.getEdad() >= 18 && alumno.getEdad() < 35;
        }
    }

    /**
     *
     * @param alumnos
     * @param criterio
     */
    public static void imprimirAlumnosPredicado(Collection<Alumno> alumnos,
            Predicate<Alumno> criterio) {
        Iterator<Alumno> it = alumnos.iterator();
        while (it.hasNext()) {
            Alumno alumno = it.next();
            if (criterio.test(alumno)) {
                System.out.println(alumno);
            }
        }
    }

    /**
     * 
     * @param alumnos
     * @param criterio
     * @param accion 
     */
    public static void procesarAlumnos(Collection<Alumno> alumnos,
                                       Predicate<Alumno> criterio,
                                       Consumer<Alumno> accion) {
        Iterator<Alumno> it = alumnos.iterator();
        while (it.hasNext()) {
            Alumno alumno = it.next();
            if (criterio.test(alumno)) {
                accion.accept(alumno);
            }
        }
    }
    
    /**
     * Procesar Alumnos
     * @param alumnos 
     */
    public static void procesarAlumnos(Collection<Alumno> alumnos){
        alumnos.stream()
               .filter(alumno -> alumno.getEdad() >= 18 && alumno.getEdad() < 35)
               .map(alumno -> alumno.getNotaMedia())
               .forEach(notaMedia -> System.out.println(notaMedia));    
    }

}
