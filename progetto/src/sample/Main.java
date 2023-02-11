package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

public class Main extends Application {              //classe Main che eredità tutti i metodi della classe Application

    @Override
    public void start(Stage primaryStage) {          //costruttore start
        try{
            ViewManager manager = new ViewManager(); //creazione variabile ViewManager
            primaryStage = manager.getMainStage();   //richiamiamo il metodo getMainStage della classe ViewManager che ci permette di ottenere lo stage del menu
            primaryStage.show();                     //mostriamo lo stage
        } catch(Exception e){                        //se rileva errori stampa l'errore
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
