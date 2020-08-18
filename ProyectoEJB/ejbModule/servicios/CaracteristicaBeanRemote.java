package servicios;

import java.util.List;

import javax.ejb.Remote;

import entities.Caracteristica;
import exception.ServiciosException;

@Remote
public interface CaracteristicaBeanRemote {
	void AltaCaracteristica(long ID,String nombre,String etiqueta,String tipo,long id_fen) throws ServiciosException;
	void ModificarCaracteristica(long ID,String nombre,String etiqueta,String tipo) throws ServiciosException;
	void BorrarCaracteristica(long ID) throws ServiciosException;
	List<Caracteristica> GetALL() throws ServiciosException;

}
