/**
 * 
 */
package insamon.insa.amerinsa;

import insamon.insa.amerinsa.pokemon.Charmander;
import insamon.insa.amerinsa.pokemon.Pikachu;
import insamon.insa.amerinsa.pokemon.Pokemon;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * @author Felipe
 *
 */
public class CombatView extends View {
	
	//initialisation mais n'importe pas. C'est pour pouvoir cr�er qqch sans cr�er de bugs,
	//mais les pokemons � afficher sont fixer par l'activit�.
	private Pokemon pokemonAttaquant= new Charmander();
	private Pokemon pokemonDresseur= new Pikachu();
	private int COMBATX;
	private int COMBATY;
	
	
	public void setScreenSize(){
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		COMBATX = display.getWidth();  // deprecated
		COMBATY= display.getHeight();  // deprecated
		System.out.println(""+display.getWidth()+""+display.getHeight());
	}
	
	
	
	public void setPokemonAttaquant(Pokemon pokemonAttaquant) {
		this.pokemonAttaquant = pokemonAttaquant;
	}

	public void setPokemonDresseur(Pokemon pokemonDresseur) {
		this.pokemonDresseur = pokemonDresseur;
	}

	/**
	 * @param context
	 */
	public CombatView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CombatView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CombatView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	

	

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint crayon=new Paint();
		setScreenSize();
		//Null pointerException..
		// il n'arrive pas � trouver le pokemon envoy�.
		
		Bitmap p=pokemonDresseur.chargerImage(this.getResources());
		//Bitmap p=pikachu.chargerImage(this.getResources());
		canvas.drawBitmap(p, 50, (int)(0.2*COMBATX), crayon);//(p, -30, 120, crayon);
		
		Bitmap c=pokemonAttaquant.chargerImage(this.getResources());
		//Bitmap c=charmander.chargerImage(this.getResources());
		canvas.drawBitmap(c,(int)(0.7*COMBATX),0, crayon);
		System.out.println("COMBATX"+COMBATX+"COMBATY"+COMBATY);
	
	}
	


}
