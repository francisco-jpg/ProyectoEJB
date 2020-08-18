package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Zona
 *
 */
@Entity
@Table(name="ZONA")
@NamedQuery(name="Zona.findAll", query="SELECT z FROM Zona z")
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ZONA_IDZONA_GENERATOR", sequenceName="SQL_ID_ZONA",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ZONA_IDZONA_GENERATOR")
	@Column(name="ID_ZONA", unique=true, nullable=false, precision=19)
	private long idZona;

	@Column(nullable=false, length=50)
	private String nombre;

	//bi-directional many-to-one association to Departamento
	@OneToMany(mappedBy="zona")
	private List<Departamento> departamentos;

	public Zona() {
	}

	public long getIdZona() {
		return this.idZona;
	}

	public void setIdZona(long idZona) {
		this.idZona = idZona;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Departamento> getDepartamentos() {
		return this.departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Departamento addDepartamento(Departamento departamento) {
		getDepartamentos().add(departamento);
		departamento.setZona(this);

		return departamento;
	}

	public Departamento removeDepartamento(Departamento departamento) {
		getDepartamentos().remove(departamento);
		departamento.setZona(null);

		return departamento;
	}

}
