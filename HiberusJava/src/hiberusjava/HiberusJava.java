package hiberusjava;

import java.util.Random;
import java.util.Scanner;

public class HiberusJava {
    public static String [][] tablero = new String [10][10];
    public static Random aleatorio = new Random();
    public static int turno = 1;
    public static int yTablero;
    public static int xTablero;
    public static int bucle = -1; // Generamos un bucle infinito
    
    public static void main(String[] args) {
        System.out.println("_______________________________ _______________  ____ ________ ");
        System.out.println("\\_   _____/\\_   ___ \\__    ___/ \\_____  \\   _  \\/_   /   __   \\");
        System.out.println(" |    __)  /    \\  \\/ |    |     /  ____/  /_\\  \\|   \\____    /");
        System.out.println(" |     \\   \\     \\____|    |    /       \\  \\_/   \\   |  /    / ");
        System.out.println(" \\___  /    \\______  /|____|    \\_______ \\_____  /___| /____/  ");
        System.out.println("     \\/            \\/                   \\/     \\/              ");
        System.out.println();
        System.out.println("              Creado por Victor Jose Barneto Magen");
        System.out.println();
        //yTablero = 10;
        //xTablero = 10;
        Scanner leer = new Scanner(System.in);
        do {
            System.out.println("Inserte la largura del tablero:");
            try {
                yTablero = leer.nextInt();
            }catch(Exception e) {
                System.out.println("Error");
                leer.next();
            }
        }while(yTablero < 2);
        do {
            System.out.println("Inserte la anchura del tablero:");
            try {
                xTablero = leer.nextInt();
            }catch (Exception e) {
                System.out.println("Error");
                leer.next();
            }
        }while(xTablero < 2);
        tablero = new String [yTablero][xTablero];
        
        CrearTablero(); // Asigna todo muerto
        
        // MENU
        int opcion = 0;
        do {
            System.out.println("Elige el método de generación (introduzca el numero):");
            System.out.println("1) Aleatorio (introducir número)");
            System.out.println("2) Aleatorio automático (se generan 8)");
            System.out.println("3) Introducir de forma manual");
            try {
                opcion = leer.nextInt(); // Seleccionar la opción
            }catch (Exception e) {
                System.out.println("Error");
                leer.next();
            }
            switch (opcion) {
                case 1: // 1) Aleatorio (introducir número)
                    System.out.println("Intoduzca el número de células:");
                    try {
                        Aleatorio(leer.nextInt());
                    }catch (Exception e) {
                        System.out.println("Error");
                        leer.next();
                    }
                    break;
                case 2: // 2) Aleatorio automático (se generan 8)
                    if (8 <= yTablero*xTablero) {
                        Asignar8();
                    }else{
                        System.out.println("Error no caben en el tablero");
                    }
                    break;
                case 3: // 3) Introducir de forma manual
                    int manualY = 0;
                    int manualX = 0;
                    do {
                        System.out.println("Introduce -1 para parar");
                        System.out.println("Introduce la cordenada vertical");
                        try {
                            manualY = leer.nextInt();
                        }catch (Exception e) {
                            System.out.println("Error");
                            leer.next();
                        }
                        if (manualY != -1) {
                            System.out.println("Introduce la coordenada horizontal");
                            try {
                                manualX = leer.nextInt();
                            }catch (Exception e) {
                                System.out.println("Error");
                                leer.next();
                            }
                            
                            try {
                                if ("*".equals(tablero[manualY][manualX])) {
                                    System.out.println("Ya hay uno en esa coordenada");
                                }else{
                                    tablero[manualY][manualX] = "*";
                                }
                            }catch(Exception e) {
                                System.out.println("Error");
                            }
                        }
                    }while(manualY != -1);
                    System.out.println("Completado con éxito");
                    break;
                default:
                    System.out.println("Error.");
            }
        }while (opcion != 1 && opcion != 2 && opcion != 3);
        
        for (bucle = 0; bucle > -1; bucle++) {
            ImprimirTablero();
            ActualizarCelulas();
        }
    }
    
    public static void CrearTablero () {
        // Asignando vacio al tablero
        for (int i = 0; i < yTablero; i++) { // Vertical
            for (int j = 0; j < xTablero; j++) { // Horizontal
                tablero[i][j] = " ";
            }
        }
    }
    
    public static void Asignar8 () {
        int errorBucle = 0;
        // Asignando celulas
        do {
            int y = aleatorio.nextInt(yTablero);
            int x = aleatorio.nextInt(xTablero);
            tablero[y][x] = "*";
            for (int i = 0; i < 7; i++) {
                boolean asignado;
                errorBucle = 0;
                do {
                    asignado = false;
                    int posicion = aleatorio.nextInt(8)+1;
                    switch (posicion) {
                        case 1: // Izquierda
                            if (x-1 >= 0 && tablero[y][x-1] != "*") {
                                x--;
                                tablero[y][x] = "*";
                                asignado = true;
                            }
                            break;
                        case 2: // Derecha
                            if (x+1 < xTablero && tablero[y][x+1] != "*") {
                                x++;
                                tablero[y][x] = "*";
                                asignado = true;
                            }
                            break;
                        case 3: // Arriba
                            if (y-1 >= 0 && tablero[y-1][x] != "*") {
                                y--;
                                tablero[y][x] = "*";
                                asignado = true;
                            }
                            break;
                        case 4: // Abajo
                            if (y+1 < yTablero && tablero[y+1][x] != "*") {
                                y++;
                                tablero[y][x] = "*";
                                asignado = true;
                            }
                            break;
                        case 5: // Arriba-Izquierda
                            if (y-1 >= 0 && x-1 >= 0 && tablero[y-1][x-1] != "*") {
                                y--;
                                x--;
                                tablero[y][x] = "*";
                                asignado = true;
                            }
                            break;
                        case 6: // Abajo-Derecha
                            if (y+1 < yTablero && x+1 < xTablero && tablero[y+1][x+1] != "*") {
                                y++;
                                x++;
                                tablero[y][x] = "*";
                                asignado = true;
                            }
                            break;
                        case 7: // Abajo-Izquierda
                            if (y-1 >= 0 && x+1 < xTablero && tablero[y-1][x+1] != "*") {
                                y--;
                                x++;
                                tablero[y][x] = "*";
                                asignado = true;
                            }
                            break;
                        case 8: // Arriba-Derecha
                            if (y+1 < yTablero && x-1 >= 0 && tablero[y+1][x-1] != "*") {
                                y++;
                                x--;
                                tablero[y][x] = "*";
                                asignado = true;
                            }
                            break;
                    }
                    if (!asignado) { // Se le suma cuando falla
                        errorBucle++;
                    }
                    
                }while(errorBucle < 9 && !asignado || errorBucle >= 9 && asignado);
                
                if (errorBucle >= 9) { // Si no se puede termina el bucle
                    i = 7;
                }
            }
            
            if (errorBucle >= 9) { // Si falla mucho se limpia el tablero
                CrearTablero();
            }
        }while(errorBucle >= 9); // Si tiene muchos errores se limpia (RESET)
    }
    
    public static void Aleatorio (int numeroDeCelulas) {
        if (numeroDeCelulas <= yTablero*xTablero) {
            int y;
            int x;
            for (int i = 0; i < numeroDeCelulas; i++) {
                do { // Genera unas cordenadas que no tengan una viva
                    y = aleatorio.nextInt(yTablero);
                    x = aleatorio.nextInt(xTablero);
                }while("*".contains(tablero[y][x]));

                tablero[y][x] = "*"; // Si no tiene una viva se le asigna
            }
        }else{
            System.out.println("Error no caben en el tablero");
        }
    }
    
    public static void ImprimirTablero () {
        System.out.println();
        System.out.println("Turno: "+turno);
        for (int i = 0; i < yTablero; i++) {
            System.out.print("|");
            for (int j = 0; j < xTablero; j++) {
                System.out.print(tablero[i][j]+"|");
            }
            System.out.println(); // Cuando acaba la fila salta de linea
        }
        turno++;
    }
    
    public static void ActualizarCelulas () {
        String [][] tableroProvisional = new String[yTablero][xTablero];
        boolean finBucle = false; // Parar la actualizacion si no cambia nada
        System.out.println();
        System.out.println("MOVIMIENTOS:");
        /*
        
            / / / for arriba
            / * / los dos if debajo de los for
            / / / for abajo
        
        */
        for (int i = 0; i < yTablero; i++) { // Verical
            for (int j = 0; j < xTablero; j++) { // Horizontal
                int vecinos = 0;
                String imprimir = tablero[i][j]+": "+i+" "+j+" vecinos: ";
                // Comprobar alrededor si estan vivas o muertas
                // Arriba
                for (int k = j-1; k < j+2; k++) {
                    if (i-1 >= 0 && // No choque con el "techo" (-1 vertical)
                        k >= 0 && // No choque con la "pared" (-1 horiontal)
                        k < xTablero && tablero[i-1][k] == "*") { // No choque con la otra "pared" (10 horiontal)
                        vecinos++;
                        imprimir+=tablero[i-1][k]+"("+(i-1)+" "+k+") ";
                    }
                }
                // Abajo
                for (int k = j-1; k < j+2; k++) {
                    if (i+1 < yTablero && // No choque con el "suelo" (10 vertical)
                        k >= 0 && // No choque con la "pared" (-1 horiontal)
                        k < xTablero && tablero[i+1][k] == "*") { // No choque con la otra "pared" (10 horiontal)
                        vecinos++;
                        imprimir+=tablero[i+1][k]+"("+(i+1)+" "+k+") ";
                    }
                }
                // Izquierda
                if (j > 0 && tablero[i][j-1] == "*" ) { // No salga por abajo de la pared
                    vecinos++;
                    imprimir+=tablero[i][j-1]+"izq ";
                }
                // Derecha
                if (j+1 < xTablero && tablero[i][j+1] == "*" ) { // No salga por abajo de la pared
                    vecinos++;
                    imprimir+=tablero[i][j+1]+"der ";
                }
                // Vivo a muerto
                if (tablero[i][j] == "*" && vecinos < 2 ||
                    tablero[i][j] == "*" && vecinos > 3) {
                    tableroProvisional[i][j] = " ";
                    imprimir += "V-M "+vecinos;
                    System.out.println(imprimir);
                    finBucle = true;
                }
                // Muerto a vivo
                if (tablero[i][j] == " " && vecinos == 3) {
                    tableroProvisional[i][j] = "*";
                    imprimir += "M-V "+vecinos;
                    System.out.println(imprimir);
                    finBucle = true;
                }
                // Si no cambia sigue igual
                if (tableroProvisional[i][j] == null)
                    tableroProvisional[i][j] = tablero[i][j];
            }
        }
        
        // Cuando acaba se le pasa el provisional al original
        tablero = tableroProvisional;
        
        if (!finBucle) {
            bucle = -2;
            System.out.println("YA NO HAY MAS CAMBIOS");
        }
    }
}
