import java.util.ArrayList;

public class Instance {

	//Matrices
	public double[][] matriceCouts;
	public double[][] matriceTemps;

	//Tableaux
	public int[] clients;
	public int[] chauffeurs;
	public double[] demandeClient;
	public double[] dureeChargement;
	public ArrayList<double []> plageHoraires;

	//Nombres
	public int nbClients;
	public int  nbChauffeurs;
	public double capaciteVehicule;
	public double coutFixe;
	
	public Instance(){
		//Instancier les données
		this.nbClients = 3;
		this.nbChauffeurs = 1;
		this.capaciteVehicule = 50;
		this.coutFixe = 10;
		
		this.clients = new int [nbClients];
		for (int i = 0; i <nbClients; i++) {
			clients[i] = i;
		}
		this.chauffeurs=new int[nbChauffeurs];
		for (int i = 0; i < nbChauffeurs; i++) {
			chauffeurs[i]=i;
		}
		this.demandeClient=new double[nbClients+1];
		for (int i = 1; i < nbClients+1; i++) {
			demandeClient[i]=i+1;
		}
		demandeClient[0] = 0;
		
		this.dureeChargement=new double[nbClients+1];
		for (int i = 1; i < nbClients+1; i++) {
			dureeChargement[i]=i+1;
		}
		dureeChargement[0] =0;
		
		plageHoraires = new ArrayList<double[]>();
		plageHoraires.add(new double [2]);
		plageHoraires.get(0)[0]=0;
		plageHoraires.get(0)[1]=100;
		for (int i = 1; i < nbClients+1; i++) {
			plageHoraires.add(new double [2]);
			plageHoraires.get(i)[0]=0;
			plageHoraires.get(i)[1]=i+20;
		}
		
		
		this.matriceCouts = new double[nbClients+1][nbClients+1];
		for (int i = 0; i <nbClients+1; i++) {
			for (int j = 0; j < nbClients+1; j++) {
				if(i==j){
					matriceCouts[i][j]=0;
				}
			}
		}
		matriceCouts[0][1] = 6;
		matriceCouts[0][2] = 3;
		matriceCouts[0][3] = 8;

		matriceCouts[1][0] = 6;
		matriceCouts[2][0] = 3;
		matriceCouts[3][0] = 8;

		
		matriceCouts[1][2] = 4;
		matriceCouts[2][1] = 4;
		
		matriceCouts[1][3] = 2;
		matriceCouts[3][1] = 2;
		
		matriceCouts[2][3] = 4;
		matriceCouts[3][2] = 4;

		this.matriceTemps = new double[nbClients+1][nbClients+1];
		for (int i = 0; i <nbClients+1; i++) {
			for (int j = 0; j < nbClients+1; j++) {
				if(i==j){
					matriceTemps[i][j]=0;
				}
			}
		}
		matriceTemps[0][1] =3;
		matriceTemps[0][2] =5;
		matriceTemps[0][3] =10;

		matriceTemps[2][0] = 5;
		matriceTemps[1][0] = 3;
		matriceTemps[3][0] =10;

		matriceTemps[1][2] = 2;
		matriceTemps[2][1] = 2;

		matriceTemps[1][3] = 8;
		matriceTemps[3][1] = 8;

		matriceTemps[2][3] = 6;
		matriceTemps[3][2] = 6;

	}
	
	
	public double[][] getMatriceCouts() {
		return matriceCouts;
	}
	public void setMatriceCouts(double[][] matriceCouts) {
		this.matriceCouts = matriceCouts;
	}
	public double[][] getMatriceTemps() {
		return matriceTemps;
	}
	public void setMatriceTemps(double[][] matriceTemps) {
		this.matriceTemps = matriceTemps;
	}
	public int[] getClients() {
		return clients;
	}
	public void setClients(int[] clients) {
		this.clients = clients;
	}
	public int[] getChauffeurs() {
		return chauffeurs;
	}
	public void setChauffeurs(int[] chauffeurs) {
		this.chauffeurs = chauffeurs;
	}
	public double[] getDemandeClient() {
		return demandeClient;
	}
	public void setDemandeClient(double[] demandeClient) {
		this.demandeClient = demandeClient;
	}
	public double[] getDureeChargement() {
		return dureeChargement;
	}
	public void setDureeChargement(double[] dureeChargement) {
		this.dureeChargement = dureeChargement;
	}
	public ArrayList<double[]> getPlageHoraires() {
		return plageHoraires;
	}
	public void setPlageHoraires(ArrayList<double[]> plageHoraires) {
		this.plageHoraires = plageHoraires;
	}
	public int getNbClients() {
		return nbClients;
	}
	public void setNbClients(int nbClients) {
		this.nbClients = nbClients;
	}
	public int getNbChauffeurs() {
		return nbChauffeurs;
	}
	public void setNbChauffeurs(int nbChauffeurs) {
		this.nbChauffeurs = nbChauffeurs;
	}
	public double getCapaciteVehicule() {
		return capaciteVehicule;
	}
	public void setCapaciteVehicule(double capaciteVehicule) {
		this.capaciteVehicule = capaciteVehicule;
	}
	public double getCoutFixe() {
		return coutFixe;
	}
	public void setCoutFixe(double coutFixe) {
		this.coutFixe = coutFixe;
	}

	

	
	
/*	public double[][] matriceCouts;
	public double[][] matriceTemps;

	//Tableaux
	public int[] clients;
	public int[] chauffeurs;
	public double[] demandeClient;
	public double[] dureeChargement;
	public double[] dureeDechargement;
	public ArrayList<int []> plageHoraires;

*/




}
