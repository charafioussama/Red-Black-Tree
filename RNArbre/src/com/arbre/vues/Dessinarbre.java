package com.arbre.vues;

import com.arbre.arbreRN.ArbreRougeNoir;
import com.arbre.arbreRN.Noeud;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class Dessinarbre extends Canvas{

	
	ArbreRougeNoir a;
	double containerWidth, containerHeight;
    
	public Dessinarbre(ArbreRougeNoir a, double containerWidth, double containerHeight) {
		this.a = a;
		this.containerWidth = containerWidth;
		this.containerHeight = containerHeight;
	}
    
	 public void dessinerNoeud(GraphicsContext g, Noeud n) {
	        if (!n.getGauche().isSentinelle()) {
	            int nfg = n.getGauche().getNGauche();
	            g.setStroke(Color.BLACK);
	            g.strokeLine(n.getDessinX() + 17, n.getDessinY() + 17, n.getDessinX() - 30 * nfg + 17, n.getDessinY() + 50 + 17);
	            n.getGauche().setDessinX(n.getDessinX() - 30 * nfg);
	            n.getGauche().setDessinY(n.getDessinY() + 50);
	        }
	        if (!n.getDroit().isSentinelle()) {
	            int nfd = n.getDroit().getNDroit();
	            g.setStroke(Color.BLACK);
	            g.strokeLine(n.getDessinX() + 17, n.getDessinY() + 17, n.getDessinX() + 30 * nfd + 17, n.getDessinY() + 50 + 17);
	            n.getDroit().setDessinX(n.getDessinX() + 30 * nfd);
	            n.getDroit().setDessinY(n.getDessinY() + 50);
	        }
	        
	         
	        Stop[] stops1 = new Stop[] { new Stop(0, n.getCouleur()), 
	                new Stop(1, Color.GRAY)};
	            RadialGradient lg1 = new RadialGradient(0, 0, 0.5, 0.5, 0.8, true, 
	                    CycleMethod.NO_CYCLE, stops1);
	        
	        
	        g.setFill(lg1);
	        g.fillOval(n.getDessinX(), n.getDessinY(), 34, 34); 
	        g.setFill(Color.WHITE);
	        g.setStroke(Color.WHITE);
	        g.strokeText(n.getInfo().getValue() + "", 
	                n.getInfo().getValue() < 10 ? n.getDessinX() + 14 : 
	                         n.getInfo().getValue() < 100 ? n.getDessinX() + 10 : n.getDessinX() + 6, n.getDessinY() + 21);
	        
	        //------------------------------------------------------------------------------------------------------
            
	        Stop[] stops2 = new Stop[] { new Stop(0, Color.BLACK), 
	                new Stop(1, Color.GRAY)};
	            RadialGradient lg2 = new RadialGradient(0, 0, 0.5, 0.5, 0.8, true, 
	                    CycleMethod.NO_CYCLE, stops2);
	        
	        if (n.getGauche().isSentinelle() && n.getDroit().isSentinelle()) {
	        g.setStroke(lg2);
            g.strokeLine(n.getDessinX() + 14, n.getDessinY() + 33, n.getDessinX() - 42 + 40, n.getDessinY() + 40 + 15);
	        
            g.setFill(lg2);
	        g.fillRect(n.getDessinX()- 10, n.getDessinY()+52, 15, 15); 
            
            g.setStroke(lg2);
            g.strokeLine(n.getDessinX() + 14, n.getDessinY() + 33, n.getDessinX() + 14 + 17, n.getDessinY() + 40 + 15);
            
            g.setFill(lg2);
	        g.fillRect(n.getDessinX()+ 25, n.getDessinY()+52, 15, 15); 
	        }
	        
	        
	        else if (n.getGauche().isSentinelle()) {
		        g.setStroke(lg2);
	            g.strokeLine(n.getDessinX() + 14, n.getDessinY() + 33, n.getDessinX() - 42 + 40, n.getDessinY() + 40 + 15);
		        
	            g.setFill(lg2);
		        g.fillRect(n.getDessinX()- 10, n.getDessinY()+52, 15, 15); 

	        }
	        
	        
	        else if (n.getDroit().isSentinelle()) { 
            
	            g.setStroke(lg2);
	            g.strokeLine(n.getDessinX() + 14, n.getDessinY() + 33, n.getDessinX() + 14 + 17, n.getDessinY() + 40 + 15);
	            
	            g.setFill(lg2);
		        g.fillRect(n.getDessinX()+ 25, n.getDessinY()+52, 15, 15);
	        }
            
            
            //--------------------------------------------------------------------------------------------------------------
            
	        if (!n.getGauche().isSentinelle()) {
	            dessinerNoeud(g, n.getGauche());
	        }
	        if (!n.getDroit().isSentinelle()) {
	            dessinerNoeud(g, n.getDroit());
	        }
	    }
 
	    public void paint(GraphicsContext gc) {
	    	
	        Noeud n = a.getRacine();
	        n.setDessinX(550);
	        n.setDessinY(50);
	        gc.setFill(Color.WHITE);
	        gc.fillRect(0, 0, containerWidth, containerHeight);
	        if (!a.getRacine().isSentinelle())
	            dessinerNoeud(gc,n);
	        	        
	    }

	    public boolean rechercher(GraphicsContext g, int val) {
	        paint(g);
	    	Noeud n = a.rechercher(val);
	        if (n != null) {
	            g.setFill(Color.BLUE);
	            g.strokeOval(n.getDessinX() - 5, n.getDessinY() - 5, 44, 44);
	            return true;
	        } else {
	            return false;
	        }
	    }
	    public void reset(GraphicsContext gc) {
	    	 gc.setFill(Color.WHITE);
	    	 gc.fillRect(0, 0, containerWidth, containerHeight);
	    	 
	    	
	    }
	
}
