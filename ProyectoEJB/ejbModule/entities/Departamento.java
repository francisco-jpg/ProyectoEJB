package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Departamento
 *
 */
@Entity
@Table(name="DEPARTAMENTO")
@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEPARTAMENTO_IDDEPARTAMENTO_GENERATOR", sequenceName="SQL_ID_DEPARTAMENTO",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTAMENTO_IDDEPARTAMENTO_GENERATOR")
	@Column(name="ID_DEPARTAMENTO", unique=true, nullable=false, precision=19)
	private long idDepartamento;

	@Column(nullable=false, length=50)
	private String nombre;

	//bi-directional many-to-one association to Zona
	@ManyToOne
	@JoinColumn(name="ID_ZONA", nullable=false)
	private Zona zona;

	//bi-directional many-to-one association to Localidad
	@OneToMany(mappedBy="departamento")
	private List<Localidad> localidads;

	public Departamento() {
	}

	public long getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Zona getZona() {
		return this.zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public List<Localidad> getLocalidads() {
		return this.localidads;
	}

	public void setLocalidads(List<Localidad> localidads) {
		this.localidads = localidads;
	}

	public Localidad addLocalidad(Localidad localidad) {
		getLocalidads().add(localidad);
		localidad.setDepartamento(this);

		return localidad;
	}

	public Localidad removeLocalidad(Localidad localidad) {
		getLocalidads().remove(localidad);
		localidad.setDepartamento(null);

		return localidad;
	}

}