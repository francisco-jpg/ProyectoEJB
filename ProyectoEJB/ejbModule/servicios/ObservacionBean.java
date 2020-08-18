package servicios;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Fenomeno;
import entities.Imagenes;
import entities.Localidad;
import entities.Observacion;
import entities.Usuario;
import exception.ServiciosException;

/**
 * Session Bean implementation class ObservacionBean
 */
@Stateless
@LocalBean
public class ObservacionBean implements ObservacionBeanRemote {
	@PersistenceContext 
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ObservacionBean() {
        
    }

	@Override
	public void AltaObservacion(String desc, String geo, long id_fen, long id_loc,long id_user,byte[] imagene) throws ServiciosException {
		Observacion obs = new Observacion();
		Date date = new Date();
		obs.setDescripcion(desc);
		obs.setGeolocalizacion(geo);
		obs.setFenomeno(em.find(Fenomeno.class, id_fen));
		obs.setLocalidad(em.find(Localidad.class, id_loc));
		obs.setUsuario(em.find(Usuario.class, id_user));
		obs.setFecha(date);
		em.persist(obs);
		em.flush();
		
	}

	@Override
	public void ModificarObservacion(long id, String desc) throws ServiciosException {
		Observacion obs = em.find(Observacion.class, id);
		em.detach(obs);
		obs.setDescripcion(desc);
		em.merge(obs);
		em.flush();
	}

	@Override
	public void BorrarObservacion(long ID) throws ServiciosException {
		Observacion obs = em.find(Observacion.class, ID);
		em.remove(obs);
	}

	@Override
	public List<Observacion> GetALL() throws ServiciosException {
		TypedQuery<Observacion> query = em.createQuery("SELECT o FROM Observacion o",Observacion.class);
		em.flush();
		return query.getResultList();
	}

	@Override
	public List<Observacion> GetALL(int id_usuario) throws ServiciosException {
		TypedQuery<Observacion> query = em.createQuery("SELECT o FROM Observacion o where o.ID_USUARIO = :id",Observacion.class).setParameter("id",id_usuario);
		em.flush();
		return query.getResultList();
	}

	@Override
	public List<Observacion> ObsFilt(int id_fen) throws ServiciosException {
		TypedQuery<Observacion> query = em.createQuery("SELECT o FROM Observacion o where o.ID_FENOMENO = :id",Observacion.class).setParameter("id",id_fen);
		em.flush();
		return query.getResultList();
	}

}
