package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Revisa
 *
 */
@Entity
@Table(name="REVISA")
@NamedQuery(name="Revisa.findAll", query="SELECT r FROM Revisa r")
public class Revisa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REVISA_IDREVICION_GENERATOR", sequenceName="SQL_ID_REVISA",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVISA_IDREVICION_GENERATOR")
	@Column(name="ID_REVICION", unique=true, nullable=false, precision=19)
	private long idRevicion;

	@Column(nullable=false, length=250)
	private String comentrarios;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false, length=250)
	private String fiabilidad;

	//bi-directional many-to-one association to Observacion
	@ManyToOne
	@JoinColumn(name="ID_OBSERVACION", nullable=false)
	private Observacion observacion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_EXPERTO", nullable=false)
	private Usuario usuario;

	public Revisa() {
	}

	public long getIdRevicion() {
		return this.idRevicion;
	}

	public void setIdRevicion(long idRevicion) {
		this.idRevicion = idRevicion;
	}

	public String getComentrarios() {
		return this.comentrarios;
	}

	public void setComentrarios(String comentrarios) {
		this.comentrarios = comentrarios;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFiabilidad() {
		return this.fiabilidad;
	}

	public void setFiabilidad(String fiabilidad) {
		this.fiabilidad = fiabilidad;
	}

	public Observacion getObservacion() {
		return this.observacion;
	}

	public void setObservacion(Observacion observacion) {
		this.observacion = observacion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
