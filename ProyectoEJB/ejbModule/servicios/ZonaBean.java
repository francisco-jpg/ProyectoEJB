package servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Zona;
import exception.ServiciosException;

/**
 * Session Bean implementation class ZonaBean
 */
@Stateless
@LocalBean
public class ZonaBean implements ZonaBeanRemote {
	@PersistenceContext 
	private EntityManager em;
    public ZonaBean() {
        
    }
	@Override
	public void AltaZona(long ID, String nombre) throws ServiciosException {
		Zona zona = new Zona();
		zona.setIdZona(ID);
		zona.setNombre(nombre);
		em.persist(zona);
		em.flush();
	}
	@Override
	public void ModificarZona(long ID, String nombre) throws ServiciosException {
		Zona zona = em.find(Zona.class, ID);
		em.detach(zona);
		zona.setNombre(nombre);
		em.merge(zona);
		em.flush();
	}
	@Override
	public void BorrarZona(long ID) throws ServiciosException {
		Zona zona = em.find(Zona.class, ID);
		em.remove(zona);
		em.flush();
		
	}
	@Override
	public List<Zona> GetALL() throws ServiciosException {
		TypedQuery<Zona> query = em.createQuery("SELECT z FROM Zona z",Zona.class);
		em.flush();
		return query.getResultList();
	}

}
