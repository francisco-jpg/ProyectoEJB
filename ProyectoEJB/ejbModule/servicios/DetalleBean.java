package servicios;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Caracteristica;
import entities.Detalle;
import entities.Observacion;
import exception.ServiciosException;

/**
 * Session Bean implementation class DetalleBean
 */
@Stateless
@LocalBean
public class DetalleBean implements DetalleBeanRemote {
	@PersistenceContext 
	private EntityManager em;
    
	
	
    public DetalleBean() {
        
    }
	@Override
	public void AltaDetalle(long ID, String val_txt, int val_n, long id_obs, long id_cara) throws ServiciosException {
		Detalle det = new Detalle();
		Date date = new Date();
		det.setIdDetalle(ID);
		det.setValN(val_n);
		det.setValTxt(val_txt);
		det.setCaracteristica(em.find(Caracteristica.class, id_cara));
		det.setObservacion(em.find(Observacion.class, id_obs));
		det.setFecha(date);
		em.persist(det);
		em.flush();
		
	}
	@Override
	public void ModificarDetalle(long ID, String val_txt, int val_n) throws ServiciosException {
		Detalle det = em.find(Detalle.class, ID);
		em.detach(det);
		det.setValN(val_n);
		det.setValTxt(val_txt);
		em.merge(det);
		em.flush();
	}
	@Override
	public void BorrarDetalle(long ID) throws ServiciosException {
		Detalle det = em.find(Detalle.class, ID);
		em.remove(det);
	}
	@Override
	public List<Detalle> GetALL() throws ServiciosException {
		TypedQuery<Detalle> query = em.createQuery("SELECT d FROM Detalle d",Detalle.class);
		em.flush();
		return query.getResultList();
	}

}
