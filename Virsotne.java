

public class Virsotne {
	int h1,h2,h3,h4;
	int depth, novertejums;
	int id = 0;
	
	public Virsotne(int h1, int h2, int h3,int h4, int depth, int id) {
		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;
		this.depth = depth;
		this.novertejums = 0;
		this.id = id;
	}
	public void printVirsotne() {
		System.out.println("Vertibas :"+h1+" "+h2+" "+h3+" "+h4);
		System.out.println("Dzilums: "+depth);
		System.out.println("Novertejums, id : "+novertejums+", "+id);
		System.out.println();
		
	}
	public int getEval() {
		return novertejums;
	}
	public int getDepth() {
		return depth;
	}
	
}