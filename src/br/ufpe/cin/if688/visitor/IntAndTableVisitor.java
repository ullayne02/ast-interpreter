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

public class IntAndTableVisitor implements IVisitor<IntAndTable> {
	private Table t;

	public IntAndTableVisitor(Table t) {
		this.t = t;
	}

	@Override
	public IntAndTable visit(Stm s) {
		return null;
	}

	@Override
	public IntAndTable visit(AssignStm s) {
		return null;
	}

	@Override
	public IntAndTable visit(CompoundStm s) {
		return null;
	}

	@Override
	public IntAndTable visit(PrintStm s) {
		return null;
	}

	@Override
	public IntAndTable visit(Exp e) {
		IntAndTable aux = e.accept(this); 
		return aux;
	}

	@Override
	public IntAndTable visit(EseqExp e) {
		Interpreter inter = new Interpreter(this.t);
		Table aux = inter.visit(e.getStm());
		this.t = aux;
		IntAndTable tb = e.getExp().accept(this);
		return tb;
	}

	@Override
	public IntAndTable visit(IdExp e) {
		int value = findValue(e.getId(),this.t);
		IntAndTable aux = new IntAndTable(value,this.t);
		return aux;
	}

	@Override
	public IntAndTable visit(NumExp e) {
		IntAndTable aux = new IntAndTable(e.getNum(),this.t);
		return aux;
	}

	@Override
	public IntAndTable visit(OpExp e) {
		int left = e.getLeft().accept(this).result, result;
		IntAndTable right = e.getRight().accept(this);
		if(e.getOper()== 1) {
			result = left+right.result;
		} else if(e.getOper()== 2) {
			result = left-right.result;
		} else if(e.getOper()== 3) {
			result = left*right.result;
		} else {
			result = left/right.result;
		}
		IntAndTable aux = new IntAndTable(result,right.table);
		return aux;
	}

	@Override
	public IntAndTable visit(ExpList el) {
		return null;
	}

	@Override
	public IntAndTable visit(PairExpList el) {
		return null;
	}

	@Override
	public IntAndTable visit(LastExpList el) {
		return null;
	}
	
	/**  Metodo que retorna o valor da tabela que possui o identificador "id" */
	public Integer findValue(String id, Table tb) {
		Table aux = tb.tail; 
		while(aux!=null) {
			if (!aux.id.equals(id)) {
				aux = aux.tail; 
			}
		}
		if(tb.id==id) return tb.value; 
		return null;
	}
}
