/**
 * 
 */
package insamon.insa.amerinsa.elementCarte;

import java.io.Serializable;

import insamon.insa.amerinsa.pokemon.Pokemon;
import android.content.res.Resources;
import android.graphics.Bitmap;

/**
 * @author Felipe
 *Un element sera tout element du jeu qui n'est pas pokemon et qui sera placé dans la map
 */
public abstract class Elements implements Serializable {
	private String type;
    private int posX;
    private int posY;
    private String nomImage;
    private int tailleX;
    private int tailleY;
    private Bitmap image;
    private Pokemon pokemonAttaquant;
    private Pokemon pokemonDresseur;
    

   
	public Pokemon getPokemonAttaquant() {
		return pokemonAttaquant;
	}


	public void setPokemonAttaquant(Pokemon pokemonAttaquant) {
		this.pokemonAttaquant = pokemonAttaquant;
	}


	public Pokemon getPokemonDresseur() {
		return pokemonDresseur;
	}


	/**
	 * 
	 */
	public Elements(String type, String nomImage) {
		
		this.type=type;
		this.tailleX=64;
		this.tailleY=64;
		
		// TODO Auto-generated constructor stub
	}
	

    public boolean equals(Elements e) {
    	if(e.type==this.type){
        return true;
        }else return false;
    	
    }


    public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	

	public Bitmap getImage() {
		return image;
	}


	public String getType() {
		return type;
	}

	public String getNomImage() {
		return nomImage;
	}
	
	
	/**
	 * Cette méthode sera redefini dans les elements pour charger l'image correspondente
	 * @param r
	 * @return
	 */
	public abstract Bitmap chargerImage(Resources r);
	
	
	@Override
	public String toString() {
		return "Elements [type=" + type + ", posX=" + posX + ", posY=" + posY
				+ ", nomImage=" + nomImage + ", dimension="+tailleX+","+tailleY+" , image=" + image + "]";
	}
	
	
}

