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
		Exp aux = s.getExp(); 
		if (aux instanceof EseqExp) {
			Stm aux1 = ((EseqExp) s.getExp()).getStm();
			return aux1.accept(this);
		}
		return 0;
	}

	@Override
	public Integer visit(CompoundStm s) {
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
		/** laco para contar a quantidade de argumentos passados no print */
		while(aux instanceof PairExpList) {
			retorno++; 
			aux = ((PairExpList) aux).getTail(); 
		}
		if (aux instanceof LastExpList) {
			retorno++; 
		}

		return Math.max(retorno, s.getExps().accept(this));
	}

	@Override
	public Integer visit(Exp e) {
		return e.accept(this);
	}

	@Override
	public Integer visit(EseqExp e) {
		Stm stm = e.getStm();
		Exp exp = e.getExp();
		int a = stm.accept(this); 
		int b = exp.accept(this); 
		return Math.max(a,b);
	}

	@Override
	public Integer visit(IdExp e) {
		return 0;
	}

	@Override
	public Integer visit(NumExp e) {
		return 0;
	}

	@Override
	public Integer visit(OpExp e) {
		Exp left,right; 
		left = e.getLeft();
		right = e.getRight(); 
		int a,b; 
		a = e.getLeft().accept(this); 
		b =  e.getRight().accept(this); 
		return Math.max(a,b);
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
		int a, b; 
		a = head.accept(this);
		b =  tail.accept(this);
		return Math.max(a,b);
	}

	@Override
	public Integer visit(LastExpList el) {
		return Math.max(1, el.getHead().accept(this)); 
	}


}
