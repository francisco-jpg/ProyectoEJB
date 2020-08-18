package servicios;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Fenomeno;
import exception.ServiciosException;

/**
 * Session Bean implementation class FenomenoBean
 */
@Stateless
@LocalBean
public class FenomenoBean implements FenomenoBeanRemote {
	@PersistenceContext 
	private EntityManager em;
	
	
    public FenomenoBean() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public void AltaFenomeno(String nombre, String descripcion, int tel) throws ServiciosException {
		Fenomeno fen = new Fenomeno();
		fen.setNombre(nombre);
		fen.setDescripcion(descripcion);
		fen.setTelefonoDeEmergencia(tel);
		em.persist(fen);
		em.flush();
	}


	@Override
	public void ModificarFenomeno(long ID, String nombre, String descripcion, int tel) throws ServiciosException {
		Fenomeno fen = em.find(Fenomeno.class, ID);
		em.detach(fen);
		fen.setNombre(nombre);
		fen.setDescripcion(descripcion);
		fen.setTelefonoDeEmergencia(tel);
		em.merge(fen);
		em.flush();
	}


	@Override
	public void BorrarFenomeno(long ID) throws ServiciosException {
		Fenomeno fen = em.find(Fenomeno.class, ID);
		em.remove(fen);
		em.flush();
		
	}


	@Override
	public List<Fenomeno> GetALL() throws ServiciosException {
		TypedQuery<Fenomeno> query = em.createQuery("SELECT f FROM Fenomeno f",Fenomeno.class);
		em.flush();
		return query.getResultList();
	}

}
