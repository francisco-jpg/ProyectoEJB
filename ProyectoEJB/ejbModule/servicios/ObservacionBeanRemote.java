package servicios;

import java.util.List;

import javax.ejb.Remote;

import entities.Observacion;
import exception.ServiciosException;

@Remote
public interface ObservacionBeanRemote {
	void AltaObservacion(String desc,String geo,long id_fen,long id_loc,long id_user,byte[] imagene) throws ServiciosException;
	void ModificarObservacion(long id,String desc) throws ServiciosException;
	void BorrarObservacion (long ID) throws ServiciosException;
	List<Observacion> GetALL() throws ServiciosException;
	List<Observacion> GetALL(int id_usuario) throws ServiciosException;
	List<Observacion> ObsFilt(int id_fen) throws ServiciosException;
}
