package servicios;

import java.util.List;

import javax.ejb.Remote;

import entities.Departamento;
import exception.ServiciosException;

@Remote
public interface DepartamentoBeanRemote {
	void AltaDepartamento(long ID,String nombre,long id_zona) throws ServiciosException;
	void Modificardepartamento(long ID,String nombre) throws ServiciosException;
	void BorrarDepartamento (long ID) throws ServiciosException;
	List<Departamento> GetALL() throws ServiciosException;
}