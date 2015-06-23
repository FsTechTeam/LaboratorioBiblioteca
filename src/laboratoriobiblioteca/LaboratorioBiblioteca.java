/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriobiblioteca;

import controlador.LibroJpaController;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Geek
 */
public class LaboratorioBiblioteca {
    private static int date=20160101;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATestPU");
        EntityManager em = emf.createEntityManager();
        LibroJpaController controladorLibro = new LibroJpaController(emf);
        
        
        Libro nuevoLibro  = new Libro();
        Libro nuevoUsuario2 = new Libro();
        nuevoLibro.setId(2);
        nuevoLibro.setNombre("Lester");
        nuevoLibro.setIsbn("01234567890");
        Date d = new Date();
        
        
        
        
    }
}
