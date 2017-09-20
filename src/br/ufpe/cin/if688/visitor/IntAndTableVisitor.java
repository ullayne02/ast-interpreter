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
		if(s instanceof AssignStm) {
			return visit((AssignStm)s);
		}else if (s instanceof CompoundStm) {
			return visit((CompoundStm)s);
		}else {
			return visit((PrintStm)s);			
		}
	}

	@Override
	public IntAndTable visit(AssignStm s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(CompoundStm s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(PrintStm s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(Exp e) {
		if (e instanceof IdExp) {
			return visit((IdExp)e);
		}else if(e instanceof OpExp) {
			return visit((OpExp)e);
		}else if (e instanceof NumExp) {
			return visit((NumExp)e);
		}else {
			return visit((EseqExp)e);
		}
	}

	@Override
	public IntAndTable visit(EseqExp e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(IdExp e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(NumExp e) {
		IntAndTable retorno = new IntAndTable(e.getNum(),t); 
		return retorno;
	}

	@Override
	public IntAndTable visit(OpExp e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(ExpList el) {
		if(el instanceof PairExpList) {
			return visit((PairExpList)el);
		}else {
			return visit((LastExpList)el);
		}
	}

	@Override
	public IntAndTable visit(PairExpList el) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(LastExpList el) {
		// TODO Auto-generated method stub
		return null;
	}


}
