package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="CARACTERISTICAS")
@NamedQuery(name="Caracteristica.findAll", query="SELECT c FROM Caracteristica c")
public class Caracteristica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CARACTERISTICAS_IDCARACTERISTICAS_GENERATOR", sequenceName="SQL_ID_CARACTERISTICAS",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CARACTERISTICAS_IDCARACTERISTICAS_GENERATOR")
	@Column(name="ID_CARACTERISTICAS", unique=true, nullable=false, precision=19)
	private long idCaracteristicas;

	@Column(nullable=false, length=25)
	private String etiqueta;

	@Column(nullable=false, length=25)
	private String nombre;

	@Column(nullable=false, length=25)
	private String tipo;

	//bi-directional many-to-one association to Fenomeno
	@ManyToOne
	@JoinColumn(name="ID_FENOMENO", nullable=false)
	private Fenomeno fenomeno;

	//bi-directional many-to-one association to Detalle
	@OneToMany(mappedBy="caracteristica")
	private List<Detalle> detalles;

	public Caracteristica() {
	}

	public long getIdCaracteristicas() {
		return this.idCaracteristicas;
	}

	public void setIdCaracteristicas(long idCaracteristicas) {
		this.idCaracteristicas = idCaracteristicas;
	}

	public String getEtiqueta() {
		return this.etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre2) {
		// TODO Auto-generated method stub
		
	}

	public void setFenomeno(Fenomeno find) {
		// TODO Auto-generated method stub
		
	}

	public void setTipo(String tipo2) {
		// TODO Auto-generated method stub
		
	}
}