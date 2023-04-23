/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech;

import cl.ucn.disc.pa.bibliotech.services.Sistema;
import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;

import java.io.IOException;
import java.util.Objects;

/**
 * The Main.
 *
 * @author Programacion Avanzada.
 */
public final class Main {

    /**
     * The main.
     *
     * @param args to use.
     * @throws IOException en caso de un error.
     */
    public static void main(final String[] args) throws IOException {

        // inicializacion del sistema.
        Sistema sistema = new Sistema();

        StdOut.println(sistema.obtegerCatalogoLibros());

        String opcion = null;
        while (!Objects.equals(opcion, "2")) {

            StdOut.println("""
                    [*] Bienvenido a BiblioTech [*]
                                    
                    [1] Iniciar Sesion
                    [2] Salir
                    """);
            StdOut.print("Escoja una opcion: ");
            opcion = StdIn.readLine();

            switch (opcion) {
                case "1" -> iniciarSesion(sistema);
                case "2" -> StdOut.println("¡Hasta Pronto!");
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    /**
     * Inicia la sesion del Socio en el Sistema.
     *
     * @param sistema a utilizar.
     */
    private static void iniciarSesion(final Sistema sistema) {
        StdOut.println("[*] Iniciar sesion en BiblioTech [*]");
        StdOut.print("Ingrese su numero de socio: ");
        int numeroSocio = StdIn.readInt();
        StdIn.readLine();

        StdOut.print("Ingrese su contrasenia: ");
        String contrasenia = StdIn.readLine();

        // intento el inicio de session
        try {
            sistema.iniciarSession(numeroSocio, contrasenia);
        } catch (IllegalArgumentException ex) {
            StdOut.println("Ocurrio un error: " + ex.getMessage());
            return;
        }

        // mostrar menu principal
        menuPrincipal(sistema);
    }

    private static void menuPrincipal(final Sistema sistema) {
        String opcion = null;
        while (!Objects.equals(opcion, "4")) {
            StdOut.println("""
                    [*] BiblioTech [*]
                                        
                    [1] Prestamo de un libro
                    [2] Editar información
                    [3] Calificar libro
                                        
                    [4] Cerrar sesion
                    """);

            StdOut.print("Escoja una opcion: ");
            opcion = StdIn.readLine();

            switch (opcion) {
                case "1" -> menuPrestamo(sistema);
                case "2" -> editarInformacion(sistema);
                case "3" -> calificarlibro(sistema);
                case "4" -> sistema.cerrarSession();
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }


    /**
     * Permite calificar entre 0 a 5 estrellas un libro.
     *
     * @param sistema a utilizar.
     */
    private static void calificarlibro(Sistema sistema) {
        StdOut.println("[*] Calificar un libro [*]");
        StdOut.println("");

        StdOut.println(sistema.obtegerCatalogoLibros());
        StdOut.println("Ingrese el ISBN del libro que desea calificar: ");
        String isbnLibro = StdIn.readLine();

        /// Comprobar si el isbn es valido


        double califacionLibro = sistema.calificacionLibro(isbnLibro);
        StdOut.println("La calificación es:" + califacionLibro);
        StdOut.println("Datos actualizados.");
        StdOut.println("");


        ///Mostrar menu principal nuevamente
        menuPrincipal(sistema);


    }

    private static void menuPrestamo(Sistema sistema) {
        StdOut.println("[*] Préstamo de un Libro [*]");
        StdOut.println(sistema.obtegerCatalogoLibros());

        StdOut.print("Ingrese el ISBN del libro a tomar prestado: ");
        String isbn = StdIn.readLine();

        try {
            sistema.realizarPrestamoLibro(isbn);
        } catch (IOException ex) {
            StdOut.println("Ocurrio un error, intente nuevamente: " + ex.getMessage());
        }
    }

    private static void editarInformacion(Sistema sistema) {

        String opcion = null;
        while (!Objects.equals(opcion, "3")) {

            StdOut.println("[*] Editar Perfil [*]");
            StdOut.println(sistema.obtenerDatosSocioLogeado());
            StdOut.println("""               
                    [1] Editar correo Electronico
                    [2] Editar Contraseña
                    [3] Editar Nombre
                    [4] Editar Apellido
                                        
                    [5] Volver atrás
                    """);
            StdOut.print("Escoja una opción: ");
            opcion = StdIn.readLine();

            switch (opcion) {
                case "1" -> editarCorreo(sistema);
                case "2" -> cambiarContrasenia(sistema);
                case "3" -> cambiarNombre(sistema);
                case "4" -> cambiarApellido(sistema);
                case "5" -> StdOut.println("Volviendo al menú anterior...");
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    private static void cambiarApellido(Sistema sistema) {
        StdOut.println("Datos del socio logeado: ");
        sistema.obtenerDatosSocioLogeado();
        String apellidoNuevo = sistema.obtenernuevoApellido();
        StdOut.println("Datos actualizados del socio: ");
        sistema.obtenerDatosSocioLogeado();


    }

    private static void cambiarNombre(Sistema sistema) {
        StdOut.println("Datos del socio logeado: ");
        sistema.obtenerDatosSocioLogeado();
        String nombreNuevo = sistema.obtenernuevoNombre();
        StdOut.println("Datos actualizados del socio: ");
        sistema.obtenerDatosSocioLogeado();

    }

    private static void cambiarContrasenia(Sistema sistema) {

        StdOut.println("Datos del socio logeado: ");
        sistema.obtenerDatosSocioLogeado();
        String contraseniaNueva = sistema.obtenernuevacontrasenia();
        StdOut.println("Datos actualizados del socio: ");
        sistema.obtenerDatosSocioLogeado();


    }


    private static void editarCorreo(Sistema sistema) {

        StdOut.println("Datos del socio logeado: ");
        sistema.obtenerDatosSocioLogeado();
        String correoNuevo = sistema.obtenerNuevoCorreo();
        StdOut.println("Datos actualizados del socio: ");
        sistema.obtenerDatosSocioLogeado();


        // TODO: implementar este metodo
    }
}
