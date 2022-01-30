package com.company;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    //FUNCIONES:

    public static void pintaMenuPrincipal(){
        System.out.println(ANSI_BLUE + "---------------------------------------------------------------------");
        System.out.println("|          BIENVENIDX AL SISTEMA DE GESTIÓN DE INCIDENCIAS          |");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("| 1.- Registrarse                                                   |");
        System.out.println("| 2.- Iniciar sesión como usuario                                   |");
        System.out.println("| 3.- Iniciar sesión como técnico                                   |");
        System.out.println("| 4.- Iniciar sesión como administrador                             |");
        System.out.println("| 5.- Cerrar el programa                                            |");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Elija una opción: \s" + ANSI_RESET);
    }

    public static void pintaMenuUsuario(String nombre){
        System.out.println(ANSI_YELLOW + "     ☆ BIENVENIDX " + nombre + ", TIENE USTED PERFIL DE USUARIO NORMAL ☆");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("| Menú:                                                             |");
        System.out.println("| 1.- Registrar nueva incidencia                                    |");
        System.out.println("| 2.- Consultar mis incidencias abiertas                            |");
        System.out.println("| 3.- Consultar mis incidencias cerradas                            |");
        System.out.println("| 4.- Mostrar mi perfil                                             |");
        System.out.println("| 5.- Cambiar clave de acceso                                       |");
        System.out.println("| 6.- Cerrar sesión                                                 |");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Elija una opción: \s" + ANSI_RESET);
    }

    public static void pintaMenuTecnico (String nombre){
        System.out.println(ANSI_CYAN + "    ☆ BIENVENIDX " + nombre + ", TIENE USTED PERFIL DE TÉCNICO ☆");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("| Menú:                                                             |");
        System.out.println("| 1.- Consultar las incidencias que tengo asignadas                 |");
        System.out.println("| 2.- Marcar una incidencia como cerrada                            |");
        System.out.println("| 3.- Consultar las incidencias que he resuelto                     |");
        System.out.println("| 4.- Mostrar perfil                                                |");
        System.out.println("| 5.- Cambiar clave de acceso                                       |");
        System.out.println("| 6.- Cerrar sesión                                                 |");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Elija una opción: \s" + ANSI_RESET);
    }

    public static void pintaMenuAdministrador (String nombre){
        System.out.println(ANSI_PURPLE + "    ☆ BIENVENIDX " + nombre + ", TIENE USTED PERFIL DE ADMINISTRACIÓN ☆");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("| Menú:                                                             |");
        System.out.println("| 1.- Consultar todas las incidencias                               |");
        System.out.println("| 2.- Consultar todos los usuarios                                  |");
        System.out.println("| 3.- Consultar todos los técnicos                                  |");
        System.out.println("| 4.- Asignar una incidencia a un técnico                           |");
        System.out.println("| 5.- Dar de alta un técnico                                        |");
        System.out.println("| 6.- Borrar un técnico                                             |");
        System.out.println("| 7.- Borrar un usuario                                             |");
        System.out.println("| 8.- Cerrar sesión                                                 |");
        System.out.println("| 9.- Cerrar el programa                                            |");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Elija una opción: \s" + ANSI_RESET);
    }

    private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        String remitente = "admiproyectoincidencias@gmail.com";
        String clave = "Admin20175258";
        // Propiedades de la conexión que se va a establecer con el servidor de correo SMTP
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); // Conectar de manera segura
        props.put("mail.smtp.port", "587"); // Puerto SMTP seguro de Google
        // Se obtiene la sesión en el servidor de correo
        Session session = Session.getDefaultInstance(props);
        try {
            // Creación del mensaje a enviar
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));

            message.setSubject(asunto);
            //message.setText(cuerpo); // Para enviar texto plano
            message.setContent(cuerpo, "text/html; charset=utf-8"); // Para enviar html
            // Definición de los parámetros del protocolo de transporte
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    static boolean enviaMensajeTelegram (String mensaje) {
        String direccion;
        String fijo = "https://api.telegram.org/bot5202479427:AAEo2tSiarYI1hf6jjMFs4wlTOu67WA6R48/sendMessage?chat_id=1954372519&text=";
        direccion = fijo + mensaje;
        URL url;
        boolean dev;
        dev = false;

        try {
            url = new URL (direccion);
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            dev = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return dev;
    }

    public static void main(String[] args) {
        //Objetos
        //Usuarios

        Usuario usuario1 = new Usuario();   Usuario usuario2 = new Usuario();   Usuario usuarioAuxiliar = null;

        //------------------------------------------------------------------------------------------------------------------------

        //Técnicos

        Tecnico tecnico1 = new Tecnico();   Tecnico tecnico2 = new Tecnico();   Tecnico tecnicoAuxiliar = null;

        //-------------------------------------------------------------------------------------------------------------------------

        //Administración
        Administracion administrador1 = new Administracion("Laura", "Akame", "laura@gmail.com", "valki");

        //--------------------------------------------------------------------------------------------------------------------------------------------------------

        //Incidencias

        Incidencia incidencia1;     Incidencia incidencia2;     Incidencia incidencia3;

        //Variables
        String usuario, password, confirmacionPassword, nuevaPassword, borrarUsuario, borrarTecnico;

        //Contadores
        int contadorUsuarios = 0, contadorTecnicos = 0, contadorIncidencias = 0, contadorUsuariosAdmin = 0;

        //OPCIONES MENU
        int opcionMenuPrincipal = 0, opcionMenuUsuario = 0, opcionMenuTecnico = 0, opcionMenuAdministrador = 0, opcionMenuPrioridadIncidencias = 0;

        //BANDERAS
        boolean bandera1 = false, cerrarSesionUsuario = false, cerrarSesionTecnico = false, cerrarSesionAdministrador = false;

        //Variables registro usuario
        String nombreUsuario, usuarioUsuario, passwordUsuario, correoUsuario, dniUsuario;
        int telefonoUsuario = 0;

        //Variables registro técnico

        String nombreTecnico, usuarioTecnico, correoTecnico, passwordTecnico;

        //Variables creación incidencia
        String comentario, prioridad = "";

        //Variables asignación incidencia
        int idIncidencia = 0, idTecnico = 0;

        //Variables cerrar incidencia
        String comentarioCerrarIncidencia;
        int idCerrarIncidencia = 0;

        Scanner sc = new Scanner(System.in);

        //CORREO ADMIN
        String destinatario = "laura.cabezas@fernando3martos.com"; // Destinatario del mensaje

        String asunto = "Correo de prueba enviado desde Java";

        String cuerpo = "<h1>Esta es una prueba de correo con html</h1>" +
                "<p>hola <strong>Akame</strong> que pasa</p>";

        enviarConGMail(destinatario, asunto, cuerpo);

        //MENSAJE TELEGRAM

        String mensaje;

        System.out.println("Intoduzca un mensaje para Telegram:");
        mensaje = sc.nextLine();

        if (enviaMensajeTelegram(mensaje)) {
            System.out.println("Mensaje enviado con éxito");
        } else {
            System.out.println("Fallo al enviar el mensaje");
        }

        //--------------------------------------------------------------------------------------------------------------------------------------------------------
//TODO ANSI RED EN TODOS LOS TRY-CATCH

        do {
            //Reseteo banderas
            cerrarSesionUsuario = false;
            cerrarSesionTecnico = false;
            cerrarSesionAdministrador = false;

            //Menú principal

            pintaMenuPrincipal();
            try {
                opcionMenuPrincipal = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ERROR: Por favor, introduzca un número para seleccionar una opción");
                System.out.println("--------------------------------------------------------------------------");
            }

            switch (opcionMenuPrincipal){
                case 1:
                    //REGISTRARSE
                    Usuario usuarioDeRegistro;

                    if (contadorUsuarios < 2){
                        System.out.println("Introduzca su nombre:");
                        nombreUsuario = sc.nextLine();

                        System.out.println("Introduzca su nombre de usuario:");
                        usuarioUsuario = sc.nextLine();

                        System.out.println("Introduzca su correo electrónico:");
                        correoUsuario = sc.nextLine();

                        do {
                            System.out.println("Introduzca su contraseña:");
                            passwordUsuario = sc.nextLine();

                            System.out.println("Confirme su contraseña:");
                            confirmacionPassword = sc.nextLine();

                            if (!passwordUsuario.equalsIgnoreCase(confirmacionPassword)) {
                                System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                System.out.println("ERROR: Las contraseñas introducidas son diferentes");
                                System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                            }

                        } while (!passwordUsuario.equalsIgnoreCase(confirmacionPassword));

                        System.out.println("Introduzca su DNI:");
                        dniUsuario = sc.nextLine();

                        do {
                            try {
                                System.out.println("Introduzca su número de teléfono:");
                                telefonoUsuario = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println(ANSI_RED + "------------------------------------------------------");
                                System.out.println("ERROR: El número de teléfono debe ser un número!!");
                                System.out.println("------------------------------------------------------\n" + ANSI_RESET);
                            }
                        } while (telefonoUsuario == 0);

                        usuarioDeRegistro = new Usuario(nombreUsuario, dniUsuario, usuarioUsuario, correoUsuario, passwordUsuario, confirmacionPassword, telefonoUsuario);

                        System.out.println(ANSI_GREEN + "---------------------------------------------------------------------");
                        System.out.println("Usuario registrado con éxito!!");
                        System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);

                        if (usuario1.getNombre() == null){
                            usuario1 = usuarioDeRegistro;
                            contadorUsuarios++;
                        } else {
                            usuario2 = usuarioDeRegistro;
                            contadorUsuarios++;
                        }
                    } else {
                        System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                        System.out.println("ERROR: Se ha superado el máximo de usuarios registrados!!");
                        System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                    }
                    break;
                case 2:
                    //INICIAR SESIÓN COMO USUARIO
                    if (usuario1.getNombre() != null || usuario2.getNombre() != null){
                        do {
                            System.out.println("Introduzca su nombre de usuario:");
                            usuario = sc.nextLine();

                            System.out.println("Introduzca su contraseña:");
                            password = sc.nextLine();

                            //Aquí se comprueba que la contraseña introducida y la registrada son iguales
                            if (usuario1.compruebaUsuario(usuario) && usuario1.compruebaPassword(password)) {
                                usuarioAuxiliar = usuario1;
                            }

                            if (usuario2.compruebaUsuario(usuario) && usuario2.compruebaPassword(password)) {
                                usuarioAuxiliar = usuario2;
                            }

                            if ((!usuario1.compruebaUsuario(usuario) || !usuario1.compruebaPassword(password)) && (!usuario2.compruebaUsuario(usuario) || !usuario2.compruebaPassword(password))) {
                                System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                System.out.println("ERROR: Usuario o contraseña incorrectos!!");
                                System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                            }

                        } while (!usuario1.compruebaUsuario(usuario) && !usuario1.compruebaPassword(password) && !usuario2.compruebaUsuario(usuario) && !usuario2.compruebaPassword(password));

                        if (usuarioAuxiliar != null) {
                            //Menú usuario //TODO USUARIO
                            do {

                                pintaMenuUsuario(usuarioAuxiliar.getNombre());
                                try {
                                    opcionMenuUsuario = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("------------------------------------------------------------");
                                    System.out.println("Por favor, introduzca un número para seleccionar una opción");
                                    System.out.println("------------------------------------------------------------");
                                }

                                switch (opcionMenuUsuario) {
                                    case 1:
                                        //REGISTRAR NUEVA INCIDENCIA
                                        if (contadorIncidencias < 3) {

                                            if (usuarioAuxiliar.getIncidencia1() == null) {
                                                System.out.println("Introduzca un comentario:");
                                                comentario = sc.nextLine();

                                                do {
                                                    try {
                                                        System.out.println("Indique la prioridad de la incidencia:");
                                                        System.out.println("1.- Baja");
                                                        System.out.println("2.- Media");
                                                        System.out.println("3.- Alta");
                                                        opcionMenuPrioridadIncidencias = Integer.parseInt(sc.nextLine());
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("\n--------------------------------------------------------------------------");
                                                        System.out.println("ERROR: Por favor, introduzca un número para seleccionar una opción!!");
                                                        System.out.println("--------------------------------------------------------------------------\n");
                                                    }
                                                } while (opcionMenuPrioridadIncidencias == 0);

                                                switch (opcionMenuPrioridadIncidencias) {
                                                    case 1:
                                                        prioridad = "Baja";
                                                        break;
                                                    case 2:
                                                        prioridad = "Media";
                                                        break;
                                                    case 3:
                                                        prioridad = "Alta";
                                                        break;
                                                }

                                                //Aquí se registra la incidencia1
                                                incidencia1 = new Incidencia(usuarioAuxiliar,null, comentario, prioridad, false,false, "No existen comentarios todavía");

                                                usuarioAuxiliar.setIncidencia1(incidencia1);

                                                contadorIncidencias++;

                                                System.out.println(ANSI_GREEN + "---------------------------------------------------------------------");
                                                System.out.println("Incidencia registrada con éxito!!");
                                                System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);

                                                opcionMenuPrioridadIncidencias = 0;

                                            } else if (usuarioAuxiliar.getIncidencia2() == null) {
                                                System.out.println("Introduzca un comentario:");
                                                comentario = sc.nextLine();

                                                do {
                                                    try {
                                                        System.out.println("Indique la prioridad de la incidencia:");
                                                        System.out.println("1.- Baja");
                                                        System.out.println("2.- Media");
                                                        System.out.println("3.- Alta");
                                                        opcionMenuPrioridadIncidencias = Integer.parseInt(sc.nextLine());
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("\n--------------------------------------------------------------------------");
                                                        System.out.println("ERROR: Por favor, introduzca un número para seleccionar una opción!!");
                                                        System.out.println("--------------------------------------------------------------------------\n");
                                                    }
                                                }while (opcionMenuPrioridadIncidencias == 0);

                                                switch (opcionMenuPrioridadIncidencias) {
                                                    case 1:
                                                        prioridad = "Baja";
                                                        break;
                                                    case 2:
                                                        prioridad = "Media";
                                                        break;
                                                    case 3:
                                                        prioridad = "Alta";
                                                        break;
                                                }

                                                //Aquí se registra la incidencia2
                                                incidencia2 = new Incidencia(usuarioAuxiliar,null, comentario, prioridad,false, false, "No existen comentarios todavía");

                                                usuarioAuxiliar.setIncidencia2(incidencia2);

                                                contadorIncidencias++;

                                                System.out.println(ANSI_GREEN + "---------------------------------------------------------------------");
                                                System.out.println("Incidencia registrada con éxito!!");
                                                System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);

                                                opcionMenuPrioridadIncidencias = 0;

                                            } else {
                                                System.out.println("Introduzca un comentario:");
                                                comentario = sc.nextLine();

                                                do {
                                                    try {
                                                        System.out.println("Indique la prioridad de la incidencia:");
                                                        System.out.println("1.- Baja");
                                                        System.out.println("2.- Media");
                                                        System.out.println("3.- Alta");
                                                        opcionMenuPrioridadIncidencias = Integer.parseInt(sc.nextLine());
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("\n--------------------------------------------------------------------------");
                                                        System.out.println("ERROR: Por favor, introduzca un número para seleccionar una opción!!");
                                                        System.out.println("--------------------------------------------------------------------------\n");
                                                    }
                                                } while (opcionMenuPrioridadIncidencias == 0);

                                                switch (opcionMenuPrioridadIncidencias) {
                                                    case 1:
                                                        prioridad = "Baja";
                                                        break;
                                                    case 2:
                                                        prioridad = "Media";
                                                        break;
                                                    case 3:
                                                        prioridad = "Alta";
                                                        break;
                                                    default:
                                                        System.out.println(ANSI_RED + "-------------------------------------------------");
                                                        System.out.println("ERROR: Acción imposible!!");
                                                        System.out.println("-------------------------------------------------\n" + ANSI_RESET);
                                                        break;
                                                }

                                                //Aquí se registra la incidencia3
                                                incidencia3 = new Incidencia(usuarioAuxiliar, null,comentario, prioridad, false,false, "No existen comentarios todavía");

                                                usuarioAuxiliar.setIncidencia3(incidencia3);

                                                contadorIncidencias++;

                                                System.out.println(ANSI_GREEN + "---------------------------------------------------------------------");
                                                System.out.println("Incidencia registrada con éxito!!");
                                                System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);

                                                opcionMenuPrioridadIncidencias = 0;
                                            }
                                        } else {
                                            System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                            System.out.println("ERROR: Se ha superado el número máximo de incidencias registradas!!");
                                            System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                        }
                                        break;
                                    case 2:
                                        //CONSULTAR INCIDENCIAS ABIERTAS
                                        if (usuarioAuxiliar.getIncidencia1() != null){
                                            if (!usuarioAuxiliar.getIncidencia1().isResuelto()) {
                                                System.out.println(usuarioAuxiliar.getIncidencia1().muestraIncidencia() + "\n");
                                            }

                                            if (usuarioAuxiliar.getIncidencia2() != null) {
                                                if (!usuarioAuxiliar.getIncidencia2().isResuelto()) {
                                                    System.out.println(usuarioAuxiliar.getIncidencia2().muestraIncidencia() + "\n");
                                                }
                                            }

                                            if (usuarioAuxiliar.getIncidencia3() != null) {
                                                if (!usuarioAuxiliar.getIncidencia3().isResuelto()) {
                                                    System.out.println(usuarioAuxiliar.getIncidencia3().muestraIncidencia() + "\n");
                                                }
                                            }

                                            if (usuarioAuxiliar.getIncidencia1() == null && usuarioAuxiliar.getIncidencia2() == null && usuarioAuxiliar.getIncidencia3() == null) {
                                                System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                                System.out.println("ERROR: No existen incidencias abiertas registradas!!");
                                                System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                            }

                                        } else {
                                            System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                            System.out.println("ERROR: No existen incidencias abiertas registradas!!");
                                            System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                        }
                                        break;
                                    case 3:
                                        //CONSULTAR INCIDENCIAS CERRADAS
                                        if (tecnico1.getIncidenciaResuelta1() != null) {
                                            System.out.println(tecnico1.getIncidenciaResuelta1().muestraIncidencia() + "\n");
                                        }

                                        if (tecnico1.getIncidenciaResuelta2() != null) {
                                            System.out.println(tecnico1.getIncidenciaResuelta2().muestraIncidencia() + "\n");
                                        }

                                        if (tecnico2.getIncidenciaResuelta1() != null) {
                                            System.out.println(tecnico2.getIncidenciaResuelta1().muestraIncidencia() + "\n");
                                        }

                                        if (tecnico2.getIncidenciaResuelta2() != null) {
                                            System.out.println(tecnico2.getIncidenciaResuelta2().muestraIncidencia() + "\n");
                                        }

                                        if (tecnico1.getIncidenciaResuelta1() == null && tecnico1.getIncidenciaResuelta2() == null && tecnico2.getIncidenciaResuelta1() == null && tecnico2.getIncidenciaResuelta2() == null) {
                                            System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                            System.out.println("ERROR: No existen incidencias cerradas registradas!!");
                                            System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                        }
                                        break;
                                    case 4:
                                        //MOSTRAR PERFIL DEL USUARIO
                                        System.out.println(usuario1.toString());
                                        break;
                                    case 5:
                                        //CAMBIAR CONTRASEÑA USUARIO
                                        do {
                                            System.out.println("Introduzca su contraseña actual:");
                                            password = sc.nextLine();

                                            if (!usuarioAuxiliar.compruebaPassword(password)){
                                                System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                                System.out.println("ERROR: Contraseña incorrecta!!");
                                                System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                            }

                                        }while (!usuarioAuxiliar.compruebaPassword(password));

                                        if (usuarioAuxiliar.compruebaPassword(password)){
                                            System.out.println("Introduzca su nueva contraseña:");
                                            nuevaPassword = sc.nextLine();
                                            usuarioAuxiliar.setPasswordRegistrada(nuevaPassword);
                                            System.out.println(ANSI_GREEN + "-------------------------------------------------");
                                            System.out.println("Clave cambiada con éxito!!");
                                            System.out.println("-------------------------------------------------\n" + ANSI_RESET);
                                        }
                                        break;
                                    case 6:
                                        //CERRAR SESIÓN USUARIO
                                        contadorIncidencias = 0;
                                        usuarioAuxiliar = null;
                                        cerrarSesionUsuario = true;
                                        break;
                                    default: break;
                                }

                            }  while (!cerrarSesionUsuario);
                        }
                    } else {
                        System.out.println(ANSI_RED + "----------------------------------------------------------------------");
                        System.out.println("ERROR: No existen usuarios registrados!!");
                        System.out.println("----------------------------------------------------------------------\n" + ANSI_RESET);
                    }
                    break;
                case 3:
                    //Iniciar sesión como técnico
                    if (tecnico1.getNombre() != null || tecnico2.getNombre() != null) {
                        do {
                            System.out.println("Introduzca su nombre de usuario:");
                            usuario = sc.nextLine();

                            System.out.println("Introduzca su contraseña:");
                            password = sc.nextLine();

                            //Aquí se comprueba que la contraseña introducida y la registrada sean iguales
                            if (tecnico1.compruebaUsuario(usuario) && tecnico1.compruebaPassword(password)) {
                                tecnicoAuxiliar = tecnico1;
                            }

                            if (tecnico2.compruebaUsuario(usuario) && tecnico2.compruebaPassword(password)) {
                                tecnicoAuxiliar = tecnico2;
                            }

                            if ((!tecnico1.compruebaUsuario(usuario) || !tecnico1.compruebaPassword(password)) && (!tecnico2.compruebaUsuario(usuario) || !tecnico2.compruebaPassword(password))) {
                                System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                System.out.println("ERROR: Usuario o contraseña incorrectos!!");
                                System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                            }

                        } while (!tecnico1.compruebaUsuario(usuario) && !tecnico1.compruebaPassword(password) && !tecnico2.compruebaUsuario(usuario) && !tecnico2.compruebaPassword(password));

                        if (tecnicoAuxiliar != null) {
                            //MENÚ TÉCNICO //TODO TÉCNICO
                            do {
                                pintaMenuTecnico(tecnicoAuxiliar.getNombre());
                                try {
                                    opcionMenuTecnico = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("\n------------------------------------------------------------");
                                    System.out.println("Por favor, introduzca un número para seleccionar una opción");
                                    System.out.println("------------------------------------------------------------\n");
                                }

                                switch (opcionMenuTecnico) {
                                    case 1:
                                        //CONSULTAR INCIDENCIAS ASIGNADAS
                                        if (tecnicoAuxiliar.getIncidencia1() != null) {
                                            if (tecnicoAuxiliar.getIncidencia1().isAsignada()){
                                                System.out.println(tecnicoAuxiliar.getIncidencia1() + "\n");
                                            }
                                        }

                                        if (tecnicoAuxiliar.getIncidencia2() != null) {
                                            if (tecnicoAuxiliar.getIncidencia2().isAsignada()){
                                                System.out.println(tecnicoAuxiliar.getIncidencia2() + "\n");
                                            }
                                        }

                                        if (tecnicoAuxiliar.getIncidencia1() == null && tecnicoAuxiliar.getIncidencia2() == null) {
                                            //Compruebo que haya incidencias regitradas
                                            System.out.println(ANSI_RED + "----------------------------------------------------");
                                            System.out.println("ERROR: No existen incidencias asignadas!!");
                                            System.out.println("----------------------------------------------------\n" + ANSI_RESET);
                                        }
                                        break;
                                    case 2:
                                        //MARCAR INCIDENCIA COMO CERRADA
                                        boolean hayIncidenciasAsignadas = false;

                                        if (tecnicoAuxiliar.getIncidencia1() != null){
                                            if (tecnicoAuxiliar.getIncidencia1().isAsignada()){
                                                System.out.println(tecnicoAuxiliar.getIncidencia1());
                                                hayIncidenciasAsignadas = true;
                                            }
                                        }

                                        if (tecnicoAuxiliar.getIncidencia2() != null){
                                            if (tecnicoAuxiliar.getIncidencia2().isAsignada()) {
                                                System.out.println(tecnicoAuxiliar.getIncidencia2());
                                                hayIncidenciasAsignadas = true;
                                            }
                                        }

                                        if (tecnicoAuxiliar.getIncidencia1() == null && tecnicoAuxiliar.getIncidencia2() == null){
                                            System.out.println(ANSI_RED + "----------------------------------------------------");
                                            System.out.println("ERROR: No existen incidencias asignadas!!");
                                            System.out.println("----------------------------------------------------\n" + ANSI_RESET);
                                        }

                                        if (hayIncidenciasAsignadas){
                                            do {
                                                try {
                                                    System.out.println("Introduzca la id de la incidencia que quiere cerrar:");
                                                    idCerrarIncidencia = Integer.parseInt(sc.nextLine());
                                                } catch (NumberFormatException e) {
                                                    System.out.println("\n----------------------------------------");
                                                    System.out.println("ERROR: La id debe ser un número!!");
                                                    System.out.println("----------------------------------------\n");
                                                }
                                            } while (idCerrarIncidencia == 0);

                                            //Aquí se comprueba que las ID sean iguales
                                            if (tecnicoAuxiliar.getIncidencia1() != null && idCerrarIncidencia == tecnicoAuxiliar.getIncidencia1().getId()){
                                                System.out.println("Introduzca un comentario:");
                                                comentarioCerrarIncidencia = sc.nextLine();

                                                tecnicoAuxiliar.getIncidencia1().setAsignada(false); //TODO AQUIIIIII

                                                tecnicoAuxiliar.getIncidencia1().setResuelto(true);

                                                tecnicoAuxiliar.getIncidencia1().setComentarioTecnico(comentarioCerrarIncidencia);

                                                //Incidencia que pasare como resuelta a la del tecnico
                                                Incidencia incidenciaResuelta1 = tecnicoAuxiliar.getIncidencia1();

                                                tecnicoAuxiliar.setIncidenciaResuelta1(incidenciaResuelta1);

                                                tecnicoAuxiliar.setIncidencia1(null);

                                                //Aquí se comprueba que las ID sean iguales
                                            }else if (tecnicoAuxiliar.getIncidencia2() != null && idCerrarIncidencia == tecnicoAuxiliar.getIncidencia2().getId()){
                                                tecnicoAuxiliar.getIncidencia2().setResuelto(true);

                                                System.out.println("Introduzca un comentario:");
                                                comentarioCerrarIncidencia = sc.nextLine();

                                                tecnicoAuxiliar.getIncidencia2().setAsignada(false);

                                                tecnicoAuxiliar.getIncidencia2().setResuelto(true);

                                                tecnicoAuxiliar.getIncidencia2().setComentarioTecnico(comentarioCerrarIncidencia);

                                                Incidencia incidenciaResuelta2 = tecnicoAuxiliar.getIncidencia2();

                                                tecnicoAuxiliar.setIncidenciaResuelta2(incidenciaResuelta2);

                                                tecnicoAuxiliar.setIncidencia1(null);
                                            } else {
                                                System.out.println(ANSI_RED + "-------------------------------------------------------------------------------------------");
                                                System.out.println("ERROR: La id introducida no coincide con la id de ninguna incidencia registrada!!");
                                                System.out.println("-------------------------------------------------------------------------------------------\n" + ANSI_RESET);
                                            }
                                        }
                                        break;
                                    case 3: //TODO POR QUÉ COJONES VUELVE AL MENÚ PRINCIPAL????
                                        //CONSULTAR INCIDENCIAS RESUELTAS
                                        if (tecnicoAuxiliar.getIncidenciaResuelta1() != null){
                                            System.out.println(tecnicoAuxiliar.getIncidenciaResuelta1().muestraIncidencia() + "\n");
                                        }

                                        if (tecnicoAuxiliar.getIncidenciaResuelta2() != null){
                                            System.out.println(tecnicoAuxiliar.getIncidenciaResuelta2().muestraIncidencia() + "\n");
                                        }

                                        if (tecnicoAuxiliar.getIncidenciaResuelta1() == null && tecnicoAuxiliar.getIncidenciaResuelta2() == null){
                                            System.out.println(ANSI_RED + "\n--------------------------------------------------------------");
                                            System.out.println("ERROR: No existen incidencias resueltas registradas!!");
                                            System.out.println("--------------------------------------------------------------\n" + ANSI_RESET);
                                        }
                                        break;
                                    case 4:
                                        //MOSTRAR PERFIL TECNICO
                                        System.out.println(tecnicoAuxiliar.toString());
                                        break;
                                    case 5:
                                        //CAMBIAR CONTRASEÑA TECNICO
                                        do {
                                            System.out.println("Introduzca su contraseña actual:");
                                            password = sc.nextLine();

                                            if (!tecnicoAuxiliar.compruebaPassword(password)){
                                                System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                                System.out.println("ERROR: Contraseña incorrecta!!");
                                                System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                            }

                                        }while (!tecnicoAuxiliar.compruebaPassword(password));

                                        //Aquí se comprueba que las contraseñas coincidan
                                        if (tecnicoAuxiliar.compruebaPassword(password)) {
                                            System.out.println("Introduzca su nueva contraseña:");
                                            password = sc.nextLine();
                                            tecnicoAuxiliar.setPasswordRegistrada(password);
                                            System.out.println(ANSI_GREEN + "-------------------------------------------------");
                                            System.out.println("Clave cambiada con éxito!!");
                                            System.out.println("-------------------------------------------------\n" + ANSI_RESET);
                                        }
                                        break;
                                    case 6:
                                        //CERRAR SESION TECNICO
                                        tecnicoAuxiliar = null;
                                        cerrarSesionTecnico = true;
                                        break;
                                    default: break;
                                }

                            } while (!cerrarSesionTecnico);
                        }
                    } else{
                        System.out.println(ANSI_RED + "----------------------------------------------------------------------");
                        System.out.println("ERROR: No existen técnicos registrados!!");
                        System.out.println("----------------------------------------------------------------------\n" + ANSI_RESET);
                    }

                    break;
                case 4:
                    //INICIAR SESIÓN COMO ADMINISTRADOR
                    do {
                        System.out.println("Introduzca su nombre de usuario:");
                        usuario = sc.nextLine();

                        System.out.println("Introduzca su contraseña:");
                        password = sc.nextLine();

                        if (!administrador1.compruebaUsuario(usuario) || !administrador1.compruebaPassword(password)) {
                            System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                            System.out.println("ERROR: Usuario o contraseña incorrectos!!");
                            System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                        }

                    } while (!administrador1.compruebaUsuario(usuario) || !administrador1.compruebaPassword(password));

                    //MENÚ ADMINISTRADOR
                    do {//TODO ADMINISTRADOR
                        pintaMenuAdministrador(administrador1.getNombre());

                        try {
                            opcionMenuAdministrador = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("--------------------------------------------------------------------------");
                            System.out.println("ERROR: Por favor, introduzca un número para seleccionar una opción");
                            System.out.println("--------------------------------------------------------------------------");
                        }

                        switch (opcionMenuAdministrador) {
                            case 1:
                                //CONSULTAR TODAS LAS INCIDENCIAS
                                System.out.println("INCIDENCIAS:");
                                if (usuario1.getIncidencia1() != null) {
                                    System.out.println(usuario1.getIncidencia1().muestraIncidencia() + "\n");
                                }

                                if (usuario1.getIncidencia2() != null) {
                                    System.out.println(usuario1.getIncidencia2().muestraIncidencia() + "\n");
                                }

                                if (usuario1.getIncidencia3() != null) {
                                    System.out.println(usuario1.getIncidencia3().muestraIncidencia() + "\n");
                                }

                                if (usuario2.getIncidencia1() != null) {
                                    System.out.println(usuario2.getIncidencia1().muestraIncidencia() + "\n");
                                }

                                if (usuario2.getIncidencia2() != null) {
                                    System.out.println(usuario2.getIncidencia2().muestraIncidencia() + "\n");
                                }

                                if (usuario2.getIncidencia3() != null) {
                                    System.out.println(usuario2.getIncidencia3().muestraIncidencia() + "\n");
                                }

                                if (usuario1.getIncidencia1() == null && usuario1.getIncidencia2() == null && usuario1.getIncidencia3() == null && usuario2.getIncidencia1() == null && usuario2.getIncidencia2() == null && usuario2.getIncidencia3() == null) {
                                    System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                    System.out.println("ERROR: No existen incidencias abiertas registradas!!");
                                    System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                }
                                break;
                            case 2:
                                //CONSULTAR TODOS LOS USUARIOS
                                System.out.println("USUARIOS:");
                                if (usuario1.getNombre() != null){
                                    System.out.println(usuario1.toString());
                                }

                                if (usuario2.getNombre() != null) {
                                    System.out.println(usuario2.toString());
                                }

                                if (usuario1.getNombre() == null && usuario2.getNombre() == null){
                                    System.out.println(ANSI_RED + "----------------------------------------------------------------------");
                                    System.out.println("ERROR: No existen usuarios registrados!!");
                                    System.out.println("----------------------------------------------------------------------\n" + ANSI_RESET);
                                }
                                break;
                            case 3:
                                //CONSULTAR TODOS LOS TÉCNICOS
                                System.out.println("TÉCNICOS");
                                if (tecnico1.getNombre() != null){
                                    System.out.println(tecnico1.toString());
                                }

                                if (tecnico2.getNombre() != null) {
                                    System.out.println(tecnico2.toString());
                                }

                                if (tecnico1.getNombre() == null && tecnico2.getNombre() == null){
                                    System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                    System.out.println("ERROR: No existen técnicos registrados!!");
                                    System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                }
                                break;
                            case 4:
                                //ASIGNAR INCIDENCIA A TÉCNICO
                                //Aquí se comprueba si hay usuarios
                                Tecnico tecnicoElegido = null;
                                Incidencia incidenciaElegida = null;
                                boolean hayIncidencias = false;

                                if (usuario1.getUsuarioRegistrado() != null || usuario2.getUsuarioRegistrado() != null) {

                                    if (tecnico1.getNombre() == null && tecnico2.getNombre() == null) {
                                        System.out.println(ANSI_RED + "----------------------------------------------------------------------------------------");
                                        System.out.println("ERROR: No existen técnicos registrados, no se pueden asignar inicidencias!!");
                                        System.out.println("----------------------------------------------------------------------------------------\n" + ANSI_RESET);
                                    } else {
                                        //Aquí se comprueba si hay incidencias asignadas a los usuarios
                                        if (usuario1.getIncidencia1() != null) {
                                            if (!usuario1.getIncidencia1().isAsignada()  && !usuario1.getIncidencia1().isResuelto()) {
                                                System.out.println(usuario1.getIncidencia1());
                                                //La asigno a verdadero, ya que si hay incidencias
                                                hayIncidencias = true;
                                            }
                                        }

                                        if (usuario1.getIncidencia2() != null) {
                                            if (!usuario1.getIncidencia2().isAsignada() && !usuario1.getIncidencia2().isResuelto()) {
                                                System.out.println(usuario1.getIncidencia2());
                                                hayIncidencias = true;
                                            }
                                        }

                                        if (usuario1.getIncidencia3() != null) {
                                            if (!usuario1.getIncidencia3().isAsignada() && !usuario1.getIncidencia3().isResuelto()) {
                                                System.out.println(usuario1.getIncidencia3());
                                                hayIncidencias = true;
                                            }
                                        }

                                        if (usuario2.getIncidencia1() != null){
                                            if (!usuario2.getIncidencia1().isAsignada() && !usuario2.getIncidencia1().isResuelto()) {
                                                System.out.println(usuario2.getIncidencia1());
                                                hayIncidencias = true;
                                            }
                                        }

                                        if (usuario2.getIncidencia2() != null){
                                            if (!usuario2.getIncidencia2().isAsignada() && !usuario2.getIncidencia2().isResuelto()) {
                                                System.out.println(usuario2.getIncidencia2());
                                                hayIncidencias = true;
                                            }
                                        }

                                        if (usuario2.getIncidencia3() != null){
                                            if (!usuario2.getIncidencia3().isAsignada() && !usuario2.getIncidencia3().isResuelto()) {
                                                System.out.println(usuario2.getIncidencia3());
                                                hayIncidencias = true;
                                            }
                                        }

                                        //Aquí se comprueba si hay incidencias entra a realizar la operación de asignación
                                        if (hayIncidencias) {

                                            do {
                                                try {
                                                    System.out.println("Introduzca la id de la incidencia que quiere asignar: ");
                                                    idIncidencia = Integer.parseInt(sc.nextLine());
                                                } catch (NumberFormatException e) {
                                                    System.out.println("\n----------------------------------------");
                                                    System.out.println("ERROR: La id debe ser un número!!");
                                                    System.out.println("----------------------------------------\n");
                                                }
                                            } while (idIncidencia == 0);

                                            //Aquí se comprueba si las ID coinciden
                                            if ( usuario1.getIncidencia1() != null && idIncidencia == usuario1.getIncidencia1().getId()) {
                                                incidenciaElegida = usuario1.getIncidencia1();
                                            } else if (usuario1.getIncidencia2() != null &&  idIncidencia == usuario1.getIncidencia2().getId()) {
                                                incidenciaElegida = usuario1.getIncidencia2();
                                            } else if (usuario1.getIncidencia3() != null &&  idIncidencia == usuario1.getIncidencia3().getId()) {
                                                incidenciaElegida = usuario1.getIncidencia3();
                                            } else if (usuario2.getIncidencia1() != null && idIncidencia == usuario2.getIncidencia1().getId()){
                                                incidenciaElegida = usuario2.getIncidencia1();
                                            } else if (usuario2.getIncidencia2() != null && idIncidencia == usuario2.getIncidencia2().getId()){
                                                incidenciaElegida = usuario2.getIncidencia2();
                                            } else if (usuario2.getIncidencia3() != null && idIncidencia == usuario2.getIncidencia3().getId()){
                                                incidenciaElegida = usuario2.getIncidencia3();
                                            } else { //TODO CÓDIGO DUPLICADO?
                                                System.out.println(ANSI_RED + "-------------------------------------------------------------------------------------------");
                                                System.out.println("ERROR: La id introducida no coincide con la id de ninguna incidencia registrada!!");
                                                System.out.println("-------------------------------------------------------------------------------------------\n" + ANSI_RESET);
                                            }

                                            //Aquí se comprueba que se haya escogido una incidencia correcta
                                            if (incidenciaElegida != null) {
                                                //Aquí se escoge un técnico
                                                if (tecnico1.getNombre() != null) {
                                                    System.out.println(tecnico1.toString());
                                                }

                                                if (tecnico2.getNombre() != null) {
                                                    System.out.println(tecnico2.toString());
                                                }

                                                do {
                                                    try {
                                                        System.out.println("Introduzca la id del tecnico al que quiere asignar la incidencia: ");
                                                        idTecnico = Integer.parseInt(sc.nextLine());
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("\n----------------------------------------");
                                                        System.out.println("ERROR: La id debe ser un número!!");
                                                        System.out.println("----------------------------------------\n");
                                                    }
                                                } while (idTecnico == 0);

                                                //Aquí se comprueba que las ID coinciden
                                                if (tecnico1.getNombre() != null && idTecnico == tecnico1.getId()) {
                                                    tecnicoElegido = tecnico1;
                                                } else if ( tecnico2.getNombre() != null && idTecnico == tecnico2.getId()) {
                                                    tecnicoElegido = tecnico2;
                                                } else {
                                                    System.out.println(ANSI_RED + "-------------------------------------------------------------------------------------------");
                                                    System.out.println("ERROR: La id introducida no coincide con la id de ningun tecnico registrado!!");
                                                    System.out.println("-------------------------------------------------------------------------------------------\n" + ANSI_RESET);
                                                }

                                                if (tecnicoElegido != null) {
                                                    if (tecnicoElegido.getIncidencia1() == null) {
                                                        tecnicoElegido.setIncidencia1(incidenciaElegida);
                                                        incidenciaElegida.setAsignada(true);
                                                        System.out.println(ANSI_GREEN + "-------------------------------------------");
                                                        System.out.println("Incidencia asignada con éxito!!");
                                                        System.out.println("-------------------------------------------\n" + ANSI_RESET);
                                                    } else if (tecnicoElegido.getIncidencia2() == null) {
                                                        tecnicoElegido.setIncidencia2(incidenciaElegida);
                                                        incidenciaElegida.setAsignada(true);
                                                        System.out.println(ANSI_GREEN + "-------------------------------------------");
                                                        System.out.println("Incidencia asignada con éxito!!");
                                                        System.out.println("-------------------------------------------\n" + ANSI_RESET);
                                                    }

                                                    if (tecnicoElegido.getIncidencia1() != null && tecnicoElegido.getIncidencia2() != null) {
                                                        System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                                        System.out.println("No se pueden asignar más incidencias a este técnico!!");
                                                        System.out.println("---------------------------------------------------------------------" + ANSI_RESET);
                                                    }
                                                }else {
                                                    System.out.println(ANSI_RED + "-------------------------------------------------------------------------------------------");
                                                    System.out.println("ERROR: La id introducida no coincide con la id de ningun tecnico registrado!!");
                                                    System.out.println("-------------------------------------------------------------------------------------------\n" + ANSI_RESET);
                                                }
                                            }
                                        } else {
                                            System.out.println(ANSI_RED + "----------------------------------------------------------------");
                                            System.out.println("ERROR: No hay incidencias registradas!!");
                                            System.out.println("----------------------------------------------------------------\n" + ANSI_RESET);
                                        }

                                    }

                                } else {
                                    //Aqui se cuentan los usuarios que no estan registrados
                                    contadorUsuariosAdmin++;
                                }

                                idIncidencia = 0;
                                idTecnico = 0;
                                break;
                            case 5:
                                //DAR DE ALTA TÉCNICO
                                Tecnico tecnicoDeRegistro;
                                if (contadorTecnicos < 2){

                                    System.out.println("Introduzca el nombre del técnico:");
                                    nombreTecnico = sc.nextLine();

                                    System.out.println("Introduzca el usuario del técnico:");
                                    usuarioTecnico = sc.nextLine();

                                    System.out.println("Introduzca el correo electrónico del técnico:");
                                    correoTecnico = sc.nextLine();

                                    do {
                                        System.out.println("Introduzca la contraseña del técnico:");
                                        passwordTecnico = sc.nextLine();

                                        System.out.println("Confirme la contraseña del técnico:");
                                        confirmacionPassword = sc.nextLine();

                                        if (!passwordTecnico.equalsIgnoreCase(confirmacionPassword)) {
                                            System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                            System.out.println("ERROR: Las contraseñas introducidas son diferentes!!");
                                            System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                        }

                                    } while (!passwordTecnico.equalsIgnoreCase(confirmacionPassword));

                                    tecnicoDeRegistro = new Tecnico(nombreTecnico, usuarioTecnico, passwordTecnico, confirmacionPassword, correoTecnico);

                                    System.out.println(ANSI_GREEN + "---------------------------------------------------------------------");
                                    System.out.println("Técnico registrado con éxito!!");
                                    System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);

                                    if (tecnico1.getNombre() == null){
                                        tecnico1 = tecnicoDeRegistro;
                                        contadorTecnicos++;
                                    } else {
                                        tecnico2 = tecnicoDeRegistro;
                                        contadorTecnicos++;
                                    }

                                } else {
                                    System.out.println(ANSI_RED + "---------------------------------------------------------------------");
                                    System.out.println("ERROR: Se ha superado el máximo de técnicos registrados!!");
                                    System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                }
                                break;
                            case 6:
                                //BORRAR TÉCNICO
                                System.out.println("TÉCNICOS REGISTRADOS:");

                                if (tecnico1.getNombre() != null){
                                    System.out.println(tecnico1.toString());
                                }

                                if (tecnico2.getNombre() != null) {
                                    System.out.println(tecnico2.toString());
                                }

                                System.out.println("Introduzca el nombre de usuario del técnico que quiere borrar:");
                                borrarTecnico = sc.nextLine();

                                //Aquí se comprueba que el nombre de usuario sea igual
                                if (borrarTecnico.equalsIgnoreCase(tecnico1.getUsuarioRegistrado())){
                                    tecnico1 = new Tecnico();
                                    contadorTecnicos = 1;
                                    System.out.println(ANSI_GREEN + "---------------------------------------------------------------------");
                                    System.out.println("Técnico borrado con éxito!!");
                                    System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                } else if (borrarTecnico.equalsIgnoreCase(tecnico2.getUsuarioRegistrado())){
                                    tecnico2 = new Tecnico();
                                    contadorTecnicos = 1;
                                    System.out.println(ANSI_GREEN + "---------------------------------------------------------------------");
                                    System.out.println("Técnico borrado con éxito!!");
                                    System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                } else {
                                    System.out.println(ANSI_RED + "--------------------------------------------------------------------------------------");
                                    System.out.println("ERROR: El nombre introducido no coincide con el de ningún técnico registrado!!");
                                    System.out.println("--------------------------------------------------------------------------------------\n" + ANSI_RESET);
                                }
                                break;
                            case 7:
                                //BORRAR USUARIO
                                System.out.println("USUARIOS REGISTRADOS:");

                                if (usuario1.getNombre() != null){
                                    System.out.println(usuario1.toString());
                                }

                                if (usuario2.getNombre() != null){
                                    System.out.println(usuario2.toString());
                                }

                                System.out.println("Introduzca el nombre de usuario del usuario que quiere borrar:");
                                borrarUsuario = sc.nextLine();

                                //Aquí se comprueba que el nombre de usuario sea igual
                                if (borrarUsuario.equalsIgnoreCase(usuario1.getUsuarioRegistrado())){
                                    usuario1 = new Usuario();
                                    contadorUsuarios = 1;
                                    System.out.println(ANSI_GREEN + "---------------------------------------------------------------------");
                                    System.out.println("Usuario borrado con éxito!!");
                                    System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                }else if (borrarUsuario.equalsIgnoreCase(usuario2.getUsuarioRegistrado())){
                                    usuario2 = new Usuario();
                                    contadorUsuarios = 1;
                                    System.out.println(ANSI_GREEN + "---------------------------------------------------------------------");
                                    System.out.println("Usuario borrado con éxito!!");
                                    System.out.println("---------------------------------------------------------------------\n" + ANSI_RESET);
                                } else {
                                    System.out.println(ANSI_RED + "-----------------------------------------------------------------------------------------");
                                    System.out.println("ERROR: El nombre introducido no coincide con el de ningún técnico registrado!!");
                                    System.out.println("-----------------------------------------------------------------------------------------\n" + ANSI_RESET);
                                }
                                break;
                            case 8:
                                //CERRAR SESIÓN ADMINISTRADOR
                                cerrarSesionAdministrador = true;
                                break;
                            case 9:
                                //CERRAR EL PROGRAMA
                                cerrarSesionAdministrador = true;
                                bandera1 = true;
                                break;
                            default: break;
                        }

                    } while (!cerrarSesionAdministrador);
                    break;
                case 5:
                    bandera1 = true;
                    break;
                default: break;
            }

        }while (!bandera1);



    }

    //Reset
    public static final String ANSI_RESET = "\u001B[0m";

    //Colores
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";
}
