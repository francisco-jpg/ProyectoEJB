package servicios;

import java.util.List;

import javax.ejb.Remote;

import entities.Detalle;
import exception.ServiciosException;

@Remote
public interface DetalleBeanRemote {
	void AltaDetalle(long ID,String val_txt,int val_n,long id_obs,long id_cara) throws ServiciosException;
	void ModificarDetalle(long ID,String val_txt,int val_n) throws ServiciosException;
	void BorrarDetalle(long ID) throws ServiciosException;
	List<Detalle> GetALL() throws ServiciosException;

}
