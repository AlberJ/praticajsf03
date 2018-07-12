package br.edu.ifpb.pweb.turmas.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.edu.ifpb.pweb.turmas.dao.TurmaDAO;
import br.edu.ifpb.pweb.turmas.model.Turma;

@ManagedBean(name="turmasBean")
@SessionScoped
public class TurmasBean 
{
	private Turma turma;
	private String nome;
	private List<Turma> turmas;
	private Map<Long, Boolean> editavel;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<Long, Boolean> getEditavel() {
		return editavel;
	}

	public void setEditavel(Map<Long, Boolean> editavel) {
		this.editavel = editavel;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public void listar(ActionEvent e)
	{
		TurmaDAO dao = new TurmaDAO();
		setTurmas(dao.findAll());
		this.editavel = new HashMap<Long, Boolean>(this.turmas.size());
		for(Turma t : this.turmas){
			this.editavel.put(t.getId(), false);
		}
	}

	public String cadastrar()
	{
		Turma nt = new Turma();
		nt.setNome(nome);
		
		Date d = new Date();
		nt.setDataCriacao(d);
		
		TurmaDAO tdao = new TurmaDAO();
		tdao.beginTransaction();
		tdao.insert(nt);
		tdao.commit();
		
		return "index?faces-redirect=true";
	}
	
	public void salvar(Turma t)
	{
		TurmaDAO tdao = new TurmaDAO();
		tdao.beginTransaction();
		tdao.update(t);
		tdao.commit();
		this.editavel.put(t.getId(), false);
	}
	
	public void excluir(Turma t)
	{
		TurmaDAO tdao = new TurmaDAO();
		tdao.beginTransaction();
		tdao.delete(t);
		tdao.commit();
		this.editavel.remove(t.getId());
	}
	
	public String cadastro(){
		return "cadastro?faces-redirect=true";
	}
	
	public String voltar(){
		return "index?faces-redirect=true";
	}
}
