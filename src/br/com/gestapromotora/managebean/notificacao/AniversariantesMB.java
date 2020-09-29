package br.com.gestapromotora.managebean.notificacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gestapromotora.dao.ClienteDao;
import br.com.gestapromotora.model.Cliente;
import br.com.gestapromotora.util.Formatacao;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class AniversariantesMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Cliente> listaCliente;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaCliente();
	}



	public List<Cliente> getListaCliente() {
		return listaCliente;
	}



	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	
	
	
	public void gerarListaCliente() {
		ClienteDao clienteDao = new ClienteDao();
		String diames = "" + Formatacao.getDiaData(new Date()) +  (Formatacao.getMesData(new Date()) + 1);
		listaCliente = clienteDao.lista("Select c From Cliente c WHERE c.diames=" + diames 
				+ " AND c.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario());
		if (listaCliente == null) {
			listaCliente = new ArrayList<Cliente>();
		}
	}

}
