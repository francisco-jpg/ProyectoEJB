package servicios;

import java.util.List;

import javax.ejb.Remote;

import entities.Zona;
import exception.ServiciosException;

@Remote
public interface ZonaBeanRemote {
	void AltaZona(long ID,String nombre) throws ServiciosException;
	void ModificarZona(long ID,String nombre) throws ServiciosException;
	void BorrarZona (long ID) throws ServiciosException;
	List<Zona> GetALL() throws ServiciosException;

}
