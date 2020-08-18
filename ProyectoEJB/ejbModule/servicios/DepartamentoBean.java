package servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Departamento;
import entities.Zona;
import exception.ServiciosException;

/**
 * Session Bean implementation class DepartamentoBean
 */
@Stateless
@LocalBean
public class DepartamentoBean implements DepartamentoBeanRemote {

	@PersistenceContext 
	private EntityManager em;
    public DepartamentoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void AltaDepartamento(long ID, String nombre, long id_zona) throws ServiciosException {
		Departamento dep = new Departamento();
		dep.setIdDepartamento(ID);
		dep.setNombre(nombre);
		dep.setZona(em.find(Zona.class, id_zona));
		em.persist(dep);
		em.flush();
		
	}

	@Override
	public void Modificardepartamento(long ID, String nombre) throws ServiciosException {
		Departamento dep = em.find(Departamento.class, ID);
		em.detach(dep);
		dep.setNombre(nombre);
		em.merge(dep);
		em.flush();
	}

	@Override
	public void BorrarDepartamento(long ID) throws ServiciosException {
		Departamento dep = em.find(Departamento.class, ID);
		em.remove(dep);
		em.flush();
	}

	@Override
	public List<Departamento> GetALL() throws ServiciosException {
		TypedQuery<Departamento> query = em.createQuery("SELECT d FROM Departamento d",Departamento.class);
		em.flush();
		return query.getResultList();
	}

}
