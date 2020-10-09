package br.com.gestapromotora.managebean.notificacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.dao.NotificacaoDao;
import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Notificacao;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class NotificacaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Notificacao> listaNotificacao;
	
	
	
	@PostConstruct
	public void init() {
		listarNotificacao();
	}
	
	
	
	
	
	
	
	public List<Notificacao> getListaNotificacao() {
		return listaNotificacao;
	}







	public void setListaNotificacao(List<Notificacao> listaNotificacao) {
		this.listaNotificacao = listaNotificacao;
	}







	public void listarNotificacao() {
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		listaNotificacao = notificacaoDao.lista("Select n From Notificacao n WHERE n.visto=false AND n.usuario.idusuario=" + 
					 usuarioLogadoMB.getUsuario().getIdusuario());
		if (listaNotificacao == null) {
			listaNotificacao = new ArrayList<Notificacao>();
		}
	}
	
	
	
	public void visto(Notificacao notificacao) {
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		notificacao.setVisto(true);
		notificacaoDao.salvar(notificacao);
		listaNotificacao.remove(notificacao);
		usuarioLogadoMB.listarNotificacao();
	}
	
	
	public String visualizarContrato(Notificacao notificacao) {
		if (notificacao.getIdcontrato() > 0) {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			ContratoFacade contratoFacade = new ContratoFacade();
			Contrato contrato = contratoFacade.consultar(notificacao.getIdcontrato());
			session.setAttribute("contrato", contrato);
			session.setAttribute("orgaobanco", contrato.getValorescoeficiente().getCoeficiente().getOrgaoBanco());
			return "visualizarContrato";
		}
		return "";
	}
	
	
	

}