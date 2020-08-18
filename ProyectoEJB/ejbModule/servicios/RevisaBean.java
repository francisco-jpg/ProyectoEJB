package servicios;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.Date;
import entities.Observacion;
import entities.Revisa;
import entities.Usuario;
import exception.ServiciosException;


@Stateless
@LocalBean
public class RevisaBean implements RevisaBeanRemote {
	
	@PersistenceContext 
	private EntityManager em;

	
	public RevisaBean() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public void AltaRev(long ID_rev, String com, String fiab, Observacion obs, Usuario usr) throws ServiciosException {
		Revisa rev = new Revisa();
		Date date = new Date();
		rev.setFecha(date);
		rev.setIdRevicion(ID_rev);
		rev.setComentrarios(com);
		rev.setFiabilidad(fiab);
		rev.setObservacion(obs);
		rev.setUsuario(usr);
		/* Añado a la base la revicion */
		em.persist(rev);
		em.flush();
		
	}


	@Override
	public void ModRev(long ID_rev, String com, String fiab) throws ServiciosException {
		Revisa rev = em.find(Revisa.class, ID_rev);
		em.detach(rev);
		rev.setComentrarios(com);
		rev.setFiabilidad(fiab);
		em.merge(rev);
		em.flush();
	}


	@Override
	public void DelRev(long ID) throws ServiciosException {
		Revisa rev = em.find(Revisa.class, ID);
		em.remove(rev);
		em.flush();
		
	}


	@Override
	public List<Revisa> GetAll() throws ServiciosException {
		TypedQuery<Revisa> query = em.createQuery("SELECT r FROM Revisa r",Revisa.class);
		em.flush();
		return query.getResultList();
	}


	@Override
	public List<Revisa> GetAllMy(String ID) throws ServiciosException {
		TypedQuery<Revisa> query = em.createQuery("SELECT r FROM Revisa r WHERE r.usuario = :id",Revisa.class).setParameter("id",ID);
		em.flush();
		return query.getResultList();
	}

	

}
