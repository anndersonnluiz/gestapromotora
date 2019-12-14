package br.com.gestapromotora.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Tipooperacao;

public class TipoOperacaoDao {

	public Tipooperacao salvar(Tipooperacao tipooperacao) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		tipooperacao = manager.merge(tipooperacao);
		tx.commit();

		return tipooperacao;
	}

	@SuppressWarnings("unchecked")
	public List<Tipooperacao> listar(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery(sql);
		List<Tipooperacao> lista = q.getResultList();

		return lista;
	}

	public void excluir(int idtipooperacao) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select t from Tipooperacao t where t.idtipooperacao=" + idtipooperacao);
		if (q.getResultList().size() > 0) {
			Tipooperacao tipooperacao = (Tipooperacao) q.getResultList().get(0);
			manager.remove(tipooperacao);
		}
		tx.commit();

	}
}