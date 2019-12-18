package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.AjudaDao;
import br.com.gestapromotora.model.Ajuda;

public class AjudaFacade {

	AjudaDao ajudaDao;
	
	public Ajuda salvar(Ajuda ajuda) {
		ajudaDao = new AjudaDao();
		return ajudaDao.salvar(ajuda);
	}

	public Ajuda consultar(int idajuda) {
		ajudaDao = new AjudaDao();
		return ajudaDao.consultar(idajuda);
	}

	public List<Ajuda> lista(String sql) {
		ajudaDao = new AjudaDao();
		return ajudaDao.lista(sql);
	}
}
