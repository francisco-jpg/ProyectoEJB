package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Observacion
 *
 */
@Entity
@Table(name="OBSERVACION")
@NamedQuery(name="Observacion.findAll", query="SELECT o FROM Observacion o")
public class Observacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OBSERVACION_IDOBSERVACION_GENERATOR", sequenceName="SQL_ID_OBSERVACION",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OBSERVACION_IDOBSERVACION_GENERATOR")
	@Column(name="ID_OBSERVACION", unique=true, nullable=false, precision=19)
	private long idObservacion;

	@Column(nullable=false, length=250)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false, length=150)
	private String geolocalizacion;
	
	@Column(nullable=false, length=2)
	private int Estado;

	//bi-directional many-to-one association to Detalle
	@OneToMany(mappedBy="observacion")
	private List<Detalle> detalles;

	//bi-directional many-to-one association to Imagenes
	@OneToMany(mappedBy="observacion")
	private List<Imagenes> imagenes;

	//bi-directional many-to-one association to Fenomeno
	@ManyToOne
	@JoinColumn(name="ID_FENOMENO", nullable=false)
	private Fenomeno fenomeno;

	//bi-directional many-to-one association to Localidad
	@ManyToOne
	@JoinColumn(name="ID_LOCALIDAD", nullable=false)
	private Localidad localidad;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Revisa
	@OneToMany(mappedBy="observacion")
	private List<Revisa> revisas;

	public Observacion() {
	}

	public long getIdObservacion() {
		return this.idObservacion;
	}

	public void setIdObservacion(long idObservacion) {
		this.idObservacion = idObservacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getGeolocalizacion() {
		return this.geolocalizacion;
	}

	public void setGeolocalizacion(String geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}

	public List<Detalle> getDetalles() {
		return this.detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	public Detalle addDetalle(Detalle detalle) {
		getDetalles().add(detalle);
		detalle.setObservacion(this);

		return detalle;
	}

	public Detalle removeDetalle(Detalle detalle) {
		getDetalles().remove(detalle);
		detalle.setObservacion(null);

		return detalle;
	}

	public List<Imagenes> getImagenes() {
		return this.imagenes;
	}

	public void setImagenes(List<Imagenes> imagenes) {
		this.imagenes = imagenes;
	}

	public Imagenes addImagene(Imagenes imagene) {
		getImagenes().add(imagene);
		imagene.setObservacion(this);

		return imagene;
	}

	public Imagenes removeImagene(Imagenes imagene) {
		getImagenes().remove(imagene);
		imagene.setObservacion(null);

		return imagene;
	}

	public Fenomeno getFenomeno() {
		return this.fenomeno;
	}

	public void setFenomeno(Fenomeno fenomeno) {
		this.fenomeno = fenomeno;
	}

	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Revisa> getRevisas() {
		return this.revisas;
	}

	public void setRevisas(List<Revisa> revisas) {
		this.revisas = revisas;
	}

	public Revisa addRevisa(Revisa revisa) {
		getRevisas().add(revisa);
		revisa.setObservacion(this);

		return revisa;
	}

	public Revisa removeRevisa(Revisa revisa) {
		getRevisas().remove(revisa);
		revisa.setObservacion(null);

		return revisa;
	}
	
	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

}