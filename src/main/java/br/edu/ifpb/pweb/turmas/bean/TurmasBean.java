package br.edu.ifpb.pweb.turmas.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import br.edu.ifpb.pweb.turmas.dao.TurmaDAO;
import br.edu.ifpb.pweb.turmas.model.Turma;

@ManagedBean(name="turmasBean")
@RequestScoped
public class TurmasBean //FALTA A LETRA 'E' DA PÁGINA 3
{
	private Turma turma;
	private List<Turma> turmas;
	private Map<Long, Boolean> editavel;

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
	
	public String novo(){
		return "cadastro";
	}

	public String cadastrar(){
		TurmaDAO tdao = new TurmaDAO();
		tdao.beginTransaction();
		tdao.insert(turma);
		tdao.commit();
		System.out.println("Id da Turma: "+turma.getId().toString());
		this.editavel.put(turma.getId(), false);
		
		return "index";
	}
	
	public void salvar(Turma t)
	{
		System.out.println("Turma: "+t);
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
}
