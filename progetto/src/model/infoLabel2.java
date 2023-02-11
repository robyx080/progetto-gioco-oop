package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class infoLabel2 extends Label {    //classe infoLabel2 che eredità tutti i metodi della classe Label

    private final static String FONT_PATH = "src/model/resources/font.ttf";    //percorso del font che utilizzeremo per la scrittura all'interno del gioco

    public infoLabel2(String text){        //costruttore infoLabel2
        setPrefHeight(380);                //settiamo l'altezza  della label
        setPrefWidth(380);                 //settiamo la larghezza  della label
        setText(text);                     //settiamo il testo da mettere nella label
        setWrapText(true);                 //se il testo fuoriesce dalla label questo metodo lo fa andare a capo
        setLabelFont();                    //richiamiamo il metodo setLabelFont per utilizzare il font corretto
        setAlignment(Pos.CENTER);          //centriamo il testo al centro della label
    }

    private void setLabelFont(){           //metodo setLabelFont
        try {
            setFont(Font.loadFont(new FileInputStream((FONT_PATH)), 20));   //settiamo il font
        }catch (FileNotFoundException e){  //se ci sono errori li rivela
            setFont(Font.font("Verdana",20));  //settiamo un altro font nel caso di errori
        }
    }
}
