package modelo;

public class Almacen {
    private Libro[] libros;
    private int librosAgregados;

    public Almacen(int numeroDeLibros) {
        this.libros = new Libro[numeroDeLibros];
    }

    public void agregaLibro(Libro libro) {
        if (librosAgregados < libros.length) {
            libros[librosAgregados] = libro;
            librosAgregados++;
        } else {
            System.out.println("El almacen esta lleno");
        }
    }

    public Libro[] getLibros() {
        return libros;
    }

    public boolean esLleno() {
        return (librosAgregados == libros.length - 1);
    }

    public int getLibrosAgregados() {
        return librosAgregados;
    }
}
