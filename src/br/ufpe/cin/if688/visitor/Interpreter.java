package br.ufpe.cin.if688.visitor;

import br.ufpe.cin.if688.ast.AssignStm;
import br.ufpe.cin.if688.ast.CompoundStm;
import br.ufpe.cin.if688.ast.EseqExp;
import br.ufpe.cin.if688.ast.Exp;
import br.ufpe.cin.if688.ast.ExpList;
import br.ufpe.cin.if688.ast.IdExp;
import br.ufpe.cin.if688.ast.LastExpList;
import br.ufpe.cin.if688.ast.NumExp;
import br.ufpe.cin.if688.ast.OpExp;
import br.ufpe.cin.if688.ast.PairExpList;
import br.ufpe.cin.if688.ast.PrintStm;
import br.ufpe.cin.if688.ast.Stm;
import br.ufpe.cin.if688.symboltable.IntAndTable;
import br.ufpe.cin.if688.symboltable.Table;

public class Interpreter implements IVisitor<Table> {
	
	//a=8;b=80;a=7;
	// a->7 ==> b->80 ==> a->8 ==> NIL
	private Table t;
	
	public Interpreter(Table t) {
		this.t = t;
	}

	@Override
	public Table visit(Stm s) {
		 s.accept(this);
		return this.t;
	}

	@Override
	public Table visit(AssignStm s) {
		Table t_aux = s.getExp().accept(this);
		t_aux.id = s.getId();
		this.t = t_aux;
		return this.t;
	}

	@Override
	public Table visit(CompoundStm s) {
		s.getStm1().accept(this);
		s.getStm2().accept(this);
		return this.t;
	}

	@Override
	public Table visit(PrintStm s) {
		s.getExps().accept(this);
		return this.t;
	}

	@Override
	public Table visit(Exp e) {
		IntAndTableVisitor aux = new IntAndTableVisitor(this.t);
		int resultado = aux.visit(e).result;
		Table retorno = new Table(" ",resultado,this.t);
		return retorno;
	}

	@Override
	public Table visit(EseqExp e) {
		IntAndTableVisitor aux = new IntAndTableVisitor(this.t);
		int resultado = aux.visit(e).result;
		Table retorno = new Table(" ",resultado,this.t);
		return retorno;
	}

	@Override
	public Table visit(IdExp e) {
		IntAndTableVisitor aux = new IntAndTableVisitor(this.t);
		int resultado = aux.visit(e).result;
		Table retorno = new Table(" ",resultado,this.t);
		return retorno;
	}

	@Override
	public Table visit(NumExp e) {
		IntAndTableVisitor aux = new IntAndTableVisitor(this.t);
		int resultado = aux.visit(e).result;
		Table retorno = new Table(" ",resultado,this.t);
		return retorno;
	}

	@Override
	public Table visit(OpExp e) {
		IntAndTableVisitor aux = new IntAndTableVisitor(this.t);
		int resultado = aux.visit(e).result;
		Table retorno = new Table(" ",resultado,this.t);
		return retorno;
	}

	@Override
	public Table visit(ExpList el) {
		el.accept(this);
		return this.t;
	}

	@Override
	public Table visit(PairExpList el) {		//chama o intandtable para o head para calcular o valor que esta para imprimir
		IntAndTableVisitor table_aux = new IntAndTableVisitor (this.t); 
		IntAndTable aux_t = table_aux.visit(el.getHead()); 
		System.out.print(aux_t.result + " ");
		return el.getTail().accept(this);		//chama o tail para fazer o mesmo metodo
	}

	@Override
	public Table visit(LastExpList el) {		//chama o intandtable para o head para calcular o valor que esta para imprimir
		IntAndTableVisitor table_aux = new IntAndTableVisitor (this.t); 
		IntAndTable aux_t = table_aux.visit(el.getHead()); 
		System.out.println(aux_t.result + " ");
		return this.t;
	}
}
