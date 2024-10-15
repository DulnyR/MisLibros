import com.coti.tools.Esdia;
import modelo.*;

public class App {

    public static final int NUEVO_ALMACEN = 1;
    public static final int RITMO_DE_LECTURA = 2;
    public static final int NUEVO_LIBRO = 3;
    public static final int INFORMACION = 4;
    public static final int SALIR = 5;
    public static final int LINEA_LONGITUD = 150;

    public static void main(String[] args) throws Exception {
        int seleccion = 0;
        Almacen miAlmacen = null;
        double ritmoDeLectura = 1;

        while (seleccion != 5) {
            seleccion = Esdia.readInt("\n|----------------------------------------------|\n"
                    + "| MIS LIBROS |\n"
                    + "|----------------------------------------------|\n"
                    + "1) Nuevo almacén de libros\n"
                    + "2) Configurar ritmo de lectura\n"
                    + "3) Añadir un nuevo libro al almacén\n"
                    + "4) Mostrar información actual de libros\n"
                    + "5) Salir (se borrará toda la información)\n"
                    + "|----------------------------------------------|\n\n"
                    + "Seleccione una opción (1-4): ", 1, 5);

            switch (seleccion) {
                case NUEVO_ALMACEN:
                    int numeroDeLibros = Esdia.readInt("\nTamano del almacen: ", 1, 20);
                    miAlmacen = new Almacen(numeroDeLibros);
                    System.out.println("\nAlmacen creado con exito.");
                    break;
                case RITMO_DE_LECTURA:
                    ritmoDeLectura = Esdia.readDouble("\nRitmo de lectura (paginas/minuto): ");
                    break;
                case NUEVO_LIBRO:
                    if(miAlmacen != null) {
                        if(miAlmacen.getLibrosAgregados() < miAlmacen.getLibros().length) {
                            String nombreAutor = Esdia.readString("\nNombre del Autor: ");
                            int numeroDeApellidos = Esdia.readInt("Numero de los apellidos del autor: ");
                            String[] apellidos = new String[numeroDeApellidos];
                            for(int i = 0; i < numeroDeApellidos; i++){
                                String apellido = Esdia.readString("Entra el apellido " + (i+1) + ": ");
                                apellidos[i] = apellido;
                            }
                            boolean premioPlaneta = Esdia.yesOrNo("Premio Planeta: ");
                            Autor nuevoAutor = new Autor(nombreAutor, apellidos, premioPlaneta);
                            String titulo = Esdia.readString("Titulo: ");
                            int anoDePublicacion = Esdia.readInt("Ano de publicacion: ");
                            int numeroDePaginas = Esdia.readInt("Numero de paginas: ");
                            double precio = Esdia.readDouble("Precio: ");
                            Libro nuevoLibro = new Libro(nuevoAutor, titulo, anoDePublicacion, numeroDePaginas, precio);
                            miAlmacen.agregaLibro(nuevoLibro);
                        }
                        else {
                            System.out.println("\nEl almacen esta lleno. Crea un almacen nuevo con tamano mas grande.");
                        }
                    }
                    else {
                        System.out.println("\nHay que crear un almacen.");
                    }
                    break;
                case INFORMACION:
                    if (miAlmacen != null) {
                        printInfo(miAlmacen, ritmoDeLectura);
                    } 
                    else {
                        System.out.println("\nHay que crear un almacen.");
                    }
                    break;
                case SALIR:
                    System.out.println("\nGracias por usar la aplicación.\n");
                    break;
                default:
                    break;
            }
        }
    }

    public static void printInfo(Almacen miAlmacen, double ritmoDeLectura)
    {
        int numeroLibrosAlmacen = miAlmacen.getLibrosAgregados();
            if (numeroLibrosAlmacen != 0) {
                linea();
                printConMargen("LIBROS EN EL ALMACEN");
                linea();
                printConMargen(new String[] { "Titulo", "Ano de Publicacion", "Autor", "Premio Planeta",
                        "Paginas", "Tiempo Lectura Minutos", "Precio" });
                linea();
                Libro[] libros = miAlmacen.getLibros();
                int tiempoTotal = 0;
                double precioTotal = 0;
                for (int i = 0; i < numeroLibrosAlmacen; i++) {
                    Libro libro = libros[i];
                    Autor miAutor = libro.getAutor();
                    String autorNombre = miAutor.getNombre();
                    for (String apellido : miAutor.getApellidos()) {
                        autorNombre += " " + apellido;
                    }
                    int paginas = libro.getNumeroDePaginas();
                    int tiempo = (int) (paginas / ritmoDeLectura);
                    double precio = libro.getPrecio();
                    tiempoTotal += tiempo;
                    precioTotal += precio;
                    printConMargen(new String[] { libro.getTitulo(),
                            Integer.toString(libro.getAnoDePublicacion()),
                            autorNombre, miAutor.getPremioPlaneta() ? "Sí" : "No",
                            Integer.toString(paginas), Integer.toString(tiempo), Double.toString(precio) + " E" });
                    linea();
                }
                printConMargen("Tiempo de lectura total del almacen: " + Integer.toString(tiempoTotal));
                printConMargen("Valor total del almacen: " + Double.toString(precioTotal));
                linea();
            } 
            else {
                System.out.println("\nHay que llenar el almacen.");
            }
    }

    public static void linea() {
        System.out.print("|");
        for (int i = 0; i < LINEA_LONGITUD; i++) {
            System.out.print("-");
        }
        System.out.println("|");
    }

    public static void printConMargen(String word) {
        System.out.print("| " + word);
        for (int i = 0; i < LINEA_LONGITUD - word.length() - 2; i++) {
            System.out.print(" ");
        }
        System.out.println(" |");
    }

    public static void printConMargen(String[] words) {
        int[] tamanos = { 30, 20, 30, 20, 10, 25, 15 };
        for (int i = 0; i < tamanos.length; i++) {
            if (words[i].length() < tamanos[i]) {
                System.out.print("| " + words[i]);
                for (int j = 0; j < tamanos[i] - words[i].length() - 2; j++) {
                    System.out.print(" ");
                }
            }
            else {
                System.out.print("| " + words[i].substring(0, tamanos[i] - 3) + " ");
            }
        }
        System.out.println(" |");
    }
}
