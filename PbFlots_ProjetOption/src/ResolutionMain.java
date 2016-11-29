import ilog.concert.IloException;

public class ResolutionMain {

	
public static void main(String[] args) throws IloException {
	
	Instance instance = new Instance();
	Résolution.resoudre(instance);
}	
}
