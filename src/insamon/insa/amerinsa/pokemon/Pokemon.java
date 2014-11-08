package insamon.insa.amerinsa.pokemon;

import java.io.Serializable;

import android.content.res.Resources;
import android.graphics.Bitmap;

/**
 * @author Felipe
 *La classe pokemon est abstract parce qu'on ne peut pas creer un pokemon qui est juste pokemon, on a defini
 *une constante pointsDeVieMax qui sera donné dans le constructeur
 */

public abstract class Pokemon implements Serializable {
	
	
	/**
	 * déclaration et initialisation des attributs des pokemons
	 */
	private static final long serialVersionUID = 2047987874169882419L;
	private String nomPokemon;
	private String nomAttaque;
    private int pointsDeVieMax; //maximum de points de vie du pokemon
    private int pAttaque;
    private int pDefense;
    private String nomImage;
    private Bitmap image;
    private int pExperience=0;//L'experience que le pokemon va gagner avec les enigmes et les combats
    private int pdv;  //points de vie qui vont varier si le pokemon rentre dans un combat par exemple
    private int lvl=1; // les pokemons vont avoir des niveaux(1 à 14)
    private boolean donjonAccessible=false; // permet d'activer le donjon pour la fin du jeu.
    private boolean donjonEntre=false;//permet de voir si on est dans le donjon ou pas
    private int compteur=0;// permet de savoir ou on en est dans le donjon.
    
    
    
    
    /**
     * Cela sera notre constructeur des pokemons, chaque param sera redifini dans les nouveaux pokemons
     * @param nomPokemon
     * @param pointsDeVieMax   cela sera une constante
     * @param pAttaque
     * @param pDefense
     * @param nomAttaque
     * @param nomImage
     */
    public Pokemon(String nomPokemon,int pointsDeVieMax, int pAttaque,int pDefense, 
    		 String nomAttaque, String nomImage) {
    	
    	this.nomPokemon=nomPokemon;
        this.pointsDeVieMax =pointsDeVieMax;
        this.pAttaque = pAttaque;
        this.pDefense= pDefense;
        this.pdv=pointsDeVieMax;
        this.nomAttaque=nomAttaque;
        this.nomImage=nomImage;
  	
    }
    
    /**
     *On aura toujours de dégats inflingés dans l'attaque facile, l'intensité de l'attaque sera en fonction 
     *de deux math.random et sera aussi en fonction du lvl des pokemons, si la defense est plus grand que l'attaque 
     *on inflinge un attaque minimum.
     * @param p sera le pokemon lequel mon pokemon va attaquer
     */
    
    public void attaqueClassique(Pokemon p ) {
	    	
	    double i= Math.random();   	// facteur chance de la réussite d'attaque
	    double j= Math.random();	// facteur chance de la réussite de défense
	    if (i<0.4){ i= i+0.3; }		
	    if (j<0.4){ j= j+0.3; }
	    	
	    int nbOffense= (int) ((this.pAttaque*i)+(this.pAttaque*0.1*i*this.lvl));	// pondération de pAttaque
		int nbDefense= (int) ((p.getPDefense()*j)+(p.getPDefense()*0.1*j*p.lvl));		// pondération de pDefense 
		int nbDegats= nbOffense-nbDefense;					// calcul des dégats infligés
	    		
	    	
	    	if(nbDefense<=nbOffense){	// dans le cas ou l'attaque est supérieure à la défense
	    		p.pdv= p.pdv-nbDegats; 								// inflige les dégats 
	    		
	    		if(p.pdv<0){ p.pdv=0 ;}				
	    		
	    	}else{							
	    		p.pdv=p.pdv-(int)(0.3*this.pAttaque); // ataque minimale
	    		if(p.pdv<0){ p.pdv=0 ;}	
	    	}
	    	
    	}
    /**
     * L'attaque aleatoire peut ou pas avoir lieu, si il est réussi il inflige plus de dégats, si non il inflige aucun dégat
     * @param p sera le pokemon lequel mon pokemon va attaquer
     */
    
    public void attaqueAleatoire(Pokemon p) {
    	
	    double i= Math.random();   	// facteur chance de la réussite d'attaque
	    double j= Math.random();	// facteur chance de la réussite de défense
	    if (0.3<i && i<0.7){ i= 0;}
	    if (0.7<i && j<0.8){ i= 2;}
	    if (j<0.4){ j= j+0.3; }
	    	
	    int nbOffense= (int) ((this.pAttaque*i)+(this.pAttaque*0.1*i*this.lvl));	// pondération de pAttaque
		int nbDefense= (int) ((p.getPDefense()*j)+(p.getPDefense()*0.1*j*p.lvl));		// pondération de pDefense 
		int nbDegats= nbOffense-nbDefense;					// calcul des dégats infligés
	    		
	    	
	    	if(nbDefense<=nbOffense){	// dans le cas ou l'attaque est supérieure à la défense
	    		p.pdv= p.pdv-nbDegats; 								// inflige les dégats
	    		
	    		if(p.pdv<0){ p.pdv=0 ;}				
	    		
	    	}else{
	    		//pas  d'attaque minimale pour l'attaque aleatoire
	    	}
    	
    }
    
    
    

    public boolean isDonjonEntre() {
		return donjonEntre;
	}

	public void setDonjonEntre(boolean donjonEntre) {
		this.donjonEntre = donjonEntre;
	}

	public boolean isDonjonAccessible() {
		return donjonAccessible;
	}

	public void setDonjonAccessible(boolean donjonDebloque) {
		this.donjonAccessible = donjonDebloque;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}


	public int getPAttaque() {
        return pAttaque;
    }

	public void setpAttaque(int pAttaque) {
		this.pAttaque = pAttaque;
	}

	public int getpDefense() {
		return pDefense;
	}

	public int getPDefense() {
        return pDefense;
    }

    public void setpDefense(int pDefense) {
		this.pDefense = pDefense;
	}

	public int getpExperience() {
		return pExperience;
	}

	public void setpExperience(int pExperience) {
		this.pExperience = pExperience;
	}
	

	public String getNomAttaque() {
		return nomAttaque;
	}

	public int getPdv() {
		return pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
	}
	
	public String getNomPokemon() {
		return nomPokemon;
	}
	

	public String getNomImage() {
		return nomImage;
	}

	public int getPointsDeVieMax() {
		return pointsDeVieMax;
	}
	

	public void setPointsDeVieMax(int pointsDeVieMax) {
		this.pointsDeVieMax = pointsDeVieMax;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

    public void perdrePDV(int i) {
    	this.pdv=this.pdv-i;
    }

    public void gagnerPDV(int i) {
    	this.pdv=this.pdv+i;
    }
   
    
    
    /**
     * Cette méthode va être redéfinie dans chaque pokemon, où on va prendre son image dans les ressources.
     * @param r on va prendre la ressource quand on appele cette methode dans une activity ou view
     * @return l'image du pokemon
     */
    public abstract Bitmap chargerImage(Resources r);
    
    
    /**
     * On va appeler cette la methode quand on gagne une bataille pour que notre pokemon gagne de l'experience.
     * @param p, cela sera le pokemon qu'on a battu, car on gagne de l'experience en foncion du niveau du pokemon battu.
     * @return
     */
	public int win(Pokemon p){
		this.pExperience=pExperience+50*p.lvl;
		return 50*p.lvl;
	}
	
	/**
	 * Pour faire varier la vie, l'attaque et defense des pokemons qui sont généré avec des niveaux aleatoires 
	 */
	public void setPdvEvolution(){
		this.pointsDeVieMax=pointsDeVieMax+10*lvl;
		this.pdv=this.pointsDeVieMax;
		this.pAttaque=pAttaque+lvl;
		this.pDefense=pDefense+lvl;
	}

	@Override
	public String toString() {
		return "Pokemon [pointsDeVie=" + pdv + ", pAttaque=" + pAttaque
				+ ", pDefense=" + pDefense 
				+ ", image=" + image + ", pExperience=" + pExperience + "]";
	}
	
	/**
	 * Tout pokemon commence dans le niveu 1 et evolue de niveau en fonction de ses points d'experience,
	 * qu'il peut gagner quand il gagne une bataille par exemple.
	 * @return le pokemon a-t-il gagné en niveau?
	 */	
	public void evoluerNiveau(){

		if(this.getpExperience()>=200 && this.getpExperience()<500){
			this.lvl=2;
		}else if(this.getpExperience()>=500 && this.getpExperience()<1000){
			this.lvl=3;
		}else if(this.getpExperience()>=1000 && this.getpExperience()<1600){
			this.lvl=4;
		}else if(this.getpExperience()>=1600 && this.getpExperience()<2200){
			this.lvl=5;
		}else if(this.getpExperience()>=2200 && this.getpExperience()<3000){
			this.lvl=6;
		}else if(this.getpExperience()>=3000 && this.getpExperience()<3600){
			this.lvl=7;
		}else if(this.getpExperience()>=3600 && this.getpExperience()<4500){
			this.lvl=8;
		}else if(this.getpExperience()>=4500 && this.getpExperience()<5500){
			this.lvl=9;
		}else if(this.getpExperience()>=7000 && this.getpExperience()<8000){
			this.lvl=10;
		}else if(this.getpExperience()>=8000 && this.getpExperience()<12000){
			this.lvl=11;
		}else if(this.getpExperience()>=12000 && this.getpExperience()<17000){
			this.lvl=12;		
		}else if(this.getpExperience()>=17000 && this.getpExperience()<23000){
			this.lvl=13;
		}else if(this.getpExperience()>=23000) {
			this.lvl=14;
		}

	}

}
