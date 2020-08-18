package servicios;

import java.util.List;

import javax.ejb.Remote;

import entities.Imagenes;
import exception.ServiciosException;

@Remote
public interface ImagenesBeanRemote {
	void AltaImagen(long ID,byte[] img,long id_obs,String formato) throws ServiciosException;
	void BorrarImagen (long ID) throws ServiciosException;
	List<Imagenes> GetALL() throws ServiciosException;
	List<Imagenes> GetALL(long id) throws ServiciosException;

}
