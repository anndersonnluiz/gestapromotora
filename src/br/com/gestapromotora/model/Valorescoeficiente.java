package br.com.gestapromotora.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "valorescoeficiente")
public class Valorescoeficiente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idvalorescoeficiente")
	private Integer idvalorescoeficiente;
	@Column(name = "prazo")
	private int prazo;
	@Column(name = "dataini")
	@Temporal(TemporalType.DATE)
	private Date dataini;
	@Column(name = "datafin")
	@Temporal(TemporalType.DATE)
	private Date datafin;
	@Column(name = "coeficientevalor")
	private float coeficientevalor;
	@Column(name = "outrosrecebimentos")
	private float outrosrecebimentos;
	@Column(name = "obsrecebimento")
	private String obsrecebimento;
	@JoinColumn(name = "coeficiente_idcoeficiente", referencedColumnName = "idcoeficiente")
	@ManyToOne(optional = false)
	private Coeficiente coeficiente;
	
	
	public Valorescoeficiente() {
	
	}


	public Integer getIdvalorescoeficiente() {
		return idvalorescoeficiente;
	}


	public void setIdvalorescoeficiente(Integer idvalorescoeficiente) {
		this.idvalorescoeficiente = idvalorescoeficiente;
	}


	public int getPrazo() {
		return prazo;
	}


	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}


	public Date getDataini() {
		return dataini;
	}


	public void setDataini(Date dataini) {
		this.dataini = dataini;
	}


	public Date getDatafin() {
		return datafin;
	}


	public void setDatafin(Date datafin) {
		this.datafin = datafin;
	}


	public float getCoeficientevalor() {
		return coeficientevalor;
	}


	public void setCoeficientevalor(float coeficientevalor) {
		this.coeficientevalor = coeficientevalor;
	}


	public float getOutrosrecebimentos() {
		return outrosrecebimentos;
	}


	public void setOutrosrecebimentos(float outrosrecebimentos) {
		this.outrosrecebimentos = outrosrecebimentos;
	}


	public String getObsrecebimento() {
		return obsrecebimento;
	}


	public void setObsrecebimento(String obsrecebimento) {
		this.obsrecebimento = obsrecebimento;
	}


	public Coeficiente getCoeficiente() {
		return coeficiente;
	}


	public void setCoeficiente(Coeficiente coeficiente) {
		this.coeficiente = coeficiente;
	}
	
	
	
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idvalorescoeficiente != null ? idvalorescoeficiente.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Banco)) {
			return false;
		}
		Valorescoeficiente other = (Valorescoeficiente) object;
		if ((this.idvalorescoeficiente == null && other.idvalorescoeficiente != null)
				|| (this.idvalorescoeficiente != null && !this.idvalorescoeficiente.equals(other.idvalorescoeficiente))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Valorescoeficiente[ idvalorescoeficiente=" + idvalorescoeficiente + " ]";
	}
	
	
	

}