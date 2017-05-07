package practica4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;

public class PruebaContenedor {

    public static void main(String[] args) throws Exception {
        ContenedorDeEnteros a = new ContenedorDeEnteros();
        a.crear("temp", 10);
        int[] v;
        System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");
        for (int i = 0; i < 10; i++) {
            a.insertar(i);
            a.buscar(i);
        }
        v = a.elementos();
        for (int i = 0; i < a.cardinal(); i++) {
            System.out.println(v[i]);
        }
        a.vaciar();
        for (int i = 0; i < 100; i++) {
            a.insertar(i);
            a.extraer(i);
        }
        a.cerrar();
        a.abrir("temp");
        System.out.println("El contenedor a tiene " + a.cardinal() + " elementos.");
        a.cerrar();

        ContenedorDeEnteros[] arbolesb = new ContenedorDeEnteros[11];
        int[] Orden = {5, 7, 9, 11, 20, 25, 55, 75, 105, 201, 301};

        for (int x = 0; x < Orden.length; x++) {
            ContenedorDeEnteros b = new ContenedorDeEnteros();
            b.crear("pruebab", Orden[x]);
            // Creamos un array con 10 elementos ordenadors para comprobar las inserciones
            int[] ordenado = {0, 4, 6, 8, 10, 12, 15, 17, 20, 25};
            // Creamos un array con 10 elementos desordenados para insertar en el contenedor
            int[] desordenado = {20, 15, 10, 6, 0, 25, 17, 12, 8, 4};
            // Insertamos todos los elementos en el contenedor
            for (int i = 0; i < desordenado.length; i++) {
                if (!b.insertar(desordenado[i])) {
                    System.out.println("El elemento '" + desordenado[i] + "' no se ha insertado");
                }
            }
            // Creamos un array con los elementos del contenedor
            int[] array = b.elementos();
            for (int i = 0; i < array.length; i++) {
                // Comprobamos que esten todos los elementos insertados
                if (ordenado[i] != array[i]) {
                    System.out.println("No se ha creado el array de elementos correctamente");
                }
                // Buscamos todos los elementos del contenedor
                if (!b.buscar(desordenado[i])) {
                    System.out.println("No se ha encontrado el elemento '" + desordenado[i] + "' en el contenedor");
                }
                if (!b.extraer(desordenado[i])) {
                    System.out.println("No se ha extraido el elemento '" + desordenado[i] + "' del contenedor");
                }
            }

            // Numero de elementos del contenedor
            if (b.cardinal() != 0) {
                System.out.println("El numero de elementos del contenedor \"a\" es erroneo.");
            }
            // Vaciamos el contenedor
            b.vaciar();
            // Comprobamos que el contenedor este vacio
            if (b.cardinal() != 0) {
                System.out.println("El contenedor no se ha vaciado correctamente");
            }

            b.crear("pruebab", Orden[x]);

            b.insertar(0);
            // Creamos un array con los elementos del contenedor
            array = b.elementos();
            // Comprobamos que el contenedor tenga un elemento
            if (b.cardinal() != 1) {
                System.out.println("No se ha insertado el elemento '" + array[0] + "' en un contenedor vacio");
            }
            // Extraemos el '0' del contenedor
            b.extraer(0);
            // Creamos un array con los elementos del contenedor
            array = b.elementos();
            // Comprobamos que el contenedor este vacio
            if (b.cardinal() != 0) {
                System.out.println("No se ha extraido el elemento '" + array[0] + "' en un contenedor con un elemento");
            }
            // Insertamos el elemento '25' en la ultima posicion del contenedor
            b.insertar(25);
            // Comprobamos que inserta el ultimo elemento anteriormente extraido
            if (b.cardinal() != 1) {
                System.out.println("El elemento anteriormente extraido no lo inserta en la ultima posicion del contenedor");
            }
            // Buscar un elemento que no estÃ¡ en el contenedor
            if (a.buscar(11)) {
                System.out.println("Se encontrÃ³ un elemento que no estÃ¡ en el contenedor");
            }
            // Extraemos un elemento que no estÃ¡ en el contenedor
            if (a.extraer(19)) {
                System.out.println("Se extrajo un elemento que no estÃ¡ en el contenedor");
            }
            b.cerrar();
        }

        // Creacion de los arboles b con los ordenes que le corresponden.
        for (int i = 0; i < arbolesb.length; i++) {
            arbolesb[i] = new ContenedorDeEnteros();
            arbolesb[i].crear("arbolb" + Orden[i], Orden[i]);
        }

        // Comienzan las pruebas de tiempos.
        System.out.println("[+] INICIANDO PRUEBAS DE CONTENEDOR");
        RandomAccessFile datosDat = new RandomAccessFile("datos.dat", "r");
        RandomAccessFile datosNoDat = new RandomAccessFile("datos_no.dat", "r");
        BufferedWriter salida4 = new BufferedWriter(new FileWriter("salida4.txt"));

        double init = 0;
        double stop = 0;

        int[] datos = new int[100000];
        for (int m = 0; m < 100000; m++) {
            datos[m] = datosDat.readInt();
        }
        datosDat.close();

        int[] noDatos = new int[20000];
        for (int n = 0; n < 20000; n++) {
            noDatos[n] = datosNoDat.readInt();
        }
        datosNoDat.close();

        salida4.write("[+] PRUEBA INSERCIONES");
        for (int i = 0; i < arbolesb.length; i++) {
            salida4.newLine();
            salida4.write("Orden " + Orden[i] + " : ");
            salida4.newLine();
            init = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10000; k++) {
                    arbolesb[i].insertar(datos[k + (j * 10000)]);
                }

                stop = System.nanoTime();
                salida4.write(Double.toString((stop - init) / 10000000.) + " ms");
                salida4.newLine();
                init = System.nanoTime();
            }
        }

        salida4.newLine();
        salida4.write("[+] PRUEBA EXTRACCIONES");
        for (int i = 0; i < arbolesb.length; i++) {
            salida4.newLine();
            salida4.write("Orden " + Orden[i] + " : ");
            salida4.newLine();
            init = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10000; k++) {
                    arbolesb[i].extraer(datos[k + (j * 10000)]);
                }

                stop = System.nanoTime();
                salida4.write(Double.toString((stop - init) / 10000000.) + " ms");
                salida4.newLine();
                init = System.nanoTime();
            }
        }

        for (int i = 0; i < arbolesb.length; i++) {
            arbolesb[i].vaciar();
        }

        salida4.newLine();
        salida4.write("[+] PRUEBA BÚSQUEDA EXITOSA");
        salida4.newLine();
        for (int i = 0; i < arbolesb.length; i++) {
            salida4.write("Orden " + Orden[i] + " : ");
            int j = 0, w = 10;
            while (j < datos.length) {

                for (int k = 0; k < 10000; k++, j++) {
                    arbolesb[i].insertar(datos[j]);
                }

                init = System.nanoTime();

                for (int l = 0; l < j; l++) {
                    arbolesb[i].buscar(datos[l]);
                }

                stop = System.nanoTime();
                salida4.write(Double.toString((stop - init) / (w * 1000000.)) + " ms");
                w += 10;
                salida4.newLine();
            }
        }

        for (int i = 0; i < arbolesb.length; i++) {
            arbolesb[i].vaciar();
        }

        salida4.newLine();
        salida4.write("[+] PRUEBA BÚSQUEDA NO EXITOSA");
        salida4.newLine();
        for (int t = 0; t < arbolesb.length; t++) {
            salida4.write("Orden " + Orden[t] + " : ");
            for (int i = 0; i < 10; i++) {

                for (int j = 0; j < 10000; j++)
                    arbolesb[t].insertar(datos[j + (i * 10000)]);

                init = System.nanoTime();

                for (int k = 0; k < 500; k++) {
                    for (int l = 0; l < 20000; l++)
                        arbolesb[t].buscar(noDatos[l]);
                }

                stop = System.nanoTime();
                salida4.write(Double.toString((stop - init) / (20000000 * 500.)) + " ms");
                salida4.newLine();
            }
        }
        salida4.close();
        System.out.println("[!] FIN DE LA PRUEBA");
    }
}
