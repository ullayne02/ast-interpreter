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

public class MaxArgsVisitor implements IVisitor<Integer> {

	@Override
	public Integer visit(Stm s) {
		if(s instanceof AssignStm) {
			return visit((AssignStm)s);
		}else if (s instanceof CompoundStm) {
			return visit((CompoundStm)s);
		}else {
			return visit((PrintStm)s);			
		}
	}

	@Override
	public Integer visit(AssignStm s) {
		// TODO Auto-generated method stub
		Exp aux = s.getExp(); 
		if (aux instanceof EseqExp) {
			Stm aux1 = ((EseqExp) s.getExp()).getStm();
			return aux1.accept(this);
			
		}
		return 0;
	}

	@Override
	public Integer visit(CompoundStm s) {
		// TODO Auto-generated method stub
		int a, b; 
		a = b = 0; 
		
		Stm stm1 = s.getStm1();
		Stm stm2 = s.getStm2();
		a = stm1.accept(this); 
		b = stm2.accept(this); 
		
		int m = Math.max(a,b);
		
		return m;
		
	}

	@Override
	public Integer visit(PrintStm s) {
		int retorno = 0;
		ExpList aux = s.getExps(); 
		
		if(aux instanceof LastExpList) {
			return aux.accept(this);
		}else if (aux instanceof PairExpList){
			int a = ((PairExpList) aux).getHead().accept(this);
			int b = ((PairExpList) aux).getTail().accept(this);
			int m = Math.max(a, b);
			return m;
		}
		return null;
	}

	@Override
	public Integer visit(Exp e) {
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
	public Integer visit(EseqExp e) {
		Stm stm = e.getStm();
		Exp exp = e.getExp();
		return stm.accept(this) + exp.accept(this);
	}

	@Override
	public Integer visit(IdExp e) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Integer visit(NumExp e) {
		return 1;
	}

	@Override
	public Integer visit(OpExp e) {
		Exp left,right; 
		left = e.getLeft();
		right = e.getRight(); 
		return e.getLeft().accept(this) + e.getRight().accept(this);
	}

	@Override
	public Integer visit(ExpList el) {
		// TODO Auto-generated method stub
		if(el instanceof PairExpList) {
			return visit((PairExpList)el);
		}else {
			return visit((LastExpList)el);
		}
	}

	@Override
	public Integer visit(PairExpList el) {
		// TODO Auto-generated method stub
		Exp head = el.getHead();
		ExpList tail = el.getTail();
		return head.accept(this) + tail.accept(this);
	}

	@Override
	public Integer visit(LastExpList el) {
		return el.getHead().accept(this); 
	}
	

}
