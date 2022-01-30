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

public class Funciones {

    public static final String ANSI_RESET = "\u001B[0m";

    //Colores
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";

    //MENÚ PRINCIPAL
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

    //MENÚ USUARIO
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

    //MENÚ TÉCNICO
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

    //MENÚ ADMINISTRADOR
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

    //GENERAR ID INCIDENCIAS
    public static int generaID() {
        int id = ((int)(Math.random() * 100000) + 1);
        return id;
    }

    //ENVIAR CORREOS
    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
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

    //ENVIAR MENSAJE TELEGRAM
    public static boolean enviaMensajeTelegram (String mensaje) {
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
}
