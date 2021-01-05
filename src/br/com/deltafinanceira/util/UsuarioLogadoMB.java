package br.com.deltafinanceira.util;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.dao.ClienteDao;
import br.com.deltafinanceira.facade.NotificacaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.model.Filial;
import br.com.deltafinanceira.model.Notificacao;
import br.com.deltafinanceira.model.Usuario;
 

@Named
@SessionScoped
public class UsuarioLogadoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private String novaSenha;
	private String senhaAtual;
	private String confirmaNovaSenha;
	private String mensagemOla;
	private String senha;
	private String login; 
	private List<String> imagens;
	private boolean logar;
	private String nomeUsuario;
	private int nNotificacao;
	private int nAniversario;
	private Filial filial;
	
	
	@PostConstruct
	public void init() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmaNovaSenha() {
		return confirmaNovaSenha;
	}

	public void setConfirmaNovaSenha(String confirmaNovaSenha) {
		this.confirmaNovaSenha = confirmaNovaSenha;
	}

	
	public String getMensagemOla() {
		return mensagemOla;
	}

	public void setMensagemOla(String mensagemOla) {
		this.mensagemOla = mensagemOla;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	} 
	

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	} 

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isLogar() {
		return logar;
	}

	public void setLogar(boolean logar) {
		this.logar = logar;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public int getnNotificacao() {
		return nNotificacao;
	}

	public void setnNotificacao(int nNotificacao) {
		this.nNotificacao = nNotificacao;
	}

	public int getnAniversario() {
		return nAniversario;
	}

	public void setnAniversario(int nAniversario) {
		this.nAniversario = nAniversario;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public String logar() {
		if (logar) {  
			return "dashboard";
		} else
			return "";
	}

	public boolean validarUsuario() throws SQLException { 
		usuario = new Usuario();  
		if ((login == null) && (senha == null)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro", "Acesso Negado."));
		} else {
			String senha = "";
		//	try {
			//	senha = Criptografia.encript(this.senha);
				this.senha = "";
		//	} catch (NoSuchAlgorithmException ex) {
			//	Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
			//} 
			UsuarioFacade usuarioFacade = new UsuarioFacade();
			usuario = usuarioFacade.consultar(login, senha);
			if (usuario == null) {
				Mensagem.lancarMensagemInfo("Aten��o", "Acesso negado");
			} else {
					mensagemOl�();
					nomeUsuario = usuario.getNome();
					//listarNotificacao();
					gerarListaCliente();
					return logar = true;
			}
		}
		usuario = new Usuario();
		return logar = false;
	}

	public void erroLogin(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(mensagem, ""));
	}

	public void validarTrocarSenha() {
		if (usuario == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
		} else {
			Map<String, Object> options = new HashMap<String, Object>();
			options.put("contentWidth", 280);
			options.put("closable", false);
			RequestContext.getCurrentInstance().openDialog("cadNovaSenha", options, null);
		}
	}

	public String confirmaNovaSenha() {
		String repetirSenhaAtual = "";
		try {
			repetirSenhaAtual = Criptografia.encript(senhaAtual);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (repetirSenhaAtual.equalsIgnoreCase(usuario.getSenha())) {
			if ((novaSenha.length() > 0) && (confirmaNovaSenha.length() > 0)) {
				if (novaSenha.equalsIgnoreCase(confirmaNovaSenha)) {
					String senha = "";
					try {
						senha = Criptografia.encript(novaSenha);
					} catch (NoSuchAlgorithmException ex) {
						Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
					}
					usuario.setSenha(senha);
					UsuarioFacade usuarioFacade = new UsuarioFacade();
					usuario = usuarioFacade.salvar(usuario);
					senhaAtual = "";
					novaSenha = "";
					confirmaNovaSenha = "";
					return "dashboard";
				} else {
					novaSenha = "";
					confirmaNovaSenha = "";
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
			}
		} else {
			Mensagem.lancarMensagemInfo("", "Altera��o Negada");
			senhaAtual = "";
			novaSenha = "";
			confirmaNovaSenha = "";
		}
		return "";
	}

	public String cancelarTrocaSenha() {
		novaSenha = "";
		confirmaNovaSenha = "";
		RequestContext.getCurrentInstance().closeDialog(null);
		return "";
	}

	

	public String deslogar() {
		Map<?, ?> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.clear();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		ConectionFactory.getInstanceClose();
		return "index";
	}

	public void mensagemOl�() throws SQLException {
		mensagemOla = "Ol� " + usuario.getNome();
		gerarListaImagens();
	}

		
	public String importar() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("contentWidth", 400);
		RequestContext.getCurrentInstance().openDialog("importarOrcamento", options, null);
		// Limpar limpar = new Limpar();
		/// limpar.limparAcomodacao();
		return "";
	}

	

	public void retornoDialogAlteracaoSenha(SelectEvent event) {
		Usuario usuario = (Usuario) event.getObject();
		if (usuario != null) {
			Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
		}
	}

	public void gerarListaImagens() {
		imagens = new ArrayList<String>();
		imagens.add("Irlanda");
		imagens.add("UK");
		imagens.add("Estados Unidos");
		imagens.add("Australia");
		imagens.add("Canada");
	}

	public String pdfAustralia() {
		return "resources/img/TopTMStars/AUSTRALIA_PDF";
	}

//	public String getFotoUsuarioLogado(Usuario usuario) {
//		String caminho = "https://local.systm.com.br/usuario/";
//		if(usuario!=null && usuario.getIdusuario()!=null){
//			if (usuario.isFoto()) {
//				caminho = caminho + usuario.getIdusuario() + ".jpg";
//			} else
//				caminho = caminho + "/0.png";
//		}else{
//			Mensagem.lancarMensagemErro("Aten��o!", "Nenhum usu�rio encontrado, favor logar novamente.");  
//			caminho = caminho + "/0.png";
//		}
//		return caminho;
//	}

	public boolean mostrarCRM(int idusuario){
		if(idusuario==125){
			return true;
		}else return false;
	}
	
	public void salvarUsuario() {
			UsuarioFacade usuarioFacade = new UsuarioFacade();
			usuario = usuarioFacade.salvar(usuario);
			RequestContext.getCurrentInstance().closeDialog("inicial.jsf");  
	}
	
	public void retornoDialogData(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("inicial.jsf");
		} catch (IOException e) {
			  
		} 
	}
	
	
	public void listarNotificacao() {
		NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
		List<Notificacao> listaNotificacao = notificacaoFacade.lista("Select n From Notificacao n WHERE n.visto=false AND n.usuario.idusuario=" + 
					 usuario.getIdusuario());
		if (listaNotificacao == null) {
			listaNotificacao = new ArrayList<Notificacao>();
		}
		nNotificacao = listaNotificacao.size();
	}
	
	public void gerarListaCliente() {
		ClienteDao clienteDao = new ClienteDao();
		int dia = Formatacao.getDiaData(new Date());
		int mes = Formatacao.getMesData(new Date()) + 1;
		List<Cliente> listaCliente = clienteDao.lista("Select c From Cliente c WHERE c.diames=" + dia + mes
				+ " AND c.idusuario=" + usuario.getIdusuario());
		if (listaCliente == null) {
			listaCliente = new ArrayList<Cliente>();
		}
		nAniversario = listaCliente.size();
	}
	
}