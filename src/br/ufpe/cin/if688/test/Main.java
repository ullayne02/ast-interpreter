package br.ufpe.cin.if688.test;

import br.ufpe.cin.if688.symboltable.Table;
import br.ufpe.cin.if688.visitor.Interpreter;
import br.ufpe.cin.if688.visitor.MaxArgsVisitor;

class Main {
	public static void main(String args[]) throws java.io.IOException {
		//max args de um programa
		// qual eh a quantidade maxima de argumentos passados para um print qualquer no programa
		MaxArgsVisitor maxArgs = new MaxArgsVisitor();
		System.out.println("MaxArgs printPrint: " + maxArgs.visit(Prog.printPrint));
		System.out.println("MaxArgs Prog: " + maxArgs.visit(Prog.prog));
		System.out.println("MaxArgs Prog2: " + maxArgs.visit(Prog.prog2));
		System.out.println("MaxArgs print1234: " + maxArgs.visit(Prog.print1234));
		System.out.println("MaxArgs prog3: " + maxArgs.visit(Prog.prog3));
		System.out.println("MaxArgs print: " + maxArgs.visit(Prog.print));
		System.out.println("MaxArgs prog5: " + maxArgs.visit(Prog.prog5));


		//interpretar programas(Prog.prog...);
		System.out.println("===================================");
		System.out.println("Prog.prog");		
		Interpreter interpreter = new Interpreter(null);
		Table t = interpreter.visit(Prog.prog);
		if (t!=null) System.out.println(t.toString());
		System.out.println("===================================");
		System.out.println("Prog.prog2");
		interpreter = new Interpreter(null);
		t = interpreter.visit(Prog.prog2);
		if (t!=null) System.out.println(t.toString());
		System.out.println("===================================");		
		System.out.println("Prog.print1234");
		interpreter = new Interpreter(null);
		t = interpreter.visit(Prog.print1234);
		if (t!=null) System.out.println(t.toString());
		System.out.println("===================================");
		System.out.println("Prog.printPrint");
		interpreter = new Interpreter(null);
		t = interpreter.visit(Prog.printPrint);
		if (t!=null) System.out.println(t.toString());
		System.out.println("===================================");		
		System.out.println("Prog.prog3");
		interpreter = new Interpreter(null);
		t = interpreter.visit(Prog.prog3);
		if (t!=null) System.out.println(t.toString());
		System.out.println("===================================");
		System.out.println("Prog.prog5");
		interpreter = new Interpreter(null);
		t = interpreter.visit(Prog.prog5);
		if (t!=null) System.out.println(t.toString());
		System.out.println("===================================");
	}
}