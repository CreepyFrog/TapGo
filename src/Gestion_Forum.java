/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commentaire;
import entities.Publication;
import entities.Reaction;
import entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.CommentaireService;
import services.PublicationService;
import services.ReactionService;

/**All Files	
 *
 * @author Chikaaa
 */
public class Gestion_Forum extends Application{
    
@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ForumInterface.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tap & Go");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
//   public static void main(String[] args) {
        
//     ************************************************************    *******************************************************
//PublicationService ps=new PublicationService();
//          Publication p2=new Publication("New eye",77,new User(36));
//          ps.addPublication(p2);
//        Publication p3=new Publication(8,"Wanna See the beauty?",120);     DONE PUBLICATION !!
        
//       ps.addPublication(p1);
//        ps.modifierPublication(p3);
////        ps.supprimerPublication(4);
//          System.out.println(ps.afficherPublication());
//                  ps.modifierPublication(new Publication(9,"Chikaaaa",778));

//*************************************************************************************************************************


//        **********************************************************************************************************************
//
//////////////////////        ReactionService rs=new ReactionService();
////////////////////////        Reaction r1=new Reaction(98, new User(35), new Publication(9));  DONE REACTION !!
//////////////////////////        Reaction r2=new Reaction(2,3,36);
//////////////////////////        
//////////////////////////        rs.addReaction(r1);
////////////////////////////       rs.addReaction(new Reaction(55,9,35));
//////////////////////////       rs.supprimerReaction(12);
//////////////////////       rs.modifierReaction(new Reaction(10,1555));
////////////////////////       System.out.println(rs.afficherReactions());
////////////////////////*********************************************************************************************************


//CommentaireService CS =new CommentaireService();
//System.out.println(CS.afficherCommentaire());
//Commentaire c1=new Commentaire("Guyss",new User(35),new Publication(6));
////CS.addCommentaire(c1);
//CS.supprimerCommentaire(6);
//CS.modifierCommentaire(new Commentaire(1,"Chika has done the cruds with the right relations"));


   
   }
    
//}
