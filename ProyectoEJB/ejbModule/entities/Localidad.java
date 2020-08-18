package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Localidad
 *
 */
@Entity
@Table(name="LOCALIDAD")
@NamedQuery(name="Localidad.findAll", query="SELECT l FROM Localidad l")
public class Localidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCALIDAD_IDLOC_GENERATOR", sequenceName="SQL_ID_LOCALIDAD",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCALIDAD_IDLOC_GENERATOR")
	@Column(name="ID_LOC", unique=true, nullable=false, precision=19)
	private long idLoc;

	@Column(nullable=false, length=150)
	private String geolocalizacion;

	@Column(nullable=false, length=50)
	private String nombre;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="ID_DEPARTAMENTO", nullable=false)
	private Departamento departamento;

	//bi-directional many-to-one association to Observacion
	@OneToMany(mappedBy="localidad")
	private List<Observacion> observacions;

	public Localidad() {
	}

	public long getIdLoc() {
		return this.idLoc;
	}

	public void setIdLoc(long idLoc) {
		this.idLoc = idLoc;
	}

	public String getGeolocalizacion() {
		return this.geolocalizacion;
	}

	public void setGeolocalizacion(String geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Observacion> getObservacions() {
		return this.observacions;
	}

	public void setObservacions(List<Observacion> observacions) {
		this.observacions = observacions;
	}

	public Observacion addObservacion(Observacion observacion) {
		getObservacions().add(observacion);
		observacion.setLocalidad(this);

		return observacion;
	}

	public Observacion removeObservacion(Observacion observacion) {
		getObservacions().remove(observacion);
		observacion.setLocalidad(null);

		return observacion;
	}

}