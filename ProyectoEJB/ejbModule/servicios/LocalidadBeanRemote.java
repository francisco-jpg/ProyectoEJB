package servicios;

import java.util.List;

import javax.ejb.Remote;

import entities.Localidad;
import exception.ServiciosException;


@Remote
public interface LocalidadBeanRemote {
	void AltaLocalidad(long ID,String nombre,String geo,long id_dep) throws ServiciosException;
	void ModificarLocalidad(long ID,String nombre,String geo) throws ServiciosException;
	void BorrarLocalidad (long ID) throws ServiciosException;
	List<Localidad> GetALL() throws ServiciosException;

}
