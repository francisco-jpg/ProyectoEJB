package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Fenomeno
 *
 */
@Entity
@Table(name="FENOMENO")
@NamedQuery(name="Fenomeno.findAll", query="SELECT f FROM Fenomeno f")
public class Fenomeno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FENOMENO_IDFENOMENO_GENERATOR", sequenceName="SQL_ID_FENOMENO",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FENOMENO_IDFENOMENO_GENERATOR")
	@Column(name="ID_FENOMENO", unique=true, nullable=false, precision=19)
	private long idFenomeno;

	@Column(nullable=false, length=250)
	private String descripcion;

	@Column(nullable=false, length=20)
	private String nombre;

	@Column(name="TELEFONO_DE_EMERGENCIA", nullable=false, precision=10)
	private int telefonoDeEmergencia;

	//bi-directional many-to-one association to Caracteristica
	@OneToMany(mappedBy="fenomeno")
	private List<Caracteristica> caracteristicas;

	//bi-directional many-to-one association to Observacion
	@OneToMany(mappedBy="fenomeno")
	private List<Observacion> observacions;

	public Fenomeno() {
	}

	public long getIdFenomeno() {
		return this.idFenomeno;
	}

	public void setIdFenomeno(long idFenomeno) {
		this.idFenomeno = idFenomeno;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefonoDeEmergencia() {
		return this.telefonoDeEmergencia;
	}

	public void setTelefonoDeEmergencia(int telefonoDeEmergencia) {
		this.telefonoDeEmergencia = telefonoDeEmergencia;
	}

	public List<Caracteristica> getCaracteristicas() {
		return this.caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Caracteristica addCaracteristica(Caracteristica caracteristica) {
		getCaracteristicas().add(caracteristica);
		caracteristica.setIdCaracteristicas(idFenomeno);
		return caracteristica;
	}

	public Caracteristica removeCaracteristica(Caracteristica caracteristica) {
		getCaracteristicas().remove(caracteristica);
		caracteristica.setIdCaracteristicas(idFenomeno);

		return caracteristica;
	}

	public List<Observacion> getObservacions() {
		return this.observacions;
	}

	public void setObservacions(List<Observacion> observacions) {
		this.observacions = observacions;
	}

	public Observacion addObservacion(Observacion observacion) {
		getObservacions().add(observacion);
		observacion.setFenomeno(this);

		return observacion;
	}

	public Observacion removeObservacion(Observacion observacion) {
		getObservacions().remove(observacion);
		observacion.setFenomeno(null);

		return observacion;
	}

}