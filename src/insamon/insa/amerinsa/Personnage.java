/**
 * 
 */
package insamon.insa.amerinsa;

import insamon.insa.amerinsa.pokemon.Pokemon;

import java.io.Serializable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * @author Felipe Guillaume
 */
public class Personnage implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5975671021108315249L;
	//private String nom;
    private int posX;
    private int posY;
	private Pokemon pokemon;
	// ce sont en fait les coordonnées de l'angle en haut � droite de l'écran par rapport à la map globale
	private int x=40; // si on modifie les coord de départ dans la map, il faut le faire aussi dans personnage!
	private int y=33;

	
	public Personnage(String nom, int posX, int posY) {

		this.posX=posX;
		this.posY=posY;
		
	}
	
	/*
	 * accesseurs
	 */
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public int getPosX() {return posX;}
	public void setPosX(int posX) {this.posX = posX;}
	public int getPosY() {return posY;}
	public void setPosY(int posY) {this.posY = posY;}
	public Pokemon getPokemon() {return pokemon;}
	public void setPokemon(Pokemon pokemon) {this.pokemon = pokemon;}
	public String getPokemonNom() {return pokemon.getNomPokemon();}
	
	
	public int getPokemonPDV() {return pokemon.getPdv();}
	
	/* 
	 * on charge les 4 images de ash.
	 */
	
	public Bitmap chargerImageFront(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.ashfront);
		return b;
	}

	public Bitmap chargerImageBack(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.ashback);
		return b;
	}
	
	public Bitmap chargerImageLeft(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.ashleft);
		return b;
	}
	
	public Bitmap chargerImageRight(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.ashright);
		return b;
	}

	
	/*
	 * méthodes de déplacement
	 * 
	 */
	
	public void deplacerGauche(){
		System.out.println("Deplacement gauche");
		x=x-1;	
	}
	
	public void deplacerDroite(){
		System.out.println("Deplacement droite");
		x=x+1;
	}
	
	public void deplacerHaut(){
		System.out.println("Deplacement haut");
		y=y-1;
	}
	
	public void deplacerBas(){
		System.out.println("Deplacement bas");
		y=y+1;
	}
	
}


