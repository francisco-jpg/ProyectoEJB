package servicios;

import java.util.List;

import javax.ejb.Remote;

import entities.Usuario;
import exception.ServiciosException;

@Remote
public interface UsuarioBeanRemote {
	void AltaUsuario(String nombre,String apellido,String contraseña,String email,int permiso,String usuarioname) throws ServiciosException;
	void ModificarUsuario(long ID,String nombre,String apellido,String contraseña,String email,int permiso ,String usuarioname ) throws ServiciosException;
	void ModificarUsuario(long ID,int per) throws ServiciosException;
	void ModificarUsuario(long ID,String pass) throws ServiciosException;
	void BorrarUsuario (long ID) throws ServiciosException;
	List<Usuario> GetALL() throws ServiciosException;
	Usuario Login(String user,String pass) throws ServiciosException;	
}
