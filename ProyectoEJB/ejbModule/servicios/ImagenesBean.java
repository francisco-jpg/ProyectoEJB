package servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Imagenes;
import entities.Observacion;
import exception.ServiciosException;


@Stateless
@LocalBean
public class ImagenesBean implements ImagenesBeanRemote {
	@PersistenceContext 
	private EntityManager em;


    public ImagenesBean() {
        
    }


	@Override
	public void AltaImagen(long ID, byte[] img, long id_obs,String formato) throws ServiciosException {
		Imagenes image = new Imagenes();
		image.setImagen(img);
		image.setObservacion(em.find(Observacion.class, id_obs));
		image.setFormato(formato);
		em.persist(image);
		em.flush();
		
	}


	@Override
	public void BorrarImagen(long ID) throws ServiciosException {
		Imagenes img = em.find(Imagenes.class, ID);
		em.remove(img);
	}


	@Override
	public List<Imagenes> GetALL() throws ServiciosException {
		TypedQuery<Imagenes> query = em.createQuery("SELECT i FROM Imagene i",Imagenes.class);
		em.flush();
		return query.getResultList();
	}


	@Override
	public List<Imagenes> GetALL(long id) throws ServiciosException {
		TypedQuery<Imagenes> query = em.createQuery("SELECT i FROM Imagene i where i.ID_OBSERVACION = :id",Imagenes.class).setParameter("id", id);
		em.flush();
		return query.getResultList();
	}

}
