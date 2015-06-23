/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;

import controlador.AutorJpaController;
import controlador.LibroJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import laboratoriobiblioteca.Autor;
import laboratoriobiblioteca.Libro;

/**
 *
 * @author Geek
 */
public class Libros implements Mostrar{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LaboratorioBibliotecaPU");
    EntityManager em = emf.createEntityManager();
    LibroJpaController controladorAutor = new LibroJpaController(emf);
    @Override
    public List<Object> obtenerDatos() {
        return null;
        
    }
    public List<Libro> obtenerDatos(String nombre) {
        List<Libro> libro;
        Query q;
        q = em.createNamedQuery("findByNombre");
        q.setParameter("nombre", nombre+"%");
        libro= q.getResultList();
        return libro;
    }
    
}
