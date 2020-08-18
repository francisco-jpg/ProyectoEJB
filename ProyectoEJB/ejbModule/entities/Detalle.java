package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Detalle
 *
 */
@Entity
@Table(name="DETALLE")
@NamedQuery(name="Detalle.findAll", query="SELECT d FROM Detalle d")
public class Detalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DETALLE_IDDETALLE_GENERATOR", sequenceName="SQL_ID_DETALLE",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETALLE_IDDETALLE_GENERATOR")
	@Column(name="ID_DETALLE", unique=true, nullable=false, precision=19)
	private long idDetalle;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(name="VAL_N", nullable=false, precision=10)
	private int valN;

	@Column(name="VAL_TXT", nullable=false, length=25)
	private String valTxt;

	//bi-directional many-to-one association to Caracteristica
	@ManyToOne
	@JoinColumn(name="ID_CARACTERISTICAS", nullable=false)
	private Caracteristica caracteristica;

	//bi-directional many-to-one association to Observacion
	@ManyToOne
	@JoinColumn(name="ID_OBSERVACION", nullable=false)
	private Observacion observacion;

	public Detalle() {
	}

	public long getIdDetalle() {
		return this.idDetalle;
	}

	public void setIdDetalle(long idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getValN() {
		return this.valN;
	}

	public void setValN(int valN) {
		this.valN = valN;
	}

	public String getValTxt() {
		return this.valTxt;
	}

	public void setValTxt(String valTxt) {
		this.valTxt = valTxt;
	}

	public Caracteristica getCaracteristica() {
		return this.caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Observacion getObservacion() {
		return this.observacion;
	}

	public void setObservacion(Observacion observacion) {
		this.observacion = observacion;
	}

}