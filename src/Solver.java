import composite.*;
import composite.cell.Cell;
import log.SudokuLogger;
import strategy.Context;
import strategy.rules.DR0;
import strategy.rules.DR1;
import strategy.rules.DR2;
import strategy.rules.DR3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;

//PROGRAMME ECRIT PAR FLORENT BELOT ET ADAM MIR-SADJADI

//DERNIERES CHOSES A FAIRE ?
//Mettre les print sous forme d'assertion ou qque chose comme ça de désactivable et activable
//Fonctions de tests ? Assertions ? (assert !grid.isWrong() par ex)
//Commenter pour la doc
//Implementer visiteur
//Cleaner code
//Passer tableau valeurs possibles en tableau de booléens
//Mettre doc en ligne
//Rendre le README propre

public class Solver {

    private static final boolean printToFile = false; //Change to true if you want to get the answer in a .txt file
    /**
     * Fonction principale du problème qui résout les grilles contenues dans les fichiers passée en arguments
     *
     * @param  args Un tableau d'URL relatives ou absolues vers les fichiers contenant les grilles à résoudre
     */
    public static void main(String[] args) throws FileNotFoundException {
        try {
            SudokuLogger.getLogger().setLevel(Level.WARNING); // Mettre en Level.INFO pour voir les détails de l'exécution du solver
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


                System.out.println("La grille est très difficile et nécessite l'intervention de l'utilisateur.");

                Memento memento = grid.makeSnapshot();

                //Implémenter la demande à l'utilisateur.
                Scanner saisie = new Scanner(System.in);
                while (grid.getCellsToFill() > 0) {

                    assert (!grid.isWrong());
                    System.out.println(grid);

                    int x;
                    int y;
                    System.out.println("Entrez les coordonnées de la cellule vide que vous voulez remplir : ");
                    System.out.println("\nSaisissez x : ");
                    x = Solver.getInt(saisie);
                    System.out.println("\nSaisissez y : ");
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
                        System.err.println("Appuyez sur Entrée pour recharger l'état de la grille avant la denière saisie manuelle ou quittez le programme avec Ctrl+C...");
                        memento = grid.restore(memento);
                        saisie.nextLine();
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
        int r = sc.nextInt();
        sc.nextLine(); // Pour retirer le retour à la ligne residuel après le nextInt.
        return r;
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
