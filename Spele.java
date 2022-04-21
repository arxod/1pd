import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Spele{

	
	static boolean debug = false;
	static int id = 0;
	static int player = 0;
	static ArrayList<Virsotne> visas = new ArrayList<Virsotne>();
	static Map<Integer, ArrayList<Integer>> loki = new HashMap<Integer, ArrayList<Integer>>();
	
	
	public static void main(String[] args) {
        
        
		Virsotne sakne = new Virsotne(1,2,3,4,1,id);
		loki.put(sakne.id, new ArrayList<Integer>());
		visas.add(sakne);
		generateBelow(sakne);
		eval(0);
		
		if(debug) {
			for (Virsotne virs : visas) {
				virs.printVirsotne();
			}
		}
		GUI.update(0);
	}
	
	
	private static void eval(int ide) {
		if(loki.get(ide).size()==0) {
			if (visas.get(ide).getDepth()%2==player) {
				valueMinus(ide);
				return;
			} else {
				valuePlus(ide);
				return;
			}
		} else {
			loki.get(ide).forEach((n) -> {
				if (visas.get(n).getEval()==0) {
					eval(n);
				}
			});
			
			if (visas.get(ide).getDepth()%2==player) {
				valueMinus(ide);
			} else {
				valuePlus(ide);
			}
			loki.get(ide).forEach((n) -> {
				
				if (visas.get(ide).getDepth()%2==player) {
					if (visas.get(n).getEval()>visas.get(ide).getEval()) {
						valuePlus(ide);
						return;
					}
				} else {
					if (visas.get(n).getEval()<visas.get(ide).getEval()) {
						valueMinus(ide);
						return;
					}
				}
			});
		}
	}
		

	private static void generateBelow(Virsotne virsotne) {
		
		
		int v1 = virsotne.h1;
		int v2 = virsotne.h2;
		int v3 = virsotne.h3;
		int v4 = virsotne.h4;
		
		for(int i = virsotne.h1; i>=1; i--) { 
			id++;
			v1--;
			Virsotne jauna =  new Virsotne(v1,virsotne.h2,virsotne.h3,virsotne.h4,virsotne.depth+1,id);
			loki.put(jauna.id, new ArrayList<Integer>());
			loki.get(virsotne.id).add(jauna.id);
			visas.add(jauna);
			generateBelow(jauna);
			
		}
		for(int i = virsotne.h2; i>=1; i--) {
			id++;
			v2--;
			Virsotne jauna =  new Virsotne(virsotne.h1,v2,virsotne.h3,virsotne.h4,virsotne.depth+1,id);
			loki.put(jauna.id, new ArrayList<Integer>());
			loki.get(virsotne.id).add(jauna.id);
			visas.add(jauna);
			generateBelow(jauna);
		}
		for(int i = virsotne.h3; i>=1; i--) {  
			id++;
			v3--;
			Virsotne jauna =  new Virsotne(virsotne.h1,virsotne.h2,v3,virsotne.h4,virsotne.depth+1,id);
			loki.put(jauna.id, new ArrayList<Integer>());
			loki.get(virsotne.id).add(jauna.id);
			visas.add(jauna);
			generateBelow(jauna);
		}
		for(int i = virsotne.h4; i>=1; i--) {  
			id++;
			v4--;
			Virsotne jauna =  new Virsotne(virsotne.h1,virsotne.h2,virsotne.h3,v4,virsotne.depth+1,id);
			loki.put(jauna.id, new ArrayList<Integer>());
			loki.get(virsotne.id).add(jauna.id);
			visas.add(jauna);
			generateBelow(jauna);
		}
	}
	
	
	private static void valuePlus(int num) {
		Virsotne temp = new Virsotne(visas.get(num).h1,visas.get(num).h2,visas.get(num).h3,visas.get(num).h4,visas.get(num).depth,visas.get(num).id);
		temp.novertejums = 1;
		visas.set(num, temp);
	}
	private static void valueMinus(int num) {
		Virsotne temp = new Virsotne(visas.get(num).h1,visas.get(num).h2,visas.get(num).h3,visas.get(num).h4,visas.get(num).depth,visas.get(num).id);
		temp.novertejums = -1;
		visas.set(num, temp);
	}
	
	
	
	private static int findByVirsotne(int h1, int h2,int h3,int h4, int depth) {
		for (Virsotne vir : visas) {
			if (vir.h1 == h1 && vir.h2 == h2 &&vir.h3 == h3 &&vir.h4 == h4 && vir.depth == depth) {
				return vir.id;
			}
		}
		return -1;
	}
	
	
	
	public static int humanMove(int stack, int amount, int id) {
		if (stack == 1) {
			if (visas.get(id).h1<amount) {
				return -1;
			}
			return (findByVirsotne(visas.get(id).h1-amount, visas.get(id).h2,visas.get(id).h3,visas.get(id).h4, visas.get(id).depth+1));
		}
		if (stack == 2) {
			if (visas.get(id).h2<amount) {
				return -1;
			}
			
			return (findByVirsotne(visas.get(id).h1, visas.get(id).h2-amount,visas.get(id).h3,visas.get(id).h4, visas.get(id).depth+1));
		}
		if (stack == 3) {
			if (visas.get(id).h3<amount) {
				return -1;
			}
			
			return (findByVirsotne(visas.get(id).h1, visas.get(id).h2,visas.get(id).h3-amount,visas.get(id).h4, visas.get(id).depth+1));
		}
		if (stack == 4) {
			if (visas.get(id).h4<amount) {
				return -1;
			}
			
			return (findByVirsotne(visas.get(id).h1, visas.get(id).h2,visas.get(id).h3,visas.get(id).h4-amount, visas.get(id).depth+1));
		} return -1;
		
	}
	static Integer i = -1;
	static boolean move = false;
	
	public static int computerMove(int id) {
		move = false;
		
		if (loki.get(id).size()==0) {
			System.out.println("HUMAN WINS");
			return -1;
			
		}
		
		loki.get(id).forEach((n) -> {
			if(visas.get(id).getDepth()%2==player) {
				if(visas.get(n).getEval()==1) {
					i = n;
					move = true;
					return;
				}
			} else {
				if(visas.get(n).getEval()==-1) {
					i = n;
					move = true;
					return;
				}
			}
		});
		
		if(!move) {
			
			i = loki.get(id).get(0);
		}
		if (debug) {
			visas.get(i).printVirsotne();
		}
		
		return i;
		
	}
}

