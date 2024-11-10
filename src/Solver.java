import composite.*;
import rules.Context;
import rules.DR0;
import rules.DR1;
import rules.DR2;
import rules.DR3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

//PROGRAMME ECRIT PAR FLORENT BELOT ET ADAM MIR-SADJADI

//DERNIERES CHOSES A FAIRE ?
//Mettre les print sous forme d'assertion ou qque chose comme ça de désactivable et activable
//Faire fonction de test avec les solutions sous forme d'assert
//Mettre UML à jour
//Commenter pour la doc
//Implementer visiteur
//Cleaner code
//restructurer packages ? (sous-repertoire memento dans composite par ex, renommer rules en strategy... GridSnapshot)
//Implementer iterator (juste la fonction qui retourne lignes, carrés et colonnes, pas compliqué) et s'en servir dans le builder et la fonction makeSnapshot de Grid
//Passer tableau valeurs possibles en tableau de booléens

public class Solver {

    private static final boolean printToFile = false; //Change to true if you want to get the answer in a .txt file

    /**
     * MODELE DOC Doxygen (voir fonction main dans le html généré dans doc/)
     * Returns an Image object that can then be painted on the screen.
     * The url argument must specify an absolute <a href="#{@link}">{@link URL}</a>. The name
     * argument is a specifier that is relative to the url argument.
     * <p>
     * This method always returns immediately, whether or not the
     * image exists. When this applet attempts to draw the image on
     * the screen, the data will be loaded. The graphics primitives
     * that draw the image will incrementally paint on the screen.
     *
     * @param  args  an absolute URL giving the base location of the image
     * @return      the image at the specified URL
     * @see         Solver
     */
    public static void main(String[] args) throws FileNotFoundException {
        args = new String[]{"/home/florent/Documents/Master/Software_eng/Sudoku_Solver-main/ressources/grille_tres_difficile1.txt"};
        try {
            for (int k = 0; k < args.length; k++) {//Chaque argument est un fichier .txt qui contient une grille
                System.out.println("--------------------------------------");
                System.out.println(args[k]);

                //BUILD

                final Scanner sc = new Scanner(new File(args[k]));
                Builder builder = new Builder(sc);
                Grid grid = builder.buildGrid();
                sc.close();

                System.out.println("\nCells to fill " + grid.getCellsToFill());
                System.out.println(grid);

                //BOUCLE "Chain of responsibility" ? :

                Context ctx = new Context(new DR0(), grid);
                ctx.solve();

                ctx.setStrategy(new DR1());
                ctx.solve();

                if (grid.getCellsToFill() == 0) {
                    System.out.println(grid);
                    System.out.println("Sudoku résolu en difficulté facile.");
                    System.out.println("Grille valide : " + grid.checkValidity());
                    if(printToFile){
                        printSolutionToFile(args[k], grid);
                    }
                    continue;
                }

                ctx.setStrategy(new DR2());
                ctx.solve();

                if (grid.getCellsToFill() == 0) {
                    System.out.println(grid);
                    System.out.println("Sudoku résolu en difficulté moyenne.");
                    System.out.println("Grille valide : " + grid.checkValidity());
                    if(printToFile){
                        printSolutionToFile(args[k], grid);
                    }
                    continue;
                }

                ctx.setStrategy(new DR3());
                ctx.solve();

                if (grid.getCellsToFill() == 0) {
                    System.out.println(grid);
                    System.out.println("Sudoku résolu en difficulté difficile.");
                    System.out.println("Grille valide : " + grid.checkValidity());
                    if(printToFile){
                        printSolutionToFile(args[k], grid);
                    }
                    continue;
                }


                System.out.println("TRES DIFFICILE.____________________________________________________");

                Memento memento = grid.makeSnapshot();

                //Implémenter la demande à l'utilisateur.
                Scanner saisie = new Scanner(System.in);
                while (grid.getCellsToFill() > 0) {

                    if(grid.isWrong()== true){
                        System.err.println("HELLOCertaines cellules vides n'ont plus de valeur possible, une mauvaise valeur à été rentrée.");
                        System.err.println("Appuyez sur une touche pour recharger l'état de la grille avant la denière saisie manuelle ou quittez le programme avec Ctrl+C.");
                        grid.restore(memento);
                        saisie.next();
                    }

                    System.out.println(grid);

                    int x;
                    int y;
                    System.out.println("Entrez les coordonnées de la cellule vide que vous voulez remplir (x y) : ");
                    x = Solver.getInt(saisie);
                    y = Solver.getInt(saisie);
                    while ((0 > x || x > 8) || (0 > y || y > 8) || grid.get(x, y).getValue() != 0) {
                        System.err.println("Ces coordonnées ne corresponde pas à une cellule vide. Entrez les coordonnées de la cellule vide que vous voulez remplir (x y) : ");
                        x = saisie.nextInt();
                        y = saisie.nextInt();
                    }

                    /*if (grid.get(x, y) instanceof EmptyCell cell && cell.numberPossibleValue() == 0) {
                        System.out.println("isWrong : " + grid.isWrong());
                        System.err.println("Cette cellule n'a pas de valeur possible, une mauvaise valeur à été rentré veuillez relancer le solver.");
                        System.err.println("Appuyez sur une touche pour recharger l'état de la grille avant la première saisie manuelle ou quittez le programme avec Ctrl+C.");
                        grid.restore(memento);
                        saisie.next();
                    }*/

                    //else {
                    System.out.print("Les valeurs possible pour cette cellule sont : ");
                    for (int i = 0; i < 9; i++) {
                        if (grid.get(x, y).checkPossibleValue(i + 1)) {
                            System.out.print(i + 1 + " ");
                        }
                    }
                    System.out.println();
                    System.out.println("Entrez la valeur que vous voulez mettre dans cette cellule : ");
                    int value = Solver.getInt(saisie);
                    while (value < 1 || value > 9 || !grid.get(x, y).checkPossibleValue(value)) {
                        System.out.println("La valeur doit être comprise entre 1 et 9 et être une valeur possible pour la cellule. Entrez la valeur que vous voulez mettre dans cette cellule : ");
                        value = Solver.getInt(saisie);
                    }

                    Cell c_new = new Cell(x, y, value);
                    grid.set(c_new, x, y);
                    grid.setCellsToFill(grid.getCellsToFill() - 1);
                    ctx.setStrategy(new DR0());
                    ctx.solve(); //Actualise les cases qui doivent l'être par le nouvel ajout

                    ctx.setStrategy(new DR3()); //On repasse sur la stratégie la plus forte
                    ctx.solve();

                    if(grid.isWrong()== true){
                        System.err.println("Certaines cellules vides n'ont plus de valeur possible, une mauvaise valeur à été rentrée.");
                        System.err.println("Appuyez sur une touche pour recharger l'état de la grille avant la denière saisie manuelle ou quittez le programme avec Ctrl+C.");
                        grid.restore(memento);
                        saisie.next();
                     }
                    else{
                        memento = grid.makeSnapshot(); //On refait une copie pour le prochain reload
                    }
                    //}
                }
                saisie.close();

                System.out.println(grid);
                System.out.println("Sudoku résolu avec une difficulté très difficiles.");
                System.out.println("Grille valide : " + grid.checkValidity());
                if(printToFile){
                    printSolutionToFile(args[k], grid);
                }
            }
            return;
        }
        catch(FileNotFoundException e){
            System.err.println("ERREUR : Fichier d'entrée non trouvé.");
            System.err.println("Exemple d'utilisation : ");
            System.err.println("./Solver grille1.txt grille2.txt");
            System.err.println("Veuillez relancer le programme avec des fichiers valides");
            e.printStackTrace();
        }
    }

    /**
     * Fonction utilitaire pour recevoir un entier depuis l'entrée standard
     *
     * @param  sc   Un Scanner ouvert sur l'entrée standard
     * @return      Un entier saisi par l'utilisateur
     */
    private static int getInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Veuillez entrer un entier.");
            sc.next();
        }
        return sc.nextInt();
    }
    private static void printSolutionToFile(String sourceName, Grid grid){
        try {
            String name = sourceName.replace(".txt", "Answer.txt");
            File outputFile = new File(name);
            if (outputFile.createNewFile()) {
                System.out.println("File created: " + outputFile.getName());
            } else {
                System.err.println("Le fichier existe déjà");
            }
            FileWriter myWriter = new FileWriter(name);
            myWriter.write(grid.toCSV());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e) {
            System.err.println("Impossible de créer ou modifier le fichier");
            e.printStackTrace();
        }
        return;
    }
}