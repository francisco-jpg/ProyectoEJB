package servicios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Observacion;
import entities.Usuario;
import exception.ServiciosException;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote {
	@PersistenceContext 
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public UsuarioBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void AltaUsuario(String nombre,String apellido,String contraseña,String email,int permiso,String usuarionom) throws ServiciosException {
		try {
			byte[] stlh =createSalt();
			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setSalt(stlh);
			try {
				usuario.setContraseña(generateHash(contraseña,stlh));
			} catch (NoSuchAlgorithmException e) {
				System.out.println("fallo en el hash");
			}
			usuario.setEmail(email);
			usuario.setPermiso(permiso);
			usuario.setUsuario(usuarionom);
			em.persist(usuario);
			em.flush();
			
		}catch(PersistenceException e) {
			throw new ServiciosException("No se pudo crear el usuario");
		}
		
	}

	@Override
	public void ModificarUsuario(long ID,String nombre, String apellido, String contraseña, String email, int permiso,String usuarioname) throws ServiciosException {
		Usuario usuario= em.find(Usuario.class, ID);
		System.out.println(usuario.getNombre());
		em.detach(usuario);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setContraseña(contraseña);
		usuario.setEmail(email);
		usuario.setPermiso(permiso);
		usuario.setUsuario(usuarioname);
		em.merge(usuario);
		em.flush();
	}
	
	@Override
	public void ModificarUsuario(long ID, int per) throws ServiciosException {
		Usuario usuario= em.find(Usuario.class, ID);
		em.detach(usuario);
		usuario.setPermiso(per);
		em.merge(usuario);
		em.flush();
		
	}
	
	@Override
	public void ModificarUsuario(long ID, String pass) throws ServiciosException {
		byte[] stlh =createSalt();
		Usuario usuario= em.find(Usuario.class, ID);
		em.detach(usuario);
		usuario.setSalt(stlh);
		try {
			usuario.setContraseña(generateHash(pass,stlh));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("fallo en el hash");
		}
		em.merge(usuario);
		em.flush();
		
	}

	@Override
	public void BorrarUsuario(long ID)throws ServiciosException {
		Usuario user = em.find(Usuario.class, ID);
		em.remove(user);
		em.flush();
		
	}

	@Override
	public List<Usuario> GetALL() throws ServiciosException {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u",Usuario.class);
		em.flush();
		return query.getResultList();
		
	}

	@Override
	public Usuario Login(String user, String pass) throws ServiciosException {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE U.usuario = ?1",Usuario.class);
		query.setParameter(1, user);
		Usuario usr = query.getSingleResult();
		byte[] stlh = usr.getSalt();
		try {
			String code = generateHash(pass,stlh);
			if(code.contentEquals(usr.getContraseña())) {
				return usr;
			}else {
				return usr = null;
			}
			
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	private byte[] createSalt() {
		byte[] bytes = new byte[20];
		SecureRandom rand = new SecureRandom();
		rand.nextBytes(bytes);
		return bytes;
	}

	private  String generateHash(String pass,byte[] salt) throws NoSuchAlgorithmException {
		String alg = "MD5";
		MessageDigest digest = MessageDigest.getInstance(alg);
		digest.reset();
		digest.update(salt);
		byte[] hash = digest.digest(pass.getBytes());
		return BToS(hash);
		
	}
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	
	public  String BToS(byte[] bytes) {
		char[] hexChars = new char[bytes.length*2];
		for (int i=0;i<bytes.length;i++) {
			int v = bytes[i] & 0xFF;
			hexChars [i*2]= hexArray[v>>> 4];
			hexChars[i*2+1] = hexArray[v & 0x0F];
		}
		return new  String(hexChars);
	}


	
	





}
