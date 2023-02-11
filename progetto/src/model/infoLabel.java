package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class infoLabel extends Label {          //classe infoLabel che eredità tutti i metodi della classe Label

    private final static String FONT_PATH = "src/model/resources/font.ttf";      //percorso del font che utilizzeremo per la scrittura all'interno del gioco
    private final static String BACKGROUND_IMAGE = "model/resources/yellow_button13.png";    //percorso dell'immagine di sfondo del Label

    public infoLabel(String text){               //costruttore infoLabel
        setPrefHeight(49);                       //settiamo l'altezza della label
        setPrefWidth(380);                       //settiamo la larghezza della label
        setText(text);                           //settiamo il testo da mettere nella label
        setWrapText(true);                       //se il testo fuoriesce dalla label questo metodo lo fa andare a capo
        setLabelFont();                          //richiamiamo il metodo setLabelFont per utilizzare il font corretto
        setAlignment(Pos.CENTER);                //centriamo il testo al centro della label

        //settiamo lo sfonfo
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE,380,49,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
    }

    private void setLabelFont(){                 //metodo setLabelFont
        try {
            setFont(Font.loadFont(new FileInputStream((FONT_PATH)), 20));   //settiamo il font
        }catch (FileNotFoundException e){        //se ci sono errori li rivela
            setFont(Font.font("Verdana",20));  //settiamo un altro font nel caso di errori
        }
    }

}
