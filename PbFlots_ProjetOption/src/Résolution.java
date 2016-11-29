import java.util.ArrayList;

import javax.swing.plaf.synth.SynthScrollBarUI;

import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloObjectiveSense;
import ilog.cplex.IloCplex;

public class Résolution {

	public static void resoudre(Instance instance) throws IloException { 

		//Données d'entrée
		int nbClients = instance.nbClients+1;
		int nbChauffeurs = instance.nbChauffeurs;
		double capaciteVehicule = instance.capaciteVehicule;
		double coutFixe = instance.coutFixe;

		int[] clients = instance.clients;
		int[] chauffeurs = instance.chauffeurs;
		double[] demandeClient = instance.demandeClient;
		double[] dureeChargement = instance.dureeChargement;
		ArrayList<double []> plageHoraires = instance.plageHoraires;

		double[][] matriceCouts = instance.matriceCouts;
		double[][] matriceTemps = instance.matriceTemps;

		//Déclaration de variables
		IloIntVar[][] x = new IloIntVar[nbClients][nbClients];
		IloNumVar[] t;// = new IloNumVar[nbClients];

		IloCplex cplex = new IloCplex();

		//Instanciation des variables
		for (int i = 0; i < nbClients; i++) {
			for (int j = 0; j < nbClients; j++) {
				x[i][j] = cplex.boolVar("x_" + i+ "_" + j);
			}
		}

//		for (int i = 0; i < nbClients; i++) {
//			t[i] = cplex.numVar(0, 100,"t_" + i);
//		}

		t = cplex.numVarArray(nbClients, 0, 100);
		//FonctionObjectif
		IloLinearNumExpr objectif = cplex.linearNumExpr();

		for (int j = 1; j < nbClients; j++) {
			objectif.addTerm(coutFixe, x[0][j]); 		//indice 0 : dépôt
		}
		for (int i = 0; i <nbClients; i++) {
			for (int j = 0; j < nbClients; j++) {
				objectif.addTerm(matriceCouts[i][j], x[i][j]);			
			}
		}
		cplex.addObjective(IloObjectiveSense.Minimize, objectif);

		//Contrainte 1 :
		for (int i = 1; i < nbClients; i++) {
			IloLinearNumExpr expr= cplex.linearNumExpr();
			for (int j = 0; j < nbClients; j++) {
				expr.addTerm(1, x[i][j]);
			}
			cplex.addEq(expr, 1);
		}

		//Contrainte 2 :
		for (int i = 1; i < nbClients; i++) {
			IloLinearNumExpr expr1= cplex.linearNumExpr();
			IloLinearNumExpr expr= cplex.linearNumExpr();
			for (int j = 0; j < nbClients; j++) {
				expr.addTerm(1,x[i][j]);
				expr1.addTerm(1, x[j][i]);
			}

			cplex.addEq(expr,expr1);
		}

		//Contrainte 3 :
		IloLinearNumExpr expression= cplex.linearNumExpr();
		for (int i = 1; i < nbClients; i++) {
			for (int j = 0; j < nbClients; j++) {
				expression.addTerm(demandeClient[i],x[i][j]);
			}
		}
		cplex.addLe(expression, capaciteVehicule);

		//Contrainte 4 :

		for (int i = 1; i < nbClients; i++) {
			for (int j = 1; j < nbClients; j++) {
					double M = plageHoraires.get(i)[1] + dureeChargement[i] + matriceTemps[i][j] -plageHoraires.get(j)[0] ;
					IloLinearNumExpr expr= cplex.linearNumExpr(); 
					expr.addTerm(1 , t[i]);
					expr.addTerm(-1,t[j]);
					expr.addTerm(M, x[i][j]);
					double nombre = -dureeChargement[i]-matriceCouts[i][j]+M;
					cplex.addLe(expr, nombre);	
			}
		}

		//Contrainte 5 : 
		for (int i = 1; i <nbClients; i++) {
			IloLinearNumExpr expr= cplex.linearNumExpr(); 
			expr.addTerm(1,t[i]);
			cplex.addLe(expr, plageHoraires.get(i)[1]);
			cplex.addGe(expr, plageHoraires.get(i)[0]);

		}
		
		//Contrainte 6 :
	/*	for (int i = 1; i < nbClients; i++) {
			for (int j = 0; j < nbClients; j++) {
				if(i==j){
					IloLinearNumExpr expr= cplex.linearNumExpr(); 
					expr.addTerm(1, x[i][j]);
					cplex.addEq(expr,0);
				}
			}
		}*/
		
		/** Résolution du modèle **/

		// Parameters for the resolution
		//	cplex.setParam(DoubleParam.TimeLimit, TimeLimit);
		cplex.setOut(System.out);
		cplex.exportModel("model.lp");
		

		if(cplex.solve()){
			System.out.println((cplex.getObjValue())); 
			System.out.println("Cplex status : " + cplex.getStatus());
			System.out.println("Value of the objectif function : " + (cplex.getObjValue()));
	
			System.out.println();
			double somme = 0;
			for (int i = 0; i < nbClients; i++) {
				for (int j = 0; j < nbClients; j++) {
					somme += cplex.getValue(x[i][j])*matriceCouts[i][j];
				}
				
			}
			System.out.println("cout total : "+somme);
			System.out.println();
			for (int i = 0; i < nbClients; i++) {
				for (int j = 0; j < nbClients; j++) {
					System.out.println("x_"+i+"_"+j+" : "+ cplex.getValue(x[i][j]) + " plagesH "+plageHoraires.get(i)[0]+" -> "+plageHoraires.get(i)[1]+" heurePassage ");
					System.out.println(cplex.getValue(t[i]));
				}
			}
			System.out.println(cplex.getValue(t[0]));
//			for (int i = 0; i < nbClients; i++) {
//					System.out.println(cplex.getValue(t[i]));
//			
//			}
		}
	}
}
