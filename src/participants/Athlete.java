package participants;

import epreuves.Manche;

public class Athlete implements Participant{
	private String nom;
	private String prenom;
	private char sexe;
	private int force;
	private int endurance;
	private int agilite;
	private Pays lePays;

	/** Créer un athlete pour les JO
	 * 
	 * @param String Le nom de l'athlete 
	 * @param String Le prenom de l'athlete
	 * @param char 'F' ou 'H'
	 * @param int La force de l'athlete comprise entre 1 et 20
	 * @param int L'endurance de l'athlete comprise entre 1 et 20
	 * @param int L'agilité de l'athlete comprise entre 1 et 20
	 * @param Pays Le pays de l'athlete
	 */
	public Athlete(String nom, String prenom, char sexe, int force, int agilite, int endurance, Pays pays) {
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = Character.toUpperCase(sexe);
		if (this.sexe != 'F'){
			this.sexe = 'H';
		}
		this.force = force;
		this.endurance = endurance;
		this.agilite = agilite;

		if(this.force<1 || this.force>20){
			if(this.force<0){this.force = (this.force*-1)-1;}
			this.force = (this.force % 20) +1;
		}

		if(this.endurance<1 || this.endurance>20){
			if(this.endurance<0){this.endurance = (this.endurance*-1)-1;}
			this.endurance = (this.endurance % 20) +1;
		}

		if(this.agilite<1 || this.agilite>20){
			if(this.agilite<0){this.agilite = (this.agilite*-1)-1;}
			this.agilite = (this.agilite % 20) +1;
		}

		this.lePays = pays;
		this.lePays.getLesAthletes().add(this);
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	@Override
	public char getSexe() {
		return this.sexe;
	}

	public int getForce() {
		return this.force;
	}

	public int getEndurance() {
		return this.endurance;
	}

	public int getAgilite() {
		return this.agilite;
	}
	
	@Override
	public Pays getPays() {
		return this.lePays;
	}

	@Override
	public int participer(Manche<? extends Participant> manche) {
		return manche.getEpreuve().getSport().bareme(this);
	}

	@Override 
	public boolean equals(Object o){
		if(o == null){return false;}
		if(o == this){return true;}
		if(!(o instanceof Athlete)){return false;}
		Athlete athlete = (Athlete) o;
		return athlete.nom.equals(this.nom) && athlete.prenom.equals(this.prenom) && this.sexe == athlete.sexe && this.lePays == athlete.lePays;
	}

	@Override
	public String toString(){
		return "("+ "nom: " + this.nom + ", prénom: " + this.prenom + ", sexe: " + this.sexe + ", pays: " + this.lePays.getNomPays() +  ", force: " + this.force + ", agilité: " + this.agilite + ", endurance: " + this.endurance + ")";
	}
}