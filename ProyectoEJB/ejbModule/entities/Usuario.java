package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@Table(name="USUARIO")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_IDUSUARIO_GENERATOR", sequenceName="SQL_ID_USUARIO",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_IDUSUARIO_GENERATOR")
	@Column(name="ID_USUARIO", unique=true, nullable=false, precision=19)
	private long idUsuario;

	@Column(nullable=false, length=50)
	private String apellido;

	@Column(nullable=false, length=55)
	private String contraseña;

	@Column(unique=true, nullable=false, length=50)
	private String email;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(nullable=false, precision=10)
	private int permiso;

	@Column(nullable=false)
	private byte[] salt;

	@Column(unique=true, length=20)
	private String usuario;

	//bi-directional many-to-one association to Observacion
	@OneToMany(mappedBy="usuario")
	private List<Observacion> observacions;

	//bi-directional many-to-one association to Revisa
	@OneToMany(mappedBy="usuario")
	private List<Revisa> revisas;

	public Usuario() {
	}

	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPermiso() {
		return this.permiso;
	}

	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}

	public byte[] getSalt() {
		return this.salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Observacion> getObservacions() {
		return this.observacions;
	}

	public void setObservacions(List<Observacion> observacions) {
		this.observacions = observacions;
	}

	public Observacion addObservacion(Observacion observacion) {
		getObservacions().add(observacion);
		observacion.setUsuario(this);

		return observacion;
	}

	public Observacion removeObservacion(Observacion observacion) {
		getObservacions().remove(observacion);
		observacion.setUsuario(null);

		return observacion;
	}

	public List<Revisa> getRevisas() {
		return this.revisas;
	}

	public void setRevisas(List<Revisa> revisas) {
		this.revisas = revisas;
	}

	public Revisa addRevisa(Revisa revisa) {
		getRevisas().add(revisa);
		revisa.setUsuario(this);

		return revisa;
	}

	public Revisa removeRevisa(Revisa revisa) {
		getRevisas().remove(revisa);
		revisa.setUsuario(null);

		return revisa;
	}

}