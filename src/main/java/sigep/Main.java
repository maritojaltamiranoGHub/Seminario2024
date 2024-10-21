/*
 * 
 * 
 */
package main.java.sigep;

import main.java.sigep.views.Principal;

/**
 *
 * @author Marito
 */
public class Main {
    
    public static void main(String[] args) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }
    
}
