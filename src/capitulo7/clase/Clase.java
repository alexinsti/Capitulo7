/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo7.clase;

import capitulo7.clase.Alumno.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author franmatias
 */
public class Clase {

    //Conjuntos
    Set<Alumno> claseConjunto = new HashSet<>();

    //Listas
    public List<Alumno> claseLista = new ArrayList<>();

    //Mapas
    Map<String, Alumno> claseMapa = new HashMap<>();
    Map<Alumno, Integer> alumnosMapa = new HashMap<>();

    //Crear un comparador de valores genérico, para mapas
    private ComparadorValoresMapas<Alumno, Integer> bva = new ComparadorValoresMapas<>(alumnosMapa);
    private TreeMap<Alumno, Integer> listaAlumnosOrdenada = new TreeMap<>(bva);
    
    public Clase() {
        claseConjunto.addAll(Arrays.asList(getDatosAlumnos()));
        claseLista.addAll(Arrays.asList(getDatosAlumnos()));

        //Convertir una colección en una lista
        claseMapa = listaComoMapa(claseLista, new ConvertidorListaAMapa<String, Alumno>() {
            @Override
            public String getKey(Alumno item) {
                return item.getDNIAlumno();
            }
        });
        
    }

    /**
     * Datos de prueba
     *
     * @return
     */
    public final Alumno[] getDatosAlumnos() {
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

    /**
     * Pasa a cadena una clase de Alumnos
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Iterator<Alumno> it = claseLista.iterator();
        while (it.hasNext()) {
            Alumno al = it.next();
            result.append(al).append("\n");
        }
        return result.toString();
    }

    /**
     * Uso de conjuntos
     */
    public void conjuntos() {
        //Conjuntos  
        System.out.println("\nLinkedHashSet. Se insertan según el orden de inserción");
        claseConjunto = new LinkedHashSet(Arrays.asList(getDatosAlumnos()));
        imprimirColeccion(claseConjunto);
        
        System.out.println("\nTreeSet. Se insertan de forma ordenada, de acuerdo a la implementación comparable de alumno");
        claseConjunto = new TreeSet(Arrays.asList(getDatosAlumnos()));
        imprimirColeccion(claseConjunto);
        
        claseConjunto = new HashSet(Arrays.asList(getDatosAlumnos()));
        System.out.println("\nHashSet. Se van insertando en la coleccion internamente sin un orden especifico");
        System.out.println("Esta clase realizan un ordenamiento interno mediante el hashcode de el elemento(Objeto), por lo cual no sabremos que elemento traerá");
        imprimirColeccion(claseConjunto);

        //Añadir elementos a partir de otra colección como una lista
        ArrayList<Alumno> c2 = new ArrayList<>(Arrays.asList(
                new Alumno("12434348L", "Albert Einstein", LocalDate.of(2000, 2, 20), 3.5f),
                new Alumno("14908113M", "Bart Simpson", LocalDate.of(1990, 1, 28), 1.5f)
        ));
        //Añadimos dos alumnos contenidos en una lista
        claseConjunto.addAll(c2);
        System.out.println("\nAñadir dos alumnos de una lista");
        imprimirColeccion(claseConjunto);

        //Operaciones
        //Insertar un alumno
        System.out.println("\nAñadir alumno");
        claseConjunto.add(new Alumno("03908113L", "Bartolo Mendez", LocalDate.of(1999, 7, 20), 9.5f));
        imprimirColeccion(claseConjunto);
        //Insertar alumno repetido
        System.out.println("\nAñadir alumno repetido. No se añade");
        claseConjunto.add(new Alumno("03908113L", "Bartolo Mendez", LocalDate.of(1999, 7, 20), 9.5f));
        imprimirColeccion(claseConjunto);

        //Comprobar si un objeto alumno existe
        Alumno alumno = new Alumno("03908113L", "Bartolo Mendez", LocalDate.of(1999, 7, 20), 9.5f);
        boolean estaAlumno = claseConjunto.contains(alumno);
        System.out.println("\n¿El alumno " + alumno + " existe? " + (estaAlumno ? "Sí" : "No"));
        //Un alumno que no existe
        alumno = new Alumno("13908113L", "Bartolo Mendez", LocalDate.of(1999, 7, 20), 9.5f);
        estaAlumno = claseConjunto.contains(alumno);
        System.out.println("¿El alumno " + alumno + " existe? " + (estaAlumno ? "Sí" : "No"));
        System.out.println("\nCalcula la intersección de dos conjuntos. Calcularmos la diferencia con:  ");
        imprimirColeccion(c2);
        claseConjunto.retainAll(c2);
        System.out.println("Resultado:");
        imprimirColeccion(claseConjunto);
        System.out.println("\nEliminar alumno Albert Einstein");
        claseConjunto.remove(new Alumno("12434348L", "Albert Einstein", LocalDate.of(2000, 2, 20), 3.5f));
        imprimirColeccion(claseConjunto);

        //Orden en conjuntos
        claseConjunto = new TreeSet(Arrays.asList(getDatosAlumnos()));
        System.out.println("Reiniciamos el conjunto");
        
        System.out.println("\nCreamos un conjunto SortedSet de alumnos con un TreeSet,");
        System.out.println(" un conjunto que permite ordenar. ");
        System.out.println("Inicialmente se ordena por el DNI");
        SortedSet<Alumno> conjuntoOrdenado = new TreeSet<>();
        conjuntoOrdenado.addAll(claseConjunto);
        imprimirColeccion(conjuntoOrdenado);
        
        System.out.println("\nCambiamos el tipo de orden. Ordenamos por edad");
        conjuntoOrdenado = new TreeSet<>(new AlumnoOrdenPorEdad());
        conjuntoOrdenado.addAll(claseConjunto);
        imprimirColeccion(conjuntoOrdenado);
        
        System.out.println("\nIncluso podemos invertir el orden");
        conjuntoOrdenado = new TreeSet<>(new AlumnoOrdenPorEdad().reversed());
        conjuntoOrdenado.addAll(claseConjunto);
        imprimirColeccion(conjuntoOrdenado);
        
        System.out.println("\nTambién nos permiten obtener un subconjunto de Alumnos desde... hasta: ");
        
        imprimirColeccion(conjuntoOrdenado.subSet(
                new Alumno("24908113L", "Pepe Martin", LocalDate.of(2000, 2, 20), 8.5f),
                conjuntoOrdenado.last()
        ));

        //Ver el orden que ocupan algunos elementos con relación a otro
        Alumno alum = new Alumno("64908113L", "Pepe Lopez", LocalDate.of(1988, 3, 15), 6.5f);
        System.out.println("Primer/os Alumno/s antes de " + alum + " son " + conjuntoOrdenado.headSet(alum));
        System.out.println("Último/s Alumno/s después de " + alum + " son " + conjuntoOrdenado.tailSet(alum));
        
    }

    /**
     * Uso de listas
     */
    public void listas() {
        //Iniciar una lista a través de un array usando el constructor
        LinkedList<Alumno> c1;
        System.out.println("Iniciando lista con alumnos...");
        c1 = new LinkedList<>(Arrays.asList(getDatosAlumnos()));
        imprimirColeccion(c1);
        
        System.out.println("\nAñadiendo datos");
        System.out.println("Las listas permiten repetidos...");
        c1.add(new Alumno("03908113L", "Bartolo Mendez", LocalDate.of(1999, 7, 20), 9.5f));   
        imprimirColeccion(c1);
        
        ArrayList<Alumno> c2 = new ArrayList<>(Arrays.asList(
                new Alumno("12434348L", "Albert Einstein", LocalDate.of(2000, 2, 20), 3.5f),
                new Alumno("14908113M", "Bart Simpson", LocalDate.of(1990, 1, 28), 1.5f),
                new Alumno("33908113L", "Zoe Baldes", LocalDate.of(1997, 2, 20), 8.5f),
                new Alumno("24908113L", "Pepe Martin", LocalDate.of(2000, 2, 20), 8.5f)
        ));
        imprimirColeccion(c2);
        
        c2.addAll(Arrays.asList(
                new Alumno("56434348L", "Jorge Viejo", LocalDate.of(2000, 2, 20), 10f),
                new Alumno("78908113M", "Yolanda Suarez", LocalDate.of(1990, 1, 28), 8f)
        ));
        
        System.out.println("\nclaseLista");
        claseLista.addAll(c1);
        claseLista.addAll(c2);
        imprimirColeccion(claseLista);
        
        Collections.sort(claseLista);
        System.out.println("\nEliminar repetidos...");
        /*
        //Usando conjuntos
        Set<Alumno> aux = new HashSet<>(claseLista); 
        claseLista.clear();
        claseLista.addAll(aux);
        */
        claseLista = claseLista.stream().distinct().collect(Collectors.toList());
        imprimirColeccion(claseLista);
        
        //Ordenar 
        System.out.println("Ordenar listas: ");
        System.out.println("Orden por defecto...");
        Collections.sort(claseLista); //Por defecto por dni
        imprimirColeccion(claseLista);
        System.out.println("\nInvertiendo orden...");
        Collections.reverse(claseLista);
        imprimirColeccion(claseLista);
        System.out.println("\nOrdenando por edad...");
        Collections.sort(claseLista, new AlumnoOrdenPorEdad()); //Alumno por edad
        imprimirColeccion(claseLista);
        System.out.println("\nOrdenando por nota...");
        Collections.sort(claseLista, new AlumnoOrdenPorNota()); //Alumno por nota
        imprimirColeccion(claseLista);
    }

    /**
     * Imprime una coleccion de Alumnos
     *
     * @param coleccion
     */
    public static void imprimirColeccion(Collection<Alumno> coleccion) {
        Iterator<Alumno> it = coleccion.iterator();
        
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        /*
         for (Iterator<Alumno> it = lista.iterator(); it.hasNext();) {
         System.out.println(it.next());
         }

         for (Alumno alumno : lista) {
         System.out.println(alumno);
         }
         */
    }

    /**
     * Uso de mapas
     */
    public void mapas() {
        claseMapa = new HashMap<>();
        for (Alumno alum : getDatosAlumnos()) {
            claseMapa.put(alum.getDNIAlumno(), alum);
        }
        imprimirMapa(claseMapa);
        claseMapa = new LinkedHashMap<>();
        //mapaAlumnos = new HashMap<>(new AlumnoOrdenPorNota()); //No puede hacerce ni con LinkedHashMap
        //mapaClase = new TreeMap<>(new AlumnoOrdenPorNota());
        //Collections.sort(mapaClase, new AlumnoOrdenPorNota());

    }

    /**
     * Imprime un mapa
     *
     * @param mapa
     */
    public static void imprimirMapa(Map<String, Alumno> mapa) {
        for (Map.Entry<String, Alumno> entrada : mapa.entrySet()) {
            System.out.println(entrada.getKey() + "/" + entrada.getValue());
        }
        
    }

    /**
     * Convierte una colección en un mapa
     *
     * @param <K> clave del mapa
     * @param <V> valor del mapa
     * @param sourceList coleccion, puede ser un conjunto o lista
     * @param converter interface obtiene una clave y la introduce en un mapa
     * @return un mapa con los elementos de una colección
     */
    public static <K, V> Map<K, V> listaComoMapa(Collection<V> sourceList, ConvertidorListaAMapa<K, V> converter) {
        Map<K, V> newMap = new HashMap<>();
        
        for (V item : sourceList) {
            newMap.put(converter.getKey(item), item);
        }
        
        return newMap;
    }

    /**
     * Interface parametrizado
     *
     * @param <K>
     * @param <V>
     */
    public static interface ConvertidorListaAMapa<K, V> {
        
        public K getKey(V item);
    }

    /**
     * Comparador genérico de valores de los mapas
     *
     * @param <T> Clave
     * @param <K> Valor
     */
    public class ComparadorValoresMapas<T, K> implements Comparator<T> {
        
        Map<T, K> mapa;
        
        public ComparadorValoresMapas(Map<T, K> mapa) {
            this.mapa = mapa;
        }
        
        @Override
        public int compare(T keyA, T keyB) {
            Comparable valueA = (Comparable) mapa.get(keyA);
            Comparable valueB = (Comparable) mapa.get(keyB);
            return valueB.compareTo(valueA);
        }
    }

    /**
     *
     * @return
     */
    public Alumno alumnoMayorNota() {
        Collections.sort(claseLista, new AlumnoOrdenPorNota());
        
        return claseLista.get(claseLista.size() - 1);
    }
    
    public void alumnosRangoEdad(int min, int max){
        Utils.imprimirAlumnosEnRangoEdad(claseLista, min, max);
    }
    
}
