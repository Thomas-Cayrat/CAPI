package fr.icom.info.m1.balleauprisonnier_mvn;


import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {
	
	/** Joueurs */
	public Player [] joueurs = new Player[6];

	/** Balle */
	Projectile [] ball = new Projectile[10];
	/** Couleurs possibles */
	String[] colorMap = new String[] {"red", "blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();
    ArrayList teamRed = new ArrayList();
	ArrayList teamBlue = new ArrayList();

    final GraphicsContext gc;
    final int width;
    final int height;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     * 
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Field(Scene scene, int w, int h) 
	{
		super(w, h); 
		width = w;
		height = h;
		
		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);
		
        gc = this.getGraphicsContext2D();
        
        /** On initialise le terrain de jeu */

		joueurs[0] = new Player(gc, colorMap[0], w/2, h-100, "bottom");
    	joueurs[0].display();
		joueurs[1] = new Ordi(gc, colorMap[0], w/4, h-100, "bottom");
		joueurs[1].display();
		joueurs[2] = new Ordi(gc, colorMap[0], 3*w/4, h-100, "bottom");
		joueurs[2].display();
		teamRed.add(joueurs[0]);
		teamRed.add(joueurs[1]);
		teamRed.add(joueurs[2]);

		joueurs[3] = new Player(gc, colorMap[1], w/2, 20, "top");
		joueurs[3].display();
		joueurs[4] = new Ordi(gc, colorMap[1], w/4, 20, "top");
		joueurs[4].display();
		joueurs[5] = new Ordi(gc, colorMap[1], 3*w/4, 20, "top");
		joueurs[5].display();
		teamBlue.add(joueurs[3]);
		teamBlue.add(joueurs[4]);
		teamBlue.add(joueurs[5]);

		//ball[0] = new Projectile(gc,10,10,30,20);
		//ball[0].display();
	    /** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
	    this.setOnKeyPressed(
	    		new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            // only add once... prevent duplicates
	    	            if ( !input.contains(code) )
	    	                input.add( code );
	    	        }
	    	    });

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est relachee on l'enleve de la liste d'input
	     *   
	     */
	    this.setOnKeyReleased(
	    	    new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            input.remove( code );
	    	        }
	    	    });
	    
	    /** 
	     * 
	     * Boucle principale du jeu
	     * 
	     * handle() est appelee a chaque rafraichissement de frame
	     * soit environ 60 fois par seconde.
	     * 
	     */
	    new AnimationTimer() 
	    {
	        public void handle(long currentNanoTime)
	        {	 
	            // On nettoie le canvas a chaque frame
	            gc.setFill( Color.DARKSEAGREEN);
	            gc.fillRect(0, 0, width, height);
	        	
	            // Deplacement et affichage des joueurs
	        	for (int i = 0; i < joueurs.length; i++) 
	    	    {
	        		if (i==0 && input.contains("LEFT"))
	        		{
	        			joueurs[i].moveLeft();
						joueurs[i+4].moveLeft();
						joueurs[i+5].moveLeft();
	        		} 
	        		if (i==0 && input.contains("RIGHT")) 
	        		{
	        			joueurs[i].moveRight();
						joueurs[i+4].moveRight();
						joueurs[i+5].moveRight();
	        		}
	        		if (i==0 && input.contains("UP"))
	        		{
	        			joueurs[i].turnLeft();
	        		} 
	        		if (i==0 && input.contains("DOWN")) 
	        		{
	        			joueurs[i].turnRight();	        			
	        		}
					if (i==0 && input.contains("M")) {
						joueurs[i].shoot();
						joueurs[i + 4].shoot();
						joueurs[i + 5].shoot();
					}
	        		if (i==3 && input.contains("Q"))
	        		{
	        			joueurs[i].moveLeft();
						joueurs[i-1].moveLeft();
						joueurs[i-2].moveLeft();
	        		} 
	        		if (i==3 && input.contains("D"))
	        		{
	        			joueurs[i].moveRight();
						joueurs[i-1].moveRight();
						joueurs[i-2].moveRight();
					}
	        		if (i==3 && input.contains("Z"))
	        		{
	        			joueurs[i].turnLeft();
	        		} 
	        		if (i==3 && input.contains("S"))
	        		{
	        			joueurs[i].turnRight();	        			
	        		}
	        		if (i==3 && input.contains("SPACE")){
	        			joueurs[i].shoot();
						joueurs[i-1].shoot();
						joueurs[i-2].shoot();
						//Projectile ball = new Projectile(gc,1,1,20,20);
						ball[0] = new Projectile(gc,10,10,30,20);
						//System.out.println(ball.getX());

					}

					joueurs[i].stayInField(); //garde les joueurs dans le field
					joueurs[i].goodSideArrow(); //garde la fleche du bon côté
	        		
	        		joueurs[i].display();

	    	    }
				if (ball[0] != null){
					ball[0].display();
				}

				//System.out.println(joueurs[0].getAngle());
				//System.out.println(joueurs[3].getX());


	    	}

	     }.start(); // On lance la boucle de rafraichissement
	     
	}

	public Player[] getJoueurs() {
		return joueurs;
	}
}

