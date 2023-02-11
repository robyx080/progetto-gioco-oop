package model;

import javafx.geometry.Insets;
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

public class SmallInfoLabel extends Label {    //classe SmallInfoLabel che eredità tutti i metodi della classe Label

    private final static String FONT_PATH = "src/model/resources/font.ttf";    //percorso del font che utilizzeremo per la scrittura all'interno del gioco
    private final static String BACKGROUND_IMAGE = "model/resources/red_info_label.png";    //percorso dell'immagine di sfondo del Label

    public SmallInfoLabel(String text){        //costruttore SmallInfoLabel
        setPrefHeight(50);                     //settiamo l'altezza  della label
        setPrefWidth(130);                     //settiamo la larghezza  della label
        //settiamo lo sfonfo
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE,130,50,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));

        setAlignment(Pos.CENTER_LEFT);         //centriamo il testo al centro della label
        setPadding(new Insets(10,10,10,10));  //il testo avrà 10 di spazio per ogni lato
        setLabelFont();                        //richiamiamo il metodo setLabelFont per utilizzare il font corretto
        setText(text);                         //settiamo il testo da mettere nella label
    }

    private void setLabelFont(){               //metodo setLabelFont
        try{
            setFont(Font.loadFont(new FileInputStream((FONT_PATH)),15));   //settiamo il font

        }catch (FileNotFoundException e){      //se ci sono errori li rivela
            setFont(Font.font("verdana",15));   //settiamo un altro font nel caso di errori
        }
    }

}
