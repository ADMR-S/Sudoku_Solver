# Sudoku_Solver

Programme conçu et implémenté par Adam Mir-sadjadi et Florent Bélot

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Commandes Terminal :

-Compiler (depuis dossier Sudoku_Solver) :
javac -d bin/ src/*.java src/*/*.java


-Exécuter (Idem, depuis dossier Sudoku_Solver) :
java -cp bin/ Solver

Notes : 

La classe CellBase est plus une classe "Composant" que la classe Composant actuelle. On implémente bien un composite à mon sens (Produit = Cellule ou CelluleVide) mais les boîtes et les produits ne sont pas considérés de la même façon (Lignes/Colonnes/Squares n'ont rien en commun avec les Cell/EmptyCell), et ce serait de toute façon une erreur de le faire selon moi à moins que l'on y trouve un intérêt dans les Deduction Rules. A discuter.

Le Solver pourrait être le maillon principal d'une Chain of Responsibility entre les différentes DR et le joueur s'il doit entrer un chiffre manuellement.
