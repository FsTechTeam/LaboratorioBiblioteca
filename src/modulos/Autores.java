/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;

import controlador.AutorJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import laboratoriobiblioteca.Autor;

/**
 *
 * @author Geek
 */
public class Autores implements Mostrar{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LaboratorioBibliotecaPU");
        EntityManager em = emf.createEntityManager();
        AutorJpaController controladorAutor = new AutorJpaController(emf);

    
    public List<Autor> obtenerDatos(String nombre) {
        List<Autor> autor;
        Query q;
        q = em.createNamedQuery("Autor.findByAutor");
        q.setParameter("autor", nombre+"%");
        autor= q.getResultList();
        return autor;
    }

    @Override
    public List<Object> obtenerDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
