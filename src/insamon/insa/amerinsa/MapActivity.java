
package insamon.insa.amerinsa;

import insamon.insa.amerinsa.elementCarte.HerbeLeg;
import insamon.insa.amerinsa.elementCarte.Path;
import insamon.insa.amerinsa.pokemon.Blastoise;
import insamon.insa.amerinsa.pokemon.Bulbasaur;
import insamon.insa.amerinsa.pokemon.Charizard;
import insamon.insa.amerinsa.pokemon.Charmander;
import insamon.insa.amerinsa.pokemon.Charmeleon;
import insamon.insa.amerinsa.pokemon.Ivysaur;
import insamon.insa.amerinsa.pokemon.Pikachu;
import insamon.insa.amerinsa.pokemon.Pokemon;
import insamon.insa.amerinsa.pokemon.Squirtle;
import insamon.insa.amerinsa.pokemon.Venusaur;
import insamon.insa.amerinsa.pokemon.Wartortle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * 
 * @author Felipe
 *La classe MapActivity sera l'Activité où on va afficher la map
 */

public class MapActivity extends Activity {
	
	/*
	 * tous les attributs
	 */
	
	private int x=47;// au cas ou on ne revient pas d'un combat mais de l'écran initial 
	private int y=29; // ce sont les coordonnées d'affichage de l'angle en haut à gauche de l'écran.
	private ArrayList<Integer> coord; // liste qui va stocker les coordonnées des énigmes résolues
	private int compteur=0; // compteur d'étapes pour savoir où on en est dans le donjon.
	private String direction; // permet d'indiquer dans quelle direction on va
	private MapJeu mapJeu; 	// le canvas qu'on affiche
	private Pokemon pokemonDresseur;
	private Pokemon pokemonAttaquant;
	ArrayList<Pokemon> listPokJeu = new ArrayList<Pokemon>();
	private Personnage personnage=new Personnage("ash", 64*9, 64*5);
	private Enigme enigme;
	private ImageButton up;
	private ImageButton right;
	private ImageButton left;
	private ImageButton down;
	
	
/* accesseurs*/
	public Personnage getPersonnage() {
		return personnage;
	}
	
	
	/**
	 * permet d'obtenir l'énigme.
	 */
	
	public void chargerEnigme(){
		enigme=mapJeu.getEnigme();

	}

	
	@Override
	/**
	 * c'est le main de l'activity
	 */
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); // méthode qui va appeler le onDraw de la view je crois mais pas sur
		requestWindowFeature(Window.FEATURE_NO_TITLE);   
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, //pour faire l'ecran fullscreen
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	   	setContentView(R.layout.activity_map);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		/* récupération de tous les attributs stockés dans l'intent*/
		Intent i= getIntent();
		// récupération du pokemon du Dresseur
		pokemonDresseur=(Pokemon)i.getSerializableExtra("pokeDresseur");
		// récupére les coordonnées si retour de combat et sinon les coord par défaut définies dans PokinitActivity
		x=(Integer) i.getSerializableExtra("x");
		y=(Integer) i.getSerializableExtra("y");
		enigme=(Enigme) i.getSerializableExtra("enigme");
		coord=(ArrayList<Integer>)i.getSerializableExtra("coord");
	
		// on regénère une map d'origine qui ne tient pas compte des modifications du terrain (énigmes à enlever> traité plus tard)
		initialiserMap(); 

		
		/*
		 * les 4 prochains blocs se rapportent au traitement des énigmes résolues:
		 * -ajouter les coords de l'énigme résolue dans la liste des énigmes résolues, et incrémenter le nb d'énigmes résolues
		 * - y a-t-il assez d'énigmes résolues pour débloquer le donjon?
		 * - les énigmes résolues sont enlevées de la carte pour ne pas tomber dessus 2 fois (et remplacées par du chemin)
		 * - y a-t-il assez d'énigmes pour débloquer le donjon?
		 */
		
//>
		if(enigme.isSolved()){ 
			// on sait que l'élément central est 9/4 donc je rajoute x et y pour le retrouver dans le tableau initial
			System.out.println("élément modifié");
			coord.add(y+4); // ajout de la coord y
			coord.add(x+7);// ajout de la coord x
		}
		
//>		//On verifie si le donjon est accessible, en fonction du nombre d'énigmes résolues
		System.out.println("nombres d'énigmes résolues: "+coord.size()/2);
				if ((coord.size()/2)>=10){ // on teste s'il y a au moins 10 énigmes résolues
					pokemonDresseur.setDonjonAccessible(true);
				}else{pokemonDresseur.setDonjonAccessible(false);}
		
//>		// On enleve les énigmes résolues
		enleverEnigme();
		
		// si c'est possible, on met les pokemon Legendaires.
		remplirPokLegendaires();
		

		up=(ImageButton)findViewById(R.id.upButton);
		right=(ImageButton)findViewById(R.id.rightButton);
		left=(ImageButton)findViewById(R.id.leftButton);
		down=(ImageButton)findViewById(R.id.downButton);
	
		/*
		 * gestion des écouteurs des boutons de déplacement 
		 */
		up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	putBooleanFalse();
                mapJeu.deplacerHaut();
                direction="haut";
                testElement();
            }
        }); 
		
		right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	putBooleanFalse();
            	mapJeu.deplacerDroite();
            	direction="droite";
            	System.out.println("coord x:"+x+" et coord y: "+y);
            	testElement();
            }
        }); 
		
	
		down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	putBooleanFalse();
            	mapJeu.deplacerBas();
            	direction="bas";
            	testElement();
            }
        }); 
		
		left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	putBooleanFalse();
            	mapJeu.deplacerGauche();
            	direction="gauche";
            	testElement(); 
            }
        }); 	
        
	}// fin du onCreate();
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
	
	
	@Override
	/**
	 * gestion du menu
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
	    int id=item.getItemId();
	    if(id==R.id.save){
	        	System.out.println("Save fait");
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	     	    builder.setMessage("Tu veux vraiment sauvegarder?")
	     	            .setCancelable(false)
	     	            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
	     	                public void onClick(DialogInterface dialog, int id) {
		     	                write();
		     	   	        	Toast.makeText(getApplicationContext(), "Le jeu a été sauvegardé!", Toast.LENGTH_SHORT).show();
	     	                }})
	     	            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
	     	                public void onClick(DialogInterface dialog, int id) {
	     	                    dialog.cancel();}});
	     	    AlertDialog alert = builder.create();
	     	    alert.show();
	        	return true;
	    }
	    else if(id==R.id.load){
	            System.out.println("Load fait");
	            read();  
	            return true;
	        } 	
	    else if(id==R.id.map){
	            Intent myintent2 = new Intent(MapActivity.this,MapLocationActivity.class);
	            remplirIntentCombat(myintent2);
	            Toast.makeText(this, "Voici la map du jeu!", Toast.LENGTH_SHORT).show();
	            return true;
	            }
	    else if(id==R.id.monPokemon){
	            Intent myintent2 = new Intent(MapActivity.this,MonPokemon.class);
	            remplirIntentCombat(myintent2);
	            Toast.makeText(this, "Voici ton pokemon!", Toast.LENGTH_SHORT).show();
	            return true;
	            }     
	    else if(id==R.id.close_app){
	            	onBackPressed();
	            	return true;
	            }
	    return true; 
	}
	
	
	
	
	/**
	 * permet d'initialiser l'écran > appel toutes les méthodes de construction de la carte 
	 * dans la classe MapJeu
	 */
	public void initialiserMap(){
		mapJeu = (MapJeu)findViewById(R.id.mapJeu1);
		mapJeu.setPersonnage(personnage);
		mapJeu.initialiserTableaux();
		mapJeu.setPokemonDresseur(pokemonDresseur);
		mapJeu.getPersonnage().setX(x);
		mapJeu.getPersonnage().setY(y);
		mapJeu.invalidate();
	}
	
	
	
	
	/**
	 * permet de charger l'intent dans le cas où l'on doit lancer une énigme
	 */
	public void remplirIntentEnigme(){
		chargerEnigme();
    	Intent myintent2 = new Intent(MapActivity.this,LanceEnigmeTest.class); 
    	remplirIntent(myintent2);
    	myintent2.putExtra("direction", direction);
    	pokemonDresseur.setCompteur(0);
    	// seulement si on rentre dans le donjon (vérifié avec le booléen, on envoie le compteur)
    	if(pokemonDresseur.isDonjonAccessible()){myintent2.putExtra("compteur", pokemonDresseur.getCompteur());}
    	startActivity(myintent2);
	}
	
	/**
	 * permet de charger l'intent dans le cas où l'on doit lancer un combat
	 * @param myintent2 l'intent dans laquelle on va (un combat)
	 */
	public void remplirIntentCombat(Intent myintent2){
		remplirIntent(myintent2);
        myintent2.putExtra("pokAtt", pokemonAttaquant);
        startActivity(myintent2);
	}
	
	/**
	 * permet de réduire les redondances dans les deux chargements d'intent.
	 * @param myintent2 l'intent dans laquelle on va (un combat)
	 */
	public void remplirIntent(Intent myintent2){
		x=mapJeu.getPersonnage().getX(); 
    	y=mapJeu.getPersonnage().getY();
    	myintent2.putExtra("x",x);
    	myintent2.putExtra("y",y);
    	myintent2.putExtra("pokeDresseur", pokemonDresseur);
    	myintent2.putExtra("enigme", enigme);
    	myintent2.putExtra("coord", coord);
	}
	
	
	
	
	

	@Override
	/**
	 * action performed du bouton back
	 */
	public void onBackPressed() {
		//super.onBackPressed();
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);

	    builder.setMessage("Tu veux vraiment sortir?")
	            .setCancelable(false)
	            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	final Intent intent = new Intent(getBaseContext(), InsamonActivity.class);
	                	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                	startActivity(intent);
	                }
	            })
	            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	    AlertDialog alert = builder.create();
	    alert.show();
	}
	
	
	/**
	 * Ce methode sert à sauvegarder les coordonnés de la map et les donnés du pokemon dresseur dans les preferences de l'android
	 * l'information reste la jusqu'à que l'application soit désinstallé.
	 */
	public void write(){
		SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = preferences.edit();
		 
		 //On prend le pokemon et ses attributs
		 pokemonDresseur=(Pokemon)getIntent().getSerializableExtra("pokeDresseur");
		 int lvlPokemon= pokemonDresseur.getLvl();
		 int pdvPokemon= pokemonDresseur.getPdv();
		 int expPokemon=pokemonDresseur.getpExperience();
		 int attaquePokemon=pokemonDresseur.getPAttaque();
		 int defensePokemon=pokemonDresseur.getpDefense();
		 int pdvMax=pokemonDresseur.getPointsDeVieMax();
		 String nomPokemon = pokemonDresseur.getNomPokemon();
		
		 //On garde les coordonnés des enigmes résolues
		 storeIntArray();
		 
		 //On écrit les attributs du pokemon et la position du personnage dans les preferences du portable
		 editor.putString("Nom Pokemon", nomPokemon);
		 editor.putInt("Level Pokemon",lvlPokemon);
		 editor.putInt("Exp Pokemon",expPokemon);
		 editor.putInt("Pdv Pokemon",pdvPokemon);
		 editor.putInt("PdvMax Pokemon",pdvMax);
		 editor.putInt("Attaque Pokemon",attaquePokemon);
		 editor.putInt("Defense Pokemon",defensePokemon);
		 editor.putInt("Position X",mapJeu.getPersonnage().getX());
		 editor.putInt("Position Y",mapJeu.getPersonnage().getY());
		 editor.commit();
		 System.out.println(x+" "+y);
		 
	}
	
	/**
	 * Cette méthode sert à lire les donnés qu'on a stocké dans les preferences du portable
	 */
	public void read(){
		
		SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String nomPokemon = preferences.getString("Nom Pokemon", null);
		int lvlPokemon= preferences.getInt("Level Pokemon", 0);
		int expPokemon= preferences.getInt("Exp Pokemon", 0);
		int pdvPokemon= preferences.getInt("Pdv Pokemon", 0);
		int attaquePokemon= preferences.getInt("Attaque Pokemon", 0);
		int defensePokemon= preferences.getInt("Defense Pokemon", 0);
		int pdvMax= preferences.getInt("PdvMax Pokemon", 0);
		int jeuPosX= preferences.getInt("Position X", 0);
		int jeuPosY= preferences.getInt("Position Y", 0);
		
		//On charge le pokemon
		if(nomPokemon!= null){  // On verifie si on a une sauvegarde pour ne pas charger une quelque chose qui n'existe pas
			
		loadPok(nomPokemon);
		mapJeu.setPokemonDresseur(pokemonDresseur);
		pokemonDresseur.setpExperience(expPokemon);
		pokemonDresseur.setPdv(pdvPokemon); 
		pokemonDresseur.setLvl(lvlPokemon);
		pokemonDresseur.setPointsDeVieMax(pdvMax);
		pokemonDresseur.setpAttaque(attaquePokemon);
		pokemonDresseur.setpDefense(defensePokemon);
		getIntent().putExtra("pokeDresseur",pokemonDresseur);
		
		//On charge les coordonnes
		mapJeu.getPersonnage().setX(jeuPosX);
		mapJeu.getPersonnage().setY(jeuPosY);
		
		// On efface tous les coordonnes qu'on avait dans la liste, pour ne pas avoir des coordonnes dupliqués..
		coord.clear(); 
		
		// On charge les enigmes resolues
		getFromPrefs();
		
		System.out.println(jeuPosX+"+"+jeuPosY+"+"+nomPokemon+"+"+pdvPokemon+"+"+lvlPokemon);
		Toast.makeText(this, "L'ancien jeu a été chargé!", Toast.LENGTH_SHORT).show();
		mapJeu.invalidate();
		
		}else if(nomPokemon==null){ // si on la pas encore fait une sauvegarde
			Toast.makeText(this, "L'ancien jeu n'a pas pu été chargé!", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * Cette méthode charge le pokemon correspondant au String qu'on a sauvegardé
	 * @param nomPokemon c'est le nom du pokemon qui etait stocké dans les preferences
	 */
	public void loadPok(String nomPokemon){
		
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
        
	}
	
	/**
	 * On prend l'array list des coordonnés des enigmes, on transforme en tableau d'entiers et on les écrit dans les preferences
	 */
	public void storeIntArray(){
		
		int[] intArray = new int[coord.size()];
		for (int i = 0; i < coord.size(); i++) {
		intArray[i] = coord.get(i);
		}
	    SharedPreferences.Editor edit= getApplicationContext().getSharedPreferences("NAME", getApplicationContext().MODE_PRIVATE).edit();
	    edit.putInt("Count", intArray.length);
	    int count = 0;
	    for (int i: intArray){
	        edit.putInt("IntValue_" + count++, i);
	    }
	    edit.commit();
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
	    enleverEnigme();
	}
	
	/**
	 * Cette méthode permet d'enlever les enigmes qui ont été resolues, les énigmes sont tout le temps 
	 * redessinnées par un bout de chemin, sinon tp compliqué.
	 */
	public void enleverEnigme(){
			if(coord.size()!=0){
				for(int k=0;k<coord.size();k=k+2){ // on commence a 1 pour éviter la valeur donnant le nombre d'énigmes résolues.
						mapJeu.mapElements[coord.get(k)][coord.get(k+1)]=new Path(); // on sait que l'élément central est 9/4 donc je rajoute x et y pour le retrouver dans le tableau initial
				}
				mapJeu.invalidate(); // on actualise la map pour faire disparaitre les case d'énigmes
			}
	}
	
	/**
	 * permet de choisir l'intent à lancer en fonction de l'élément rencontré.
	 */
	public void testElement(){
		
//>				// l'élement est-il un combat?
		if (mapJeu.isCombat()){
    		pokemonAttaquant=mapJeu.getPokemonAttaquant();
    		Intent myintent2 = new Intent(MapActivity.this,CombatActivity.class);
       	    remplirIntentCombat(myintent2);
       	    
//>     	    //Une énigme?
        }else if(mapJeu.isEnigme()){ 
        	/* si oui, il faut vérifier que l'énigme n'est pas une case du donjon, ou alors
        	 * , il faut que le donjon soit débloqué
        	 * 
        	 */
//>>        	// le donjon ne se débloque pas car il manque des énigmes à résoudre.
        	if(mapJeu.isDonjon() && !pokemonDresseur.isDonjonAccessible()){
        		System.out.println("donjon bloqué");	
        		Toast.makeText(getApplicationContext(), "Cette porte ne semble pas s'ouvrir. En regardant de plus près, on aperçoit des écritures que vous ne semblez pas savoir lire. Peut-être faudra-t-il se cultiver d'avantage...", Toast.LENGTH_LONG).show();
        		mapJeu.deplacerBas();
        	}
        
//>>        	// l'énigme rencontrée est celle du donjon, et assez d'énigmes sont résolues
        	else if(mapJeu.isDonjon() && (pokemonDresseur.isDonjonAccessible())){
        		// on rentre dans le donjon
        		pokemonDresseur.setDonjonEntre(true);
        		Toast.makeText(getApplicationContext(), "Le long périble que vous avez traversé a fait de vous un dresseur respectable. Vous êtes digne d'entrer dans le donjon de la connaissance.", Toast.LENGTH_SHORT).show();
        		remplirIntentEnigme();
        	}
        	
//>>        	// l'énigme rencontrée n'a pas les coordonnées de l'énigme du donjon
        	else {remplirIntentEnigme();}
        		
//>    			//une case soin?
        }else if(mapJeu.isEstSoin()){
        	Toast.makeText(getApplicationContext(), "Ton pokemon a été soigné!", Toast.LENGTH_SHORT).show();
    		pokemonDresseur=mapJeu.getPokemonDresseur();// on actualise au cas ou il a récupéré sa vie
        }
		    
		
	}
	
	
	/**
	 * Permet de créer un bonus après donjon.
	 * Des pokemons legendaires sont ajoutés.
	 */
	public void remplirPokLegendaires(){
		if(pokemonDresseur.isDonjonAccessible()&& pokemonDresseur.getCompteur()==10){
		System.out.println(""+pokemonDresseur.getCompteur());
		mapJeu.mapElements[38][48]=new HerbeLeg();
		}
	}
	
	
	/**
	 * il faut réinitialiser car sinon il peut garder en mémoire le fait que les booleans soient à true. (on a pas redémarré l'activity).
	 */
	public void putBooleanFalse(){
		mapJeu.setEstSoin(false);
    	mapJeu.setDonjon(false); 
    	mapJeu.setEnigme(false);
	}
}
	

	
