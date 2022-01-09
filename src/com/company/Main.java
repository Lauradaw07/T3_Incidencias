package com.company;

import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Objetos
        //Usuarios
        Usuario usuario1 = new Usuario();

        Usuario usuario2 = new Usuario();

        //------------------------------------------------------------------------------------------------------------------------

        //Técnicos
        Tecnico tecnico1 = new Tecnico();

        Tecnico tecnico2 = new Tecnico();

        Tecnico tecnicoAuxiliar = null;

        //-------------------------------------------------------------------------------------------------------------------------

        //Administración
        Administracion administrador1 = new Administracion("Laura", "Akame", "laura@gmail.com", "valki");

        //--------------------------------------------------------------------------------------------------------------------------------------------------------

        //Incidencias
        Incidencia incidencia1 = new Incidencia();

        Incidencia incidencia2 = new Incidencia();

        Incidencia incidencia3 = new Incidencia();

        Incidencia incidencia4 = new Incidencia();

        Incidencia incidencia5 = new Incidencia();

        Incidencia incidencia6 = new Incidencia();

        Incidencia incidenciaAuxiliar = null;

        //Variables
        String usuario, password, confirmacionPassword, passwordRegistrada, nuevaPassword, borrarUsuario, borrarTecnico;

        //Contadores
        int contadorUsuarios = 0, contadorTecnicos = 0, contadorIncidencias = 0, contadorIncidenciasAsignadas = 0;

        //OPCIONES MENU
        int opcionMenuPrincipal, opcionMenuUsuario, opcionMenuTecnico, opcionMenuAdministrador, opcionMenuPrioridadIncidencias;

        //BANDERAS
        boolean bandera1 = false, cerrarSesionUsuario = false, cerrarSesionTecnico = false, cerrarSesionAdministrador = false;

        //Variables registro usuario
        String nombreUsuario, usuarioUsuario, passwordUsuario, correoUsuario, dniUsuario, telefonoUsuario;

        //Variables registro técnico

        String nombreTecnico, usuarioTecnico, correoTecnico, passwordTecnico;

        //Variables creación incidencia
        String comentario, prioridad = "";

        //Variables asignación incidencia
        String parseoId = "", idIncidencia, idTecnico;

        do {
            //Reseteo banderas
            cerrarSesionUsuario = false;
            cerrarSesionTecnico = false;
            cerrarSesionAdministrador = false;

            //Menú principal
            Scanner sc = new Scanner(System.in);
            System.out.println("---------------------------------------------------------------------");
            System.out.println("|          BIENVENIDX AL SISTEMA DE GESTIÓN DE INCIDENCIAS          |");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("| 1.- Registrarse                                                   |");
            System.out.println("| 2.- Iniciar sesión como usuario                                   |");
            System.out.println("| 3.- Iniciar sesión como técnico                                   |");
            System.out.println("| 4.- Iniciar sesión como administrador                             |");
            System.out.println("| 5.- Cerrar el programa                                            |");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Elija una opción: \s" );
            opcionMenuPrincipal = Integer.parseInt(sc.nextLine());

            switch (opcionMenuPrincipal){
                case 1:
                    //REGISTRARSE
                    Usuario usuarioDeRegistro;

                    if (contadorUsuarios < 2){
                        do {
                            //TODO ARREGLAR VOLVER A DAR DE ALTA UN USUARIO
                            //TODO CAMBIAR CONFIRMACION CONTRASEÑA
                            System.out.println("Introduzca su nombre:");
                            nombreUsuario = sc.nextLine();

                            System.out.println("Introduzca su nombre de usuario:");
                            usuarioUsuario = sc.nextLine();

                            System.out.println("Introduzca su contraseña:");
                            passwordUsuario = sc.nextLine();

                            System.out.println("Confirme su contraseña:");
                            confirmacionPassword = sc.nextLine();

                            System.out.println("Introduzca su correo electrónico:");
                            correoUsuario = sc.nextLine();

                            System.out.println("Introduzca su DNI:");
                            dniUsuario = sc.nextLine();

                            System.out.println("Introduzca su número de teléfono:");
                            telefonoUsuario = sc.nextLine();

                            usuarioDeRegistro = new Usuario(nombreUsuario, dniUsuario, usuarioUsuario, correoUsuario, passwordUsuario, confirmacionPassword, telefonoUsuario);

                            if (usuarioDeRegistro.confirmaPassword(passwordUsuario, confirmacionPassword)) {
                                System.out.println("---------------------------------------------------------------------");
                                System.out.println("Usuario registrado con éxito!!");
                                System.out.println("---------------------------------------------------------------------");
                            } else {
                                System.out.println("ERROR: Las contraseñas introducidas son diferentes");
                                System.out.println("---------------------------------------------------------------------");
                            }
                        }while (!usuarioDeRegistro.confirmaPassword(passwordUsuario, confirmacionPassword));


                        if (usuario1.getNombre() == null){
                            usuario1 = usuarioDeRegistro;
                            contadorUsuarios++;
                        } else {
                            usuario2 = usuarioDeRegistro;
                            contadorUsuarios++;
                        }
                    } else {
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("Se ha superado el máximo de usuarios registrados!!");
                        System.out.println("---------------------------------------------------------------------");
                    }
                    break;
                case 2:
                    //INICIAR SESIÓN COMO USUARIO
                    do {
                        System.out.println("\nIntroduzca su nombre de usuario:");
                        usuario = sc.nextLine();

                        System.out.println("Introduzca su contraseña:");
                        password = sc.nextLine();

                        if (!usuario1.compruebaUsuario(usuario) && !usuario1.compruebaPassword(password) && !usuario2.compruebaUsuario(usuario) && !usuario2.compruebaPassword(password)){
                            System.out.println("ERROR: Usuario o contraseña incorrectos!!");
                            System.out.println("---------------------------------------------------------------------");
                        }

                    } while (!usuario1.compruebaUsuario(usuario) && !usuario1.compruebaPassword(password) && !usuario2.compruebaUsuario(usuario) && !usuario2.compruebaPassword(password));

                    if (usuario1.compruebaUsuario(usuario) && usuario1.compruebaPassword(password)) {
                        //Menú usuario
                        do {
                            System.out.println("     ☆ BIENVENIDX " + usuario1.getNombre() + ", TIENE USTED PERFIL DE USUARIO NORMAL ☆");
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("| Menú:                                                             |");
                            System.out.println("| 1.- Registrar nueva incidencia                                    |");
                            System.out.println("| 2.- Consultar mis incidencias abiertas                            |");
                            System.out.println("| 3.- Consultar mis incidencias cerradas                            |");
                            System.out.println("| 4.- Mostrar mi perfil                                             |");
                            System.out.println("| 5.- Cambiar clave de acceso                                       |");
                            System.out.println("| 6.- Cerrar sesión                                                 |");
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("Elija una opción: \s" );
                            opcionMenuUsuario = Integer.parseInt(sc.nextLine());

                            switch (opcionMenuUsuario) {
                                case 1:
                                    //REGISTRAR NUEVA INCIDENCIA
                                    //TODO ARREGLAR MÁXIMO DE INCIDENCIAS (3 POR USUARIO)

                                    if (contadorIncidencias < 3) {
                                        Incidencia incidenciaDeRegistro;

                                        if (usuario1.getIncidencia1() == null) {
                                            System.out.println("Introduzca un comentario:");
                                            comentario = sc.nextLine();

                                            System.out.println("Indique la prioridad de la incidencia:");
                                            System.out.println("1.- Baja");
                                            System.out.println("2.- Media");
                                            System.out.println("3.- Alta");
                                            opcionMenuPrioridadIncidencias = Integer.parseInt(sc.nextLine());

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
                                                    System.out.println("ERROR: Acción imposible!!");
                                                    System.out.println("-----------------------------------------------------------------------------------");
                                                    break;
                                            }

                                            incidencia1 = new Incidencia(usuario1, null, comentario, prioridad, false,false);

                                            usuario1.setIncidencia1(incidencia1);

                                            System.out.println("---------------------------------------------------------------------");
                                            System.out.println("Incidencia registrada con éxito!!");
                                            System.out.println("---------------------------------------------------------------------");

                                            contadorIncidencias++;

                                        } else if (usuario1.getIncidencia2() == null) {
                                            System.out.println("Introduzca un comentario:");
                                            comentario = sc.nextLine();

                                            System.out.println("Indique la prioridad de la incidencia:");
                                            System.out.println("1.- Baja");
                                            System.out.println("2.- Media");
                                            System.out.println("3.- Alta");
                                            opcionMenuPrioridadIncidencias = Integer.parseInt(sc.nextLine());

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
                                                    System.out.println("ERROR: Acción imposible!!");
                                                    System.out.println("-----------------------------------------------------------------------------------");
                                                    break;
                                            }

                                            incidencia2 = new Incidencia(usuario1, null ,comentario, prioridad, false,false);

                                            usuario1.setIncidencia2(incidencia2);

                                            System.out.println("---------------------------------------------------------------------");
                                            System.out.println("Incidencia registrada con éxito!!");
                                            System.out.println("---------------------------------------------------------------------");

                                            contadorIncidencias++;

                                        } else {
                                            System.out.println("Introduzca un comentario:");
                                            comentario = sc.nextLine();


                                            System.out.println("Indique la prioridad de la incidencia:");
                                            System.out.println("1.- Baja");
                                            System.out.println("2.- Media");
                                            System.out.println("3.- Alta");
                                            opcionMenuPrioridadIncidencias = Integer.parseInt(sc.nextLine());

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
                                                    System.out.println("ERROR: Acción imposible!!");
                                                    System.out.println("-----------------------------------------------------------------------------------");
                                                    break;
                                            }

                                            incidencia3 = new Incidencia(usuario1,null,comentario, prioridad,false,false);

                                            usuario1.setIncidencia3(incidencia3);

                                            System.out.println("---------------------------------------------------------------------");
                                            System.out.println("Incidencia registrada con éxito!!");
                                            System.out.println("---------------------------------------------------------------------");

                                            contadorIncidencias++;
                                        }
                                    } else {
                                        System.out.println("---------------------------------------------------------------------");
                                        System.out.println("Se ha superado el número máximo de incidencias registradas!!");
                                        System.out.println("---------------------------------------------------------------------");
                                    }
                                    break;
                                case 2:
                                    //CONSULTAR INCIDENCIAS ABIERTAS
                                    if (incidencia1.getUsuario() != null){
                                        if (!incidencia1.isResuelto()){
                                            System.out.println(incidencia1.muestraIncidencia());
                                        }
                                    }

                                    if (incidencia2.getUsuario() != null){
                                        if (!incidencia2.isResuelto()){
                                            System.out.println(incidencia2.muestraIncidencia());
                                        }
                                    }

                                    if (incidencia3.getUsuario() != null){
                                        if (!incidencia3.isResuelto()){
                                            System.out.println(incidencia3.muestraIncidencia());
                                        }
                                    }

                                    if (incidencia1.getUsuario() == null && incidencia2.getUsuario() == null && incidencia3.getUsuario() == null){
                                        System.out.println("---------------------------------------------------------------------");
                                        System.out.println("No existen incidencias abiertas registradas!!");
                                        System.out.println("---------------------------------------------------------------------");
                                    }

                                    break;
                                case 3:
                                    //CONSULTAR INCIDENCIAS CERRADAS
                                    //TODO CONSULTAR INCIDENCIAS CERRADAS
                                    break;
                                case 4:
                                    //MOSTRAR PERFIL DEL USUARIO
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println(usuario1.toString());
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    break;
                                case 5:
                                    //CAMBIAR CONTRASEÑA USUARIO
                                    do {
                                        System.out.println("Introduzca su contraseña actual:");
                                        password = sc.nextLine();

                                        if (!usuario1.compruebaPassword(password)){
                                            System.out.println("ERROR: Contraseña incorrecta!!");
                                            System.out.println("---------------------------------------------------------------------");
                                        }

                                    }while (!usuario1.compruebaPassword(password));

                                    System.out.println("Introduzca su nueva contraseña:");
                                    nuevaPassword = sc.nextLine();
                                    usuario1.setPasswordRegistrada(nuevaPassword);
                                    break;
                                case 6:
                                    //CERRAR SESIÓN USUARIO
                                    contadorIncidencias = 0;
                                    cerrarSesionUsuario = true;
                                    break;
                                default:
                                    System.out.println("ERROR: Acción imposible!!");
                                    System.out.println("---------------------------------------------------------------------");
                                    break;
                            }

                        } while (!cerrarSesionUsuario);
                    }else if (usuario2.compruebaUsuario(usuario) && usuario2.compruebaPassword(password)) {
                        do {
                            System.out.println("     ☆ BIENVENIDX " + usuario2.getNombre() + ", TIENE USTED PERFIL DE USUARIO NORMAL ☆");
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("| Menú:                                                             |");
                            System.out.println("| 1.- Registrar nueva incidencia                                    |");
                            System.out.println("| 2.- Consultar mis incidencias abiertas                            |");
                            System.out.println("| 3.- Consultar mis incidencias cerradas                            |");
                            System.out.println("| 4.- Mostrar mi perfil                                             |");
                            System.out.println("| 5.- Cambiar clave de acceso                                       |");
                            System.out.println("| 6.- Cerrar sesión                                                 |");
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("Elija una opción: \s" );
                            opcionMenuUsuario = Integer.parseInt(sc.nextLine());

                            switch (opcionMenuUsuario) {
                                case 1:
                                    //REGISTRAR NUEVA INCIDENCIA
                                    //TODO ARREGLAR MÁXIMO DE INCIDENCIAS (3 POR USUARIO)

                                    if (contadorIncidencias < 3) {
                                        Incidencia incidenciaDeRegistro;

                                        if (usuario2.getIncidencia1() == null) {
                                            System.out.println("Introduzca un comentario:");
                                            comentario = sc.nextLine();

                                            System.out.println("Indique la prioridad de la incidencia:");
                                            System.out.println("1.- Baja");
                                            System.out.println("2.- Media");
                                            System.out.println("3.- Alta");
                                            opcionMenuPrioridadIncidencias = Integer.parseInt(sc.nextLine());

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
                                                    System.out.println("ERROR: Acción imposible!!");
                                                    System.out.println("-----------------------------------------------------------------------------------");
                                                    break;
                                            }

                                            incidencia4 = new Incidencia(usuario2, null,comentario, prioridad, false,false);

                                            usuario2.setIncidencia1(incidencia4);

                                            System.out.println("---------------------------------------------------------------------");
                                            System.out.println("Incidencia registrada con éxito!!");
                                            System.out.println("---------------------------------------------------------------------");

                                            contadorIncidencias++;

                                        } else if (usuario2.getIncidencia2() == null) {
                                            System.out.println("Introduzca un comentario:");
                                            comentario = sc.nextLine();

                                            System.out.println("Indique la prioridad de la incidencia:");
                                            System.out.println("1.- Baja");
                                            System.out.println("2.- Media");
                                            System.out.println("3.- Alta");
                                            opcionMenuPrioridadIncidencias = Integer.parseInt(sc.nextLine());

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
                                                    System.out.println("ERROR: Acción imposible!!");
                                                    System.out.println("-----------------------------------------------------------------------------------");
                                                    break;
                                            }

                                            incidencia5 = new Incidencia(usuario2, null,comentario, prioridad, false,false);

                                            usuario2.setIncidencia2(incidencia5);

                                            System.out.println("---------------------------------------------------------------------");
                                            System.out.println("Incidencia registrada con éxito!!");
                                            System.out.println("---------------------------------------------------------------------");

                                            contadorIncidencias++;

                                        } else {
                                            System.out.println("Introduzca un comentario:");
                                            comentario = sc.nextLine();

                                            System.out.println("Indique la prioridad de la incidencia:");
                                            System.out.println("1.- Baja");
                                            System.out.println("2.- Media");
                                            System.out.println("3.- Alta");
                                            opcionMenuPrioridadIncidencias = Integer.parseInt(sc.nextLine());

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
                                                    System.out.println("ERROR: Acción imposible!!");
                                                    System.out.println("-----------------------------------------------------------------------------------");
                                                    break;
                                            }

                                            incidencia6 = new Incidencia(usuario2,null,comentario, prioridad, false,false);

                                            usuario2.setIncidencia3(incidencia6);

                                            System.out.println("---------------------------------------------------------------------");
                                            System.out.println("Incidencia registrada con éxito!!");
                                            System.out.println("---------------------------------------------------------------------");

                                            contadorIncidencias++;
                                        }
                                    } else {
                                        System.out.println("---------------------------------------------------------------------");
                                        System.out.println("Se ha superado el número máximo de incidencias registradas!!");
                                        System.out.println("---------------------------------------------------------------------\n");
                                    }

                                    break;
                                case 2:
                                    //CONSULTAR INCIDENCIAS ABIERTAS
                                    if (incidencia4.getUsuario() != null){
                                        if (!incidencia4.isResuelto()){
                                            System.out.println(incidencia4.muestraIncidencia());
                                        }
                                    }

                                    if (incidencia5.getUsuario() != null){
                                        if (!incidencia5.isResuelto()){
                                            System.out.println(incidencia5.muestraIncidencia());
                                        }
                                    }

                                    if (incidencia6.getUsuario() != null){
                                        if (!incidencia6.isResuelto()){
                                            System.out.println(incidencia6.muestraIncidencia());
                                        }
                                    }

                                    if (incidencia4.getUsuario() == null && incidencia5.getUsuario() == null && incidencia6.getUsuario() == null){
                                        System.out.println("---------------------------------------------------------------------");
                                        System.out.println("No existen incidencias abiertas registradas!!");
                                        System.out.println("---------------------------------------------------------------------");
                                    }

                                    break;
                                case 3:
                                    //CONSULTAR INCIDENCIAS CERRADAS
                                    //TODO CONSULTAR INCIDENCIAS CERRADAS
                                    break;
                                case 4:
                                    //MOSTRAR PERFIL DEL USUARIO
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println(usuario2.toString());
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    break;
                                case 5:
                                    //CAMBIAR CONTRASEÑA USUARIO
                                    do {
                                        System.out.println("Introduzca su contraseña actual:");
                                        password = sc.nextLine();

                                        if (!usuario2.compruebaPassword(password)){
                                            System.out.println("ERROR: Contraseña incorrecta!!");
                                            System.out.println("---------------------------------------------------------------------");
                                        }

                                    }while (!usuario2.compruebaPassword(password));

                                    System.out.println("Introduzca su nueva contraseña:");
                                    nuevaPassword = sc.nextLine();
                                    usuario2.setPasswordRegistrada(nuevaPassword);
                                    break;
                                case 6:
                                    //CERRAR SESIÓN USUARIO
                                    contadorIncidencias = 0;
                                    cerrarSesionUsuario = true;
                                    break;
                                default:
                                    System.out.println("ERROR: Acción imposible!!");
                                    System.out.println("---------------------------------------------------------------------");
                                    break;
                            }

                        } while (!cerrarSesionUsuario);
                    }else{
                        System.out.println("Usuario o contraseña incorrectos!! \n");
                        System.out.println("---------------------------------------------------------------------");
                    }
                    break;
                case 3:
                    //Iniciar sesión como técnico
                    do {
                        System.out.println("Introduzca su nombre de usuario:");
                        usuario = sc.nextLine();

                        System.out.println("Introduzca su contraseña:");
                        password = sc.nextLine();

                        if (tecnico1.compruebaUsuario(usuario) && tecnico1.compruebaPassword(password)) {
                            tecnicoAuxiliar = tecnico1;
                        }

                        if (tecnico2.compruebaUsuario(usuario) && tecnico2.compruebaPassword(password)) {
                            tecnicoAuxiliar = tecnico2;
                        }

                        if ((!tecnico1.compruebaUsuario(usuario) || !tecnico1.compruebaPassword(password)) && (!tecnico2.compruebaUsuario(usuario) || !tecnico2.compruebaPassword(password))) {
                            System.out.println("ERROR: Usuario o contraseña incorrectos!!");
                            System.out.println("---------------------------------------------------------------------\n");
                        }

                    } while (!tecnico1.compruebaUsuario(usuario) && !tecnico1.compruebaPassword(password) && !tecnico2.compruebaUsuario(usuario) && !tecnico2.compruebaPassword(password));

                    if (tecnicoAuxiliar != null) {
                        //Menú técnico
                        do {

                            System.out.println("    ☆ BIENVENIDX " + tecnicoAuxiliar.getNombre() + ", TIENE USTED PERFIL DE TÉCNICO ☆");
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("| Menú:                                                             |");
                            System.out.println("| 1.- Consultar las incidencias que tengo asignadas                 |");
                            System.out.println("| 2.- Marcar una incidencia como cerrada                            |");
                            System.out.println("| 3.- Consultar las incidencias que he resuelto                     |");
                            System.out.println("| 4.- Mostrar perfil                                                |");
                            System.out.println("| 5.- Cambiar clave de acceso                                       |");
                            System.out.println("| 6.- Cerrar sesión                                                 |");
                            System.out.println("---------------------------------------------------------------------");
                            System.out.println("Elija una opción: \s" );

                            opcionMenuTecnico = Integer.parseInt(sc.nextLine());

                            switch (opcionMenuTecnico) {
                                case 1:
                                    //CONSULTAR INCIDENCIAS ASIGNADAS
                                    //TODO CONSULTAR INCIDENCIAS ASIGNADAS

                                    break;
                                case 2:
                                    //MARCAR INCIDENCIA COMO CERRADA
                                    //TODO MARCAR INCIDENCIA COMO CERRADA
                                    break;
                                case 3:
                                    //CONSULTAR INCIDENCIAS RESUELTAS
                                    //TODO CONSULTAR INCIDENCIAS RESUELTAS

                                    break;
                                case 4:
                                    //MOSTRAR PERFIL TECNICO
                                    System.out.println("---------------------------------------------------------------------");
                                    System.out.println(tecnicoAuxiliar.toString());
                                    System.out.println("---------------------------------------------------------------------");
                                    break;
                                case 5:
                                    //CAMBIAR CONTRASEÑA TECNICO
                                    do {
                                        System.out.println("Introduzca su contraseña actual:");
                                        password = sc.nextLine();

                                        if (!tecnicoAuxiliar.compruebaPassword(password)){
                                            System.out.println("ERROR: Contraseña incorrecta!!");
                                            System.out.println("---------------------------------------------------------------------");
                                        }

                                    }while (!tecnicoAuxiliar.compruebaPassword(password));

                                    if (tecnicoAuxiliar.compruebaPassword(password)) {
                                        System.out.println("Introduzca su nueva contraseña:");
                                        password = sc.nextLine();
                                        tecnicoAuxiliar.setPasswordRegistrada(password);
                                    }
                                    break;
                                case 6:
                                    //CERRAR SESION TECNICO
                                    tecnicoAuxiliar = null;
                                    cerrarSesionTecnico = true;
                                    break;
                                default:
                                    System.out.println("ERROR: Acción imposible!!");
                                    System.out.println("---------------------------------------------------------------------");
                                    break;
                            }

                        } while (!cerrarSesionTecnico);
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
                            System.out.println("Usuario o contraseña incorrectos!! \n");
                            System.out.println("---------------------------------------------------------------------");
                        }

                    } while (!administrador1.compruebaUsuario(usuario) || !administrador1.compruebaPassword(password));

                    //Menú administrador
                    do {
                        System.out.println("    ☆ Bienvenidx " + administrador1.getNombre() + ", tiene usted perfil de administración ☆");
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
                        System.out.println("Elija una opción: \s" );
                        opcionMenuAdministrador = Integer.parseInt(sc.nextLine());

                        switch (opcionMenuAdministrador) {
                            case 1:
                                //CONSULTAR TODAS LAS INCIDENCIAS
                                if (incidencia1.getUsuario() != null){
                                    System.out.println(incidencia1.muestraIncidencia() + "\n");
                                }
                                if (incidencia2.getUsuario() != null){
                                    System.out.println(incidencia2.muestraIncidencia() + "\n");
                                }

                                if (incidencia3.getUsuario() != null){
                                    System.out.println(incidencia3.muestraIncidencia() + "\n");
                                }

                                if (incidencia4.getUsuario() != null){
                                    System.out.println(incidencia4.muestraIncidencia() + "\n");
                                }

                                if (incidencia5.getUsuario() != null){
                                    System.out.println(incidencia5.muestraIncidencia() + "\n");
                                }

                                if (incidencia6.getUsuario() != null){
                                    System.out.println(incidencia6.muestraIncidencia() + "\n");
                                }

                                if (incidencia1.getUsuario() == null && incidencia2.getUsuario() == null && incidencia3.getUsuario() == null && incidencia4.getUsuario() == null && incidencia5.getUsuario() == null && incidencia6.getUsuario() == null){
                                    System.out.println("---------------------------------------------------------------------");
                                    System.out.println("No existen incidencias registradas!!");
                                    System.out.println("---------------------------------------------------------------------");
                                }

                                break;
                            case 2:
                                //CONSULTAR TODOS LOS USUARIOS
                                if (usuario1.getNombre() != null){
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println(usuario1.toString());
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                }

                                if (usuario2.getNombre() != null) {
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println(usuario2.toString());
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                }

                                if (usuario1.getNombre() == null && usuario2.getNombre() == null){
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println("No existen usuarios registrados!!");
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                }
                                break;
                            case 3:
                                //CONSULTAR TODOS LOS TÉCNICOS
                                if (tecnico1.getNombre() != null){
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println(tecnico1.toString());
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                }

                                if (tecnico2.getNombre() != null) {
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println(tecnico2.toString());
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                }

                                if (tecnico1.getNombre() == null && tecnico2.getNombre() == null){
                                    System.out.println("---------------------------------------------------------------------");
                                    System.out.println("No existen técnicos registrados!!");
                                    System.out.println("---------------------------------------------------------------------");
                                }

                                break;
                            case 4:
                                //ASIGNAR INCIDENCIA A TÉCNICO
                                //TODO ASIGNAR INCIDENCIA A TÉCNICO (COMPROBACIÓN CON PARSEO NO FUNCIONA)
                                System.out.println("INCIDENCIAS SIN ASIGNAR:");

                                if (incidencia1.getUsuario() != null){
                                    if (!incidencia1.isAsignada()){
                                        System.out.println(incidencia1.muestraIncidencia());
                                        parseoId = Integer.toString(incidencia1.getId());
                                    }
                                }

                                if (incidencia2.getUsuario() != null){
                                    if (!incidencia2.isAsignada()){
                                        System.out.println(incidencia2.muestraIncidencia());
                                        parseoId = Integer.toString(incidencia2.getId());
                                    }
                                }

                                if (incidencia3.getUsuario() != null){
                                    if (!incidencia1.isAsignada()){
                                        System.out.println(incidencia3.muestraIncidencia());
                                        parseoId = Integer.toString(incidencia3.getId());
                                    }
                                }

                                if (incidencia4.getUsuario() != null){
                                    if (!incidencia1.isAsignada()){
                                        System.out.println(incidencia4.muestraIncidencia());
                                        parseoId = Integer.toString(incidencia4.getId());
                                    }
                                }

                                if (incidencia5.getUsuario() != null){
                                    if (!incidencia1.isAsignada()){
                                        System.out.println(incidencia5.muestraIncidencia());
                                        parseoId = Integer.toString(incidencia5.getId());
                                    }
                                }

                                if (incidencia6.getUsuario() != null){
                                    if (!incidencia1.isAsignada()){
                                        System.out.println(incidencia6.muestraIncidencia());
                                        parseoId = Integer.toString(incidencia6.getId());
                                    }
                                }
                                System.out.println("Introduzca la id de la incidencia que quiere asignar:");
                                idIncidencia = sc.nextLine();

                                if (idIncidencia.equalsIgnoreCase(parseoId)){

                                    System.out.println("TÉCNICOS:");

                                    if (tecnico1.getNombre() != null){
                                        System.out.println("--------------------------------------------------------------------------------------------------------");
                                        System.out.println(tecnico1.toString());
                                        System.out.println("--------------------------------------------------------------------------------------------------------");
                                    }

                                    if (tecnico2.getNombre() != null) {
                                        System.out.println("--------------------------------------------------------------------------------------------------------");
                                        System.out.println(tecnico2.toString());
                                        System.out.println("--------------------------------------------------------------------------------------------------------");
                                    }

                                    if (tecnico1.getNombre() == null && tecnico2.getNombre() == null){
                                        System.out.println("---------------------------------------------------------------------");
                                        System.out.println("No existen técnicos registrados!!");
                                        System.out.println("---------------------------------------------------------------------");
                                    }

                                    System.out.println("Introduzca el nombre de usuario del técnico al que quiere asignar la incidencia:");
                                    idTecnico = sc.nextLine();

                                    if (idTecnico.equalsIgnoreCase(tecnico1.getUsuarioRegistrado())){
                                        if (contadorIncidenciasAsignadas < 2){

                                        }else {
                                            System.out.println("------------------------------------------------------------------");
                                            System.out.println("No se pueden asignar más incidencias a este técnico!!");
                                            System.out.println("------------------------------------------------------------------");
                                        }
                                    }else if (idTecnico.equalsIgnoreCase(tecnico2.getUsuarioRegistrado())){
                                        if (contadorIncidenciasAsignadas < 2){

                                        }else {
                                            System.out.println("------------------------------------------------------------------");
                                            System.out.println("No se pueden asignar más incidencias a este técnico!!");
                                            System.out.println("------------------------------------------------------------------");
                                        }
                                    }

                                }else {
                                    System.out.println("--------------------------------------------------------------------------");
                                    System.out.println("ERROR: La id introducida no coincide con la id de ningún técnico!!");
                                    System.out.println("--------------------------------------------------------------------------");
                                }

                                break;
                            case 5:
                                //DAR DE ALTA TÉCNICO
                                //TODO ARREGLAR VOLVER A DAR DE ALTA UN TÉCNICO
                                Tecnico tecnicoDeRegistro;
                                if (contadorTecnicos < 2){
                                    do {
                                        System.out.println("Introduzca el nombre del técnico:");
                                        nombreTecnico = sc.nextLine();

                                        System.out.println("Introduzca el usuario del técnico:");
                                        usuarioTecnico = sc.nextLine();;

                                        System.out.println("Introduzca el correo electrónico del técnico:");
                                        correoTecnico = sc.nextLine();

                                        System.out.println("Introduzca la contraseña del técnico:");
                                        passwordTecnico = sc.nextLine();

                                        System.out.println("Confirme la contraseña del técnico:");
                                        confirmacionPassword = sc.nextLine();

                                        tecnicoDeRegistro = new Tecnico(nombreTecnico, usuarioTecnico, passwordTecnico, confirmacionPassword, correoTecnico);

                                        if (tecnicoDeRegistro.confirmaPassword(passwordTecnico, confirmacionPassword)) {
                                            System.out.println("---------------------------------------------------------------------");
                                            System.out.println("Técnico registrado con éxito!!");
                                            System.out.println("---------------------------------------------------------------------");
                                        } else {
                                            System.out.println("ERROR: Las contraseñas introducidas son diferentes!!");
                                            System.out.println("---------------------------------------------------------------------");
                                        }
                                    }while (!tecnicoDeRegistro.confirmaPassword(passwordTecnico, confirmacionPassword));


                                    if (tecnico1.getNombre() == null){
                                        tecnico1 = tecnicoDeRegistro;
                                        contadorTecnicos++;
                                    } else {
                                        tecnico2 = tecnicoDeRegistro;
                                        contadorTecnicos++;
                                    }

                                } else {
                                    System.out.println("---------------------------------------------------------------------");
                                    System.out.println("Se ha superado el máximo de técnicos registrados!!");
                                    System.out.println("---------------------------------------------------------------------\n");
                                }
                                break;
                            case 6:
                                //BORRAR TÉCNICO
                                System.out.println("TÉCNICOS REGISTRADOS:");

                                if (tecnico1.getNombre() != null){
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println(tecnico1.toString());
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                }

                                if (tecnico2.getNombre() != null) {
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println(tecnico2.toString());
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                }

                                System.out.println("Introduzca el nombre de usuario del técnico que quiere borrar:");
                                borrarTecnico = sc.nextLine();

                                if (borrarTecnico.equalsIgnoreCase(tecnico1.getUsuarioRegistrado())){
                                    tecnico1 = new Tecnico();
                                    System.out.println("---------------------------------------------------------------------");
                                    System.out.println("Técnico borrado con éxito!!");
                                    System.out.println("---------------------------------------------------------------------");
                                } else if (borrarTecnico.equalsIgnoreCase(tecnico2.getUsuarioRegistrado())){
                                    tecnico2 = new Tecnico();
                                    System.out.println("---------------------------------------------------------------------");
                                    System.out.println("Técnico borrado con éxito!!");
                                    System.out.println("---------------------------------------------------------------------");
                                } else {
                                    System.out.println("--------------------------------------------------------------------------------------");
                                    System.out.println("ERROR: El nombre introducido no coincide con el de ningún técnico registrado!!");
                                    System.out.println("--------------------------------------------------------------------------------------");
                                }
                                break;
                            case 7:
                                //BORRAR USUARIO
                                System.out.println("USUARIOS REGISTRADOS:");

                                if (usuario1.getNombre() != null){
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println(usuario1.toString());
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                }

                                if (usuario2.getNombre() != null){
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println(usuario2.toString());
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                }

                                System.out.println("Introduzca el nombre de usuario del usuario que quiere borrar:");
                                borrarUsuario = sc.nextLine();

                                if (borrarUsuario.equalsIgnoreCase(usuario1.getUsuarioRegistrado())){
                                    usuario1 = new Usuario();
                                    System.out.println("---------------------------------------------------------------------");
                                    System.out.println("Usuario borrado con éxito!!");
                                    System.out.println("---------------------------------------------------------------------");
                                }else if (borrarUsuario.equalsIgnoreCase(usuario2.getUsuarioRegistrado())){
                                    usuario2 = new Usuario();
                                    System.out.println("---------------------------------------------------------------------");
                                    System.out.println("Usuario borrado con éxito!!");
                                    System.out.println("---------------------------------------------------------------------");
                                } else {
                                    System.out.println("---------------------------------------------------------------------");
                                    System.out.println("ERROR: El nombre introducido no coincide con el de ningún técnico registrado!!");
                                    System.out.println("---------------------------------------------------------------------");
                                }
                                break;
                            case 8:
                                //CERRAR SESIÓN ADMINISTRADOR
                                cerrarSesionAdministrador = true;
                                break;
                            case 9:
                                cerrarSesionAdministrador = true;
                                bandera1 = true;
                                break;
                            default:
                                System.out.println("Acción imposible");
                                System.out.println("---------------------------------------------------------------------");
                                break;
                        }

                    } while (!cerrarSesionAdministrador);
                    break;
                case 5:
                    bandera1 = true;
                    break;
                default:
                    break;
            }


        }while (!bandera1);
    }
}
