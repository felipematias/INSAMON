/**
 * 
 */
package insamon.insa.amerinsa.elementCarte;

import insamon.insa.amerinsa.Personnage;
import insamon.insa.amerinsa.R;
import insamon.insa.amerinsa.pokemon.Articuno;
import insamon.insa.amerinsa.pokemon.Moltres;
import insamon.insa.amerinsa.pokemon.Zapdos;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Felipe
 *Cet élement sera mis dans le jeu juste quand on vient de finir le donjon, si on resoudre un enigme ou sion sauvegarde 
 *et on retourne apres il disparait et on doit finir le donjon de nouveau.
 */
public class HerbeLeg extends Elements implements Traversable{

	/**
	 * @param type
	 * @param nomImage
	 */
	public HerbeLeg() {
		super("HerbeLegendaire","HerbeLegendaire");
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see insamon.insa.amerinsa.elementCarte.Elements#chargerImage(android.content.res.Resources)
	 */
	@Override
	public Bitmap chargerImage(Resources r) {
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.grass_border);
		return b;
	}

	public boolean action(Personnage p) {
		boolean res=false;
		int random = (int) (Math.random()*15);
		
		if(random==1){
			Articuno articuno= new Articuno();
			articuno.setLvl(14);
			this.setPokemonAttaquant(articuno);
			res=true;
		}
		else if(random==7){
			Zapdos zapdos= new Zapdos();
			zapdos.setLvl(14);
			this.setPokemonAttaquant(zapdos);
			res=true;
		}else if(random==14){
			Moltres moltres= new Moltres();
			moltres.setLvl(14);
			this.setPokemonAttaquant(moltres);
			res=true;
		}
		return res;
	}

}
