package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Imagenes
 *
 */
@Entity
@Table(name="IMAGENES")
@NamedQuery(name="Imagenes.findAll", query="SELECT i FROM Imagenes i")
public class Imagenes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="IMAGENES_IDIMAGEN_GENERATOR", sequenceName="SQL_ID_IMAGENES",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IMAGENES_IDIMAGEN_GENERATOR")
	@Column(name="ID_IMAGEN", unique=true, nullable=false, precision=19)
	private long idImagen;

	@Lob
	private byte[] imagen;
	
	@Column(nullable=false, length=50)
	private String formato;

	//bi-directional many-to-one association to Observacion
	@ManyToOne
	@JoinColumn(name="ID_OBSERVACION", nullable=true)
	private Observacion observacion;

	public Imagenes() {
	}

	public long getIdImagen() {
		return this.idImagen;
	}

	public void setIdImagen(long idImagen) {
		this.idImagen = idImagen;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Observacion getObservacion() {
		return this.observacion;
	}

	public void setObservacion(Observacion observacion) {
		this.observacion = observacion;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

}