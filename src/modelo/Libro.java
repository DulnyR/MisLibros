package modelo;

public class Libro {
    private Autor autor;
    private String titulo;
    private int anoDePublicacion;
    private int numeroDePaginas;
    private double precio;

    public Libro(Autor autor, String titulo, int anoDePublicacion, int numeroDePaginas, double precio) {
        this.autor = autor;
        this.titulo = titulo;
        this.anoDePublicacion = anoDePublicacion;
        this.numeroDePaginas = numeroDePaginas;
        this.precio = precio;
    }

    public int getAnoDePublicacion() {
        return anoDePublicacion;
    }

    public Autor getAutor() {
        return autor;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public double getPrecio() {
        return precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAnoDePublicacion(int anoDePublicacion) {
        this.anoDePublicacion = anoDePublicacion;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
