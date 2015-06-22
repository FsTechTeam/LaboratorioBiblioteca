/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import laboratoriobiblioteca.Editorial;
import laboratoriobiblioteca.Autor;
import laboratoriobiblioteca.Libro;

/**
 *
 * @author Geek
 */
public class LibroJpaController implements Serializable {

    public LibroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libro libro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Editorial editorialid = libro.getEditorialid();
            if (editorialid != null) {
                editorialid = em.getReference(editorialid.getClass(), editorialid.getId());
                libro.setEditorialid(editorialid);
            }
            Autor autorid = libro.getAutorid();
            if (autorid != null) {
                autorid = em.getReference(autorid.getClass(), autorid.getId());
                libro.setAutorid(autorid);
            }
            em.persist(libro);
            if (editorialid != null) {
                editorialid.getLibroCollection().add(libro);
                editorialid = em.merge(editorialid);
            }
            if (autorid != null) {
                autorid.getLibroCollection().add(libro);
                autorid = em.merge(autorid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libro libro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro persistentLibro = em.find(Libro.class, libro.getId());
            Editorial editorialidOld = persistentLibro.getEditorialid();
            Editorial editorialidNew = libro.getEditorialid();
            Autor autoridOld = persistentLibro.getAutorid();
            Autor autoridNew = libro.getAutorid();
            if (editorialidNew != null) {
                editorialidNew = em.getReference(editorialidNew.getClass(), editorialidNew.getId());
                libro.setEditorialid(editorialidNew);
            }
            if (autoridNew != null) {
                autoridNew = em.getReference(autoridNew.getClass(), autoridNew.getId());
                libro.setAutorid(autoridNew);
            }
            libro = em.merge(libro);
            if (editorialidOld != null && !editorialidOld.equals(editorialidNew)) {
                editorialidOld.getLibroCollection().remove(libro);
                editorialidOld = em.merge(editorialidOld);
            }
            if (editorialidNew != null && !editorialidNew.equals(editorialidOld)) {
                editorialidNew.getLibroCollection().add(libro);
                editorialidNew = em.merge(editorialidNew);
            }
            if (autoridOld != null && !autoridOld.equals(autoridNew)) {
                autoridOld.getLibroCollection().remove(libro);
                autoridOld = em.merge(autoridOld);
            }
            if (autoridNew != null && !autoridNew.equals(autoridOld)) {
                autoridNew.getLibroCollection().add(libro);
                autoridNew = em.merge(autoridNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = libro.getId();
                if (findLibro(id) == null) {
                    throw new NonexistentEntityException("The libro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro libro;
            try {
                libro = em.getReference(Libro.class, id);
                libro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libro with id " + id + " no longer exists.", enfe);
            }
            Editorial editorialid = libro.getEditorialid();
            if (editorialid != null) {
                editorialid.getLibroCollection().remove(libro);
                editorialid = em.merge(editorialid);
            }
            Autor autorid = libro.getAutorid();
            if (autorid != null) {
                autorid.getLibroCollection().remove(libro);
                autorid = em.merge(autorid);
            }
            em.remove(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libro> findLibroEntities() {
        return findLibroEntities(true, -1, -1);
    }

    public List<Libro> findLibroEntities(int maxResults, int firstResult) {
        return findLibroEntities(false, maxResults, firstResult);
    }

    private List<Libro> findLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Libro findLibro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    public int getLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libro> rt = cq.from(Libro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
