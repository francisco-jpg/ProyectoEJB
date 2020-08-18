package servicios;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Departamento;
import entities.Localidad;
import exception.ServiciosException;

/**
 * Session Bean implementation class LocalidadBean
 */
@Stateless
@LocalBean
public class LocalidadBean implements LocalidadBeanRemote {
	@PersistenceContext 
	private EntityManager em;
	
	
    public LocalidadBean() {
        
    }

	@Override
	public void AltaLocalidad(long ID, String nombre, String geo, long id_dep) throws ServiciosException {
		Localidad loc = new Localidad();
		loc.setIdLoc(ID);
		loc.setNombre(nombre);
		loc.setGeolocalizacion(geo);
		loc.setDepartamento(em.find(Departamento.class, id_dep));
		em.persist(loc);
		em.flush();
		
	}

	@Override
	public void ModificarLocalidad(long ID, String nombre, String geo) throws ServiciosException {
		Localidad loc = em.find(Localidad.class, ID);
		em.detach(loc);
		loc.setNombre(nombre);
		loc.setGeolocalizacion(geo);
		em.merge(loc);
		em.flush();
		
	}

	@Override
	public void BorrarLocalidad(long ID) throws ServiciosException {
		Localidad loc = em.find(Localidad.class, ID);
		em.remove(loc);
		em.flush();
	}

	@Override
	public List<Localidad> GetALL() throws ServiciosException {
		TypedQuery<Localidad> query = em.createQuery("SELECT l FROM Localidad l",Localidad.class);
		em.flush();
		return query.getResultList();

	}

}
