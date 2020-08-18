package servicios;



import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Caracteristica;
import entities.Fenomeno;
import exception.ServiciosException;

/**
 * Session Bean implementation class CaracteristicaBean
 */
@Stateless
@LocalBean
public class CaracteristicaBean implements CaracteristicaBeanRemote {
	@PersistenceContext 
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public CaracteristicaBean() {
        
    }

	@Override
	public void AltaCaracteristica(long ID, String nombre, String etiqueta, String tipo, long id_fen) throws ServiciosException {
		Caracteristica cara = new Caracteristica();
		cara.setIdCaracteristicas(ID);
		cara.setNombre(nombre);
		cara.setEtiqueta(etiqueta);
		cara.setTipo(tipo);
		cara.setFenomeno(em.find(Fenomeno.class, id_fen));
		em.persist(cara);
		em.flush();
	}

	@Override
	public void ModificarCaracteristica(long ID, String nombre, String etiqueta, String tipo) throws ServiciosException {
		Caracteristica cara = em.find(Caracteristica.class, ID);
		em.detach(cara);
		cara.setNombre(nombre);
		cara.setEtiqueta(etiqueta);
		cara.setTipo(tipo);
		em.merge(cara);
		em.flush();
	}

	@Override
	public void BorrarCaracteristica(long ID) throws ServiciosException {
		Caracteristica cara = em.find(Caracteristica.class, ID);
		em.remove(cara);
		em.flush();
	}

	@Override
	public List<Caracteristica> GetALL() throws ServiciosException {
		TypedQuery<Caracteristica> query = em.createQuery("SELECT c FROM Caracteristica c",Caracteristica.class);
		em.flush();
		return query.getResultList();
	}

}
