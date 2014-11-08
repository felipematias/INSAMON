package insamon.insa.amerinsa;

import java.util.ArrayList;

import insamon.insa.amerinsa.pokemon.Blastoise;
import insamon.insa.amerinsa.pokemon.Bulbasaur;
import insamon.insa.amerinsa.pokemon.Charizard;
import insamon.insa.amerinsa.pokemon.Charmander;
import insamon.insa.amerinsa.pokemon.Charmeleon;
import insamon.insa.amerinsa.pokemon.Guinchu;
import insamon.insa.amerinsa.pokemon.Isachu;
import insamon.insa.amerinsa.pokemon.Ivysaur;
import insamon.insa.amerinsa.pokemon.Pikachu;
import insamon.insa.amerinsa.pokemon.Pokemon;
import insamon.insa.amerinsa.pokemon.Squirtle;
import insamon.insa.amerinsa.pokemon.Venusaur;
import insamon.insa.amerinsa.pokemon.Wartortle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 
 * @author Felipe
 *L'activity InitActivity est l'ecran initial du jeu, ou on peut choisir entre continuer l'ancienne partie ou 
 *commencer un autre.
 */

public class InitActivity extends Activity {
		
	private Vibrator myVib;
	private int x;
	private int y;
	private Pokemon pokemonDresseur;
    private ArrayList<Integer> coord =new ArrayList <Integer>();
    private Enigme enigme=new Enigme("","","","","");
    private String nomPokemon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);   
	     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, //pour faire l'ecran fullscreen
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_init);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
		TextView pokInit = (TextView) findViewById(R.id.nouvellePartie);
		pokInit.setOnClickListener(new View.OnClickListener() {

	        public void onClick(View v) {
	        	myVib.vibrate(50);
	            Intent myintent2 = new Intent(InitActivity.this,PokInitActivity.class);
	            startActivity(myintent2);

	        }
	    });
		
		TextView continuer = (TextView) findViewById(R.id.continuer);
		continuer.setOnClickListener(new View.OnClickListener() {

	        public void onClick(View v) {
	        	myVib.vibrate(50);
	        	getFromPrefs();
	        	read();
	        	if(nomPokemon!= null){ // On commence l'activity map juste si on avait une sauvegarde à lire, si non on ne fait rien
	        	putSavedIntents();}
	        }
	    });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.init, menu);
		return true;
	}
	
	public void putSavedIntents(){
	    Intent myintent2 = new Intent(InitActivity.this,MapActivity.class);
	    //myintent2.putExtra("Read","Read");
	    myintent2.putExtra("pokeDresseur",pokemonDresseur);
	    myintent2.putExtra("x",x);
		myintent2.putExtra("y",y);
		myintent2.putExtra("enigme", enigme);
		myintent2.putExtra("coord", coord); 
	    startActivity(myintent2);
	    Toast.makeText(this, "Bon Jeu! (:", Toast.LENGTH_LONG).show();
	}
	
	/**
	 * Cette méthode sert à lire les donnés qu'on a stocké dans les preferences du portable
	 */
	public void read(){
		
		SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		nomPokemon = preferences.getString("Nom Pokemon", null);
		int lvlPokemon= preferences.getInt("Level Pokemon", 0);
		int expPokemon= preferences.getInt("Exp Pokemon", 0);
		int pdvPokemon= preferences.getInt("Pdv Pokemon", 0);
		int attaquePokemon= preferences.getInt("Attaque Pokemon", 0);
		int defensePokemon= preferences.getInt("Defense Pokemon", 0);
		int pdvMax= preferences.getInt("PdvMax Pokemon", 0);
		int jeuPosX= preferences.getInt("Position X", 0);
		int jeuPosY= preferences.getInt("Position Y", 0);
		
		
		if(nomPokemon!= null){  // On verifie si on a une sauvegarde pour ne pas charger une quelque chose qui n'existe pas
		//On charge le pokemon
		loadPok(nomPokemon);
		pokemonDresseur.setpExperience(expPokemon);
		pokemonDresseur.setPdv(pdvPokemon); 
		pokemonDresseur.setLvl(lvlPokemon);
		pokemonDresseur.setPointsDeVieMax(pdvMax);
		pokemonDresseur.setpAttaque(attaquePokemon);
		pokemonDresseur.setpDefense(defensePokemon);
		getIntent().putExtra("pokeDresseur",pokemonDresseur);
		x=jeuPosX;
		y=jeuPosY;

		
		System.out.println(jeuPosX+"+"+jeuPosY+"+"+nomPokemon+"+"+pdvPokemon+"+"+lvlPokemon);
		}
	}
	
	/**
	 * Cette méthode charge le pokemon correspondant au String qu'on a sauvegardé
	 * @param nomPokemon c'est le nom du pokemon qui etait stocké dans les preferences
	 */
	public void loadPok(String nomPokemon){
			// on stocke le pokemon choisi ds l'attribut correspondant.
		if((nomPokemon.equals("Charmander"))){this.pokemonDresseur=new Charmander();}
        else if((nomPokemon.equals("Charmeleon"))){this.pokemonDresseur=new Charmeleon();}
        else if(nomPokemon.equals("Charizard")){this.pokemonDresseur=new Charizard();}
        else if(nomPokemon.equals("Bulbasaur")){this.pokemonDresseur=new Bulbasaur();}
        else if((nomPokemon.equals("Ivysaur"))){this.pokemonDresseur=new Ivysaur();}
        else if(nomPokemon.equals("Venusaur")){this.pokemonDresseur=new Venusaur();}
        else if(nomPokemon.equals("Squirtle")){this.pokemonDresseur=new Squirtle();}
        else if(nomPokemon.equals("Wartortle")){this.pokemonDresseur=new Wartortle();}
        else if(nomPokemon.equals("Blastoise")){this.pokemonDresseur=new Blastoise();}
        else if(nomPokemon.equals("Pikachu")){this.pokemonDresseur=new Pikachu();}
        else if(nomPokemon.equals("Isachu")){this.pokemonDresseur=new Isachu();}
        else if(nomPokemon.equals("Guinchu")){this.pokemonDresseur=new Guinchu();}
		}
	
	
	/**
	 * On prend les entiers des coordonnés qu'on a sauvegardé et on les écrit de nouveau dans la liste coord,
	 * après on enleve les enigmes avec ces coordonnés.
	 */
	public void getFromPrefs(){
	    int[] ret;
	    SharedPreferences prefs = getApplicationContext().getSharedPreferences("NAME", getApplicationContext().MODE_PRIVATE);
	    int count = prefs.getInt("Count", 0);
	    ret = new int[count];
	    for (int i = 0; i < count; i++){
	        coord.add(prefs.getInt("IntValue_"+ i, i));
	        System.out.println("1");
	    }
	}	

}
