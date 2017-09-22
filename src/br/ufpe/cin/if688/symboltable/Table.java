package br.ufpe.cin.if688.symboltable;

public class Table {
	public String id;
	public int value;
	public Table tail;
	public Table(String i, int v, Table t) {
		id = i;
		value = v;
		tail = t;
	}
	public String toString() {
		String result = id+"="+value+" ==> ";
		Table aux = tail;
		while(aux!=null) {
			result += aux.id+"="+aux.value+" ==> ";
			aux = aux.tail;
		}
		result += "Nil";
		return result;
	}
}
