package com.arbre.vues;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.arbre.arbreRN.ArbreRougeNoir;
import com.arbre.arbreRN.Data;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Principal extends Application {

	BorderPane brd = new BorderPane();
	TextField valnd=new TextField();
	ObservableList<String> options = 
	        FXCollections.observableArrayList(
	            "Ajouter",
	            "Supprimer",
	            "Rechercher"
	        );
	ComboBox comboBox = new ComboBox(options);	
	Button btn=new Button("valider");
	
	Button btng=new Button("Génerer arbre");
	Button btnr=new Button("vider arbre");
	ArbreRougeNoir a;
	Dessinarbre canvas;
	int addordelorsear=1;
	Pane panebott;
	
	private Pane createContentTop(){
		HBox pane=new HBox();
		pane.setPrefHeight(150);		
		pane.setStyle("-fx-background-color:#129BC4;");
		
		HBox logo=new HBox();
		
		ImageView imgview=new ImageView();
		FileInputStream file=null;		
		try {
		file = new FileInputStream("photos/Logo.png");
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		Image img =new Image(file);
		imgview.setImage(img);
		imgview.setFitHeight(130);
		imgview.setFitWidth(130);
		logo.setPadding(new Insets(10));
		logo.getChildren().add(imgview);
		
		HBox form1=new HBox();
		form1.setMaxHeight(80);
		form1.setPrefWidth(400);
		form1.setStyle("-fx-background-color:#129BC4;");
		form1.setSpacing(20);
		form1.setEffect(new DropShadow(2d, 0d, +2d, Color.RED));
		
        HBox.setMargin(form1, new Insets(35,0,0,80));
        form1.setAlignment(Pos.CENTER);
        valnd.setPrefWidth(140);
		valnd.setPromptText("Valeur");
		
		
		comboBox.setValue("Ajouter");
		form1.getChildren().add(comboBox);
		form1.getChildren().add(valnd);
		comboBox.setStyle("-fx-base:red;");
		comboBox.valueProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String t1) {
	        	if(t1=="Ajouter") {
	        		addordelorsear=1;
	        	}
	        	else if(t1=="Supprimer"){
					addordelorsear=2;
				}
				else {
					addordelorsear=3;
				}
	        }    
	    });		
		
		
		btn.setStyle("-fx-base:red;");
		form1.getChildren().add(btn);
		
		HBox form2=new HBox();
		form2.setMaxHeight(80);
		form2.setPrefWidth(350);
		form2.setStyle("-fx-background-color:#129BC4;");
		form2.setSpacing(20);
		form2.setEffect(new DropShadow(2d, 0d, +2d, Color.RED));

		
        HBox.setMargin(form2, new Insets(35,0,0,80));
        form2.setAlignment(Pos.CENTER);
        valnd.setPrefWidth(140);
		valnd.setPromptText("Valeur");
		valnd.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")){
                	valnd.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
		
		btng.setStyle("-fx-base:red;");
				
        btng.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				  
				if(ArbreRougeNoir.list!=null) {
				for(Data o:ArbreRougeNoir.list) {
					a.supprimer(new Data(o.getValue()));
				}
								}
				 a.ajout(new Data(10));
				 a.ajout(new Data(85));
				 a.ajout(new Data(15));
				 a.ajout(new Data(70));
				 a.ajout(new Data(20));
				 a.ajout(new Data(60));
				 a.ajout(new Data(5));
				 a.ajout(new Data(12));
				 a.ajout(new Data(11));
									
				 canvas.paint(canvas.getGraphicsContext2D());
                 			
			}
		});
		
		
		btnr.setStyle("-fx-base: red;");
		
		
		
		
		btnr.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				 
				reset();
				
			}
		});
		
		form2.getChildren().add(btng);
		form2.getChildren().add(btnr);
		
		pane.getChildren().addAll(logo,form1,form2);
		
		return pane;
		}
	
	private void reset() {
		brd.setCenter(createContentBottom());
	}
	
	private Pane createContentBottom(){
	
		panebott=new Pane();
		panebott.setMaxHeight(550);
		panebott.setMaxWidth(1200); 
		
		a = new ArbreRougeNoir();
        
		canvas = new Dessinarbre(a, panebott.getMaxWidth(),panebott.getMaxHeight());
		
		
		
		canvas.widthProperty().bind(panebott.widthProperty());
        canvas.heightProperty().bind(panebott.heightProperty());
		
                
        panebott.setStyle("-fx-background-color:white;");
		
        panebott.getChildren().add(canvas);
		
		initialiser(panebott);
                   
		return panebott;
		}
	
	
	public void initialiser(Pane pane) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			 
			@Override
			public void handle(ActionEvent arg0) {
				
				
				switch (addordelorsear) {
	            case 1:
				
				try {
                    if (valnd.getText().trim().length() != 0) {
                        a.ajout(new Data(Integer.parseInt(valnd.getText().trim())));
                                                     
                        canvas.paint(canvas.getGraphicsContext2D());
                        
                        
                        
                        valnd.setText("");
                        valnd.requestFocus();
                        
                    } else {
                    	 Alert alert = new Alert(AlertType.INFORMATION, "Merci de saisir la valeur à jouter", ButtonType.OK);
                         alert.showAndWait();
                        valnd.requestFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                };break;
	            case 2:
					
	            	try {
	                    if (valnd.getText().trim().length() != 0) {
	                        if (!canvas.rechercher(canvas.getGraphicsContext2D(), Integer.parseInt(valnd.getText().trim()))) {
	                            
	                            Alert alert = new Alert(AlertType.INFORMATION, "Cette valeur n'existe pas", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
	                            alert.showAndWait();
	                            
	                        }
	                       a.supprimer(new Data(Integer.parseInt(valnd.getText().trim())));
	                       canvas.paint(canvas.getGraphicsContext2D());
	                       valnd.setText("");
	                       valnd.requestFocus();
	                    } else {
	                        Alert alert = new Alert(AlertType.INFORMATION, "Merci d'entrer une valeur a supprimer ", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
	                         alert.showAndWait();
	                        valnd.requestFocus();
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                };break;
	            case 3:
					
	            	try {
	                    if (valnd.getText().trim().length() != 0) {
	                        if (!canvas.rechercher(canvas.getGraphicsContext2D(), Integer.parseInt(valnd.getText().trim()))) {
	                            
	                            Alert alert = new Alert(AlertType.INFORMATION, "Cette valeur n'existe pas", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
	                            alert.showAndWait();
	                            
	                        }
	                        	                                          
	                       valnd.setText("");
	                       valnd.requestFocus();
	                    } else {
	                        Alert alert = new Alert(AlertType.INFORMATION, "Merci d'entrer une valeur a rechercher ", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
	                         alert.showAndWait();
	                        valnd.requestFocus();
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                };break;
			}
			}
		});
	}

	@Override
	public void start(Stage window) throws Exception {
	
		window.setWidth(1200);
		window.setHeight(700);
		window.getIcons().add(new Image(new File("photos/Logo.png").toURI().toString()));
		window.setTitle("Arbre Rouge Noir");
		brd.setTop(createContentTop());
		brd.setCenter(createContentBottom());
		Scene scene = new Scene(brd, 200, 300, Color.WHITE);
		window.setScene(scene);
		window.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
