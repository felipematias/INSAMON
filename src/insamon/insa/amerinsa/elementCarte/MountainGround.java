package insamon.insa.amerinsa.elementCarte;


import insamon.insa.amerinsa.Personnage;
import insamon.insa.amerinsa.R;
import insamon.insa.amerinsa.pokemon.Eevee;
import insamon.insa.amerinsa.pokemon.Growlithe;
import insamon.insa.amerinsa.pokemon.Onix;
import insamon.insa.amerinsa.pokemon.Pikachu;
import insamon.insa.amerinsa.pokemon.Pokemon;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class MountainGround extends Elements implements Traversable {
		

	public MountainGround() {
		super("MountainGround","MountainGround");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.mountain_ground);
		return b;
	}

	public boolean action(Personnage p) {  
		boolean res=false;
		int random = (int) (Math.random()*20);
		
		if(random<2){
			Pikachu pikachu= new Pikachu();
			setLvl(pikachu);
			pikachu.setPdvEvolution();
			this.setPokemonAttaquant(pikachu);
			res=true;
		}
		else if(random==4){
			Onix onix = new Onix();
			setLvl(onix);
			onix.setPdvEvolution();
			this.setPokemonAttaquant(onix);
			res=true;
		}
		else if(random==6){
			Growlithe growlithe = new Growlithe();
			setLvl(growlithe);
			growlithe.setPdvEvolution();
			this.setPokemonAttaquant(growlithe);
			res=true;
		}
		
		else if(random>18){
			Eevee eevee = new Eevee();
			setLvl(eevee);
			eevee.setPdvEvolution();
			this.setPokemonAttaquant(eevee);
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
			p.setLvl(5);
		}
		else if(random>10 && random<=20){
			p.setLvl(6);
		}
		else if(random>20 && random<=30){
			p.setLvl(7);
		}
	}


}
