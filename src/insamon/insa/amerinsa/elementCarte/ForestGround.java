package insamon.insa.amerinsa.elementCarte;


import insamon.insa.amerinsa.Personnage;
import insamon.insa.amerinsa.R;
import insamon.insa.amerinsa.pokemon.Butterfree;
import insamon.insa.amerinsa.pokemon.Haunter;
import insamon.insa.amerinsa.pokemon.Metapod;
import insamon.insa.amerinsa.pokemon.Pokemon;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class ForestGround extends Elements implements Traversable {
		

	public ForestGround() {
		super("ForestGround","ForestGround");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.forest_ground);
		return b;
	}

	public boolean action(Personnage p) {
		boolean res=false;
		int random = (int) (Math.random()*20);
		
		if(random<2){
			Haunter haunter= new Haunter();
			setLvl(haunter);
			haunter.setPdvEvolution();
			this.setPokemonAttaquant(haunter);
			res=true;
		}
		else if(random>18){
			Butterfree butterfree= new Butterfree();
			setLvl(butterfree);
			butterfree.setPdvEvolution();
			this.setPokemonAttaquant(butterfree);
			res=true;
		}
		else if(random==5){
			Metapod metapod= new Metapod();
			setLvl(metapod);
			metapod.setPdvEvolution();
			this.setPokemonAttaquant(metapod);
			res=true;
		}
		return res;
	}
	
	/**
	 * Cette méthode sert a mettre des niveaux aletoires dans les pokemons
	 * @param p
	 */
	public void setLvl(Pokemon p){
		int random = (int) (Math.random()*30);
		
		
		if(random<=10){
			p.setLvl(3);
		}
		else if(random>10 && random<=20){
			p.setLvl(4);
		}
		else if(random>20 && random<=30){
			p.setLvl(5);
		}
	}


}
