package com.paintingcompetition.infrastructure.adapters.in.console;

import com.paintingcompetition.domain.model.RolUsuario;
import com.paintingcompetition.domain.model.Usuario;
import com.paintingcompetition.domain.ports.out.MotorCriptograficoPort;

import java.util.Scanner;
import java.util.UUID;

/**
 * Adaptador de Entrada (Driving Adapter) que implementa una interfaz de usuario
 * por consola.
 * Proporciona un menú de inicio de sesión simulado para interactuar con el
 * sistema criptográfico.
 */
public class MenuPrincipalConsola {

    private final Scanner scanner;
    private final MotorCriptograficoPort motorConfidencialidad;
    private final MotorCriptograficoPort motorRegistro;
    private final MotorCriptograficoPort motorAnonimato;

    public MenuPrincipalConsola(MotorCriptograficoPort motorConfidencialidad,
            MotorCriptograficoPort motorRegistro,
            MotorCriptograficoPort motorAnonimato) {
        this.scanner = new Scanner(System.in);
        this.motorConfidencialidad = motorConfidencialidad;
        this.motorRegistro = motorRegistro;
        this.motorAnonimato = motorAnonimato;
    }

    /**
     * Inicia el bucle principal de la aplicación en consola.
     */
    public void iniciar() {
        boolean salir = false;

        System.out.println("========================================================");
        System.out.println(" SISTEMA DE CONCURSO DE PINTURA (CORE CRIPTOGRÁFICO)");
        System.out.println(" Arquitectura Hexagonal - Puertos y Adaptadores");
        System.out.println("========================================================");

        while (!salir) {
            System.out.println("\n--- Menú Principal: Inicio de Sesión Simulado ---");
            System.out.println("1. Entrar como Pintor");
            System.out.println("2. Entrar como Juez");
            System.out.println("3. Entrar como Presidente");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    loginPintor();
                    break;
                case "2":
                    loginJuez();
                    break;
                case "3":
                    loginPresidente();
                    break;

                case "4":
                    System.out.println("\nSaliendo del sistema. ¡Hasta pronto!");
                    salir = true;
                    break;
                default:
                    System.out.println("\n[!] Opción no válida. Por favor ingrese 1, 2 o 3.");
            }
        }
    }

    private void loginPintor() {
        System.out.print("\nIngrese su nombre de Pintor/Autor: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            nombre = "PintorAnonimo";
        }

        Usuario pintor = new Usuario(UUID.randomUUID().toString(), nombre, RolUsuario.PINTOR);
        System.out.println(
                ">> Sesión iniciada con éxito como: " + pintor.getNombreUsuario() + " (Rol: " + pintor.getRol() + ")");

        menuPintor(pintor);
    }

    private void loginJuez() {
        System.out.print("\nIngrese su nombre de Juez/Calificador: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            nombre = "JuezOficial";
        }

        Usuario juez = new Usuario(UUID.randomUUID().toString(), nombre, RolUsuario.JUEZ);
        System.out.println(
                ">> Sesión iniciada con éxito como: " + juez.getNombreUsuario() + " (Rol: " + juez.getRol() + ")");

        menuJuez(juez);
    }

    private void loginPresidente() {
        System.out.print("\nIngrese su nombre de Presidente: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            nombre = "Presidente";
        }
        Usuario presidente = new Usuario(UUID.randomUUID().toString(), nombre, RolUsuario.ORGANIZADOR);
        System.out.println(">> Sesión iniciada con éxito como: " + presidente.getNombreUsuario() + " (Rol: "
                + presidente.getRol() + ")");
        menuPresidente(presidente);
    }

    private void menuPintor(Usuario pintor) {
        boolean regresar = false;
        while (!regresar) {
            System.out.println("\n--- Panel del Pintor (" + pintor.getNombreUsuario() + ") ---");
            System.out.println("1. Registrar y proteger obra (ECDSA + AES + Blind RSA)");
            System.out.println("2. Consultar mis obras registradas");
            System.out.println("3. Regresar al menú de inicio");
            System.out.print("Seleccione una opción: ");

            String op = scanner.nextLine().trim();
            switch (op) {
                case "1":
                    System.out.println("\n[Simulación]: Preparando registro de obra con módulos criptográficos...");
                    System.out.println("- Módulo AES: "
                            + (motorConfidencialidad != null ? "Listo (Pendiente implementar)" : "No configurado"));
                    System.out.println("- Módulo ECDSA: "
                            + (motorRegistro != null ? "Listo (Pendiente implementar)" : "No configurado"));
                    System.out.println("- Módulo RSA: "
                            + (motorAnonimato != null ? "Listo (Pendiente implementar)" : "No configurado"));
                    break;
                case "2":
                    System.out.println("\n[Simulación]: No hay obras registradas en memoria en este momento.");
                    break;
                case "3":
                    regresar = true;
                    break;
                default:
                    System.out.println("\n[!] Opción no válida.");
            }
        }
    }

    private void menuJuez(Usuario juez) {
        boolean regresar = false;
        while (!regresar) {
            System.out.println("\n--- Panel del Juez (" + juez.getNombreUsuario() + ") ---");
            System.out.println("1. Validar firma ciega de obra anónima");
            System.out.println("2. Calificar obra");
            System.out.println("3. Regresar al menú de inicio");
            System.out.print("Seleccione una opción: ");

            String op = scanner.nextLine().trim();
            switch (op) {
                case "1":
                    System.out.println("\n[Simulación]: Verificación con ModuloAnonimatoRSA...");
                    break;
                case "2":
                    System.out.println("\n[Simulación]: Asignación de puntaje a obra anónima...");
                    break;
                case "3":
                    regresar = true;
                    break;
                default:
                    System.out.println("\n[!] Opción no válida.");
            }
        }
    }

    private void menuPresidente(Usuario presidente) {
        boolean regresar = false;
        while (!regresar) {
            System.out.println("\n--- Panel del Presidente (" + presidente.getNombreUsuario() + ") ---");
            System.out.println("1. Realizar firma de los votos");
            System.out.println("2. Regresar al menú de inicio");
            System.out.print("Seleccione una opción: ");

            String op = scanner.nextLine().trim();
            switch (op) {
                case "1":
                    System.out.println("\n[Simulación]: Realizar firma ciega.");
                    break;
                case "2":
                    regresar = true;
                    break;
                default:
                    System.out.println("\n[!] Opción no válida.");
            }
        }
    }
}
