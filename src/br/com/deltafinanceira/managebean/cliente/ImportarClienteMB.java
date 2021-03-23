package br.com.deltafinanceira.managebean.cliente;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.deltafinanceira.bean.ClienteBean;
import br.com.deltafinanceira.facade.ClienteFacade;
import br.com.deltafinanceira.model.Cliente;

@Named
@ViewScoped
public class ImportarClienteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UploadedFile file;
	private List<Cliente> listaCliente;
	private List<ClienteBean> listaDados;
	
	
	@PostConstruct
	public void init() {
		
	}


	public UploadedFile getFile() {
		return file;
	}


	public void setFile(UploadedFile file) {
		this.file = file;
	}


	public List<Cliente> getListaCliente() {
		return listaCliente;
	}


	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}


	public List<ClienteBean> getListaDados() {
		return listaDados;
	}


	public void setListaDados(List<ClienteBean> listaDados) {
		this.listaDados = listaDados;
	}
	
	
	
	
	public String cancelar() {
		return "consCliente";
	}
	
	
	public String salvar() {
		ClienteFacade clienteFacade = new ClienteFacade();
		for (int i = 0; i < listaDados.size(); i++) {
			Cliente cliente = new Cliente();
			cliente = clienteFacade.salvar(cliente);
		}
		return "importarCliente";
	}
	
	
	
	
	

}
