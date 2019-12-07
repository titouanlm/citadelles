# Citadelles - 2019-20
![pasted image 0](https://user-images.githubusercontent.com/54990083/70319708-3cba8480-1823-11ea-858d-9b204d29abaa.png)
#### Groupe
   "JTHS"
#### Auteurs 
   -   Julien N'DIAYE 
   -   Titouan LE MAO 
   -   Hamza AYOUB
   -   Sana DIBE  
#### Arborescence et structure du projet
   ```
   src
        main
              java
                    fr.unice.polytech.code
                          +--bots
                                Bot
                                BotAleatoire
                                BotFairPlay
                                BotTricheur
                          +--cartes
                                Bibliotheque
                                CarteCitadelles
                                CarteCitadellesAvecPouvoir
                                CarteCitadellesSansPouvoir
                                Cimitière
                                CouleurCarteCitadelles
                                CourDesMiracles
                                Donjon
                                Dracopert
                                EcoleDeMagie
                                Laboratoire
                                Manufacture
                                Observatoire
                                Universite
                          +--moteur
                                Moteur
                                Tour
                          +--personnages
                                Architecte
                                Assassin
                                Condottiere
                                Eveque
                                Magicien
                                Marchand
                                Personnage
                                Roi
                                Voleur
                          +--pioches
                                PiocheCartesCitadelles
                                PiocheCartesPersonnage
                          Affichage
                          Arbitre
                          Main
                          Ville
        test
              java
                    fr.unice.polytech.code
                          +--bots
                                BotAleatoireTest
                                BotFairPlayTest
                                BotTricheurTest
                          +--cartes
                                CarteCitadellesAvecPouvoirTest
                                CarteCitadellesTest
                          +--moteur
                                MoteurTest
                                TourTest
                          +--personnages
                                ArchitecteTest
                                AssassinTest
                                CondottiereTest
                                EvequeTest
                                MagicienTest
                                MarchandTest
                                PersonnageTest
                                RoiTest
                                VoleurTest
                          +--pioches
                                PiocheCartesCitadellesTest
                                PiocheCartesPersonnageTest
                          AffichageTest
                          ArbitreTest
                          VilleTest
      
   ```
#### État du projet 
Le projet est terminé
#### Descrption du projet
Chaque Bot est à la tête d'une ville qu'il doit développer pour la rendre plus riche et plus prestigieuse. Les
quartiers des villes sont représentés par des cartes Citadelles. Lorsqu'un Bot place le huitième quartier de sa ville, on finit le
tour et la partie est terminée.
Au début du jeu, chaque Bot reçoit quatre cartes Citadelles (cartes quartiers) et deux pièces d'or. Un Bot, tiré au sort, reçoit la carte « Couronne ».
###### Les cartes de personnage et leurs pouvoirs: 
Il y a 8 cartes de personnages :
   - L’Assassin
Il assassine un personnage de son choix. Le Bot qui a ce personnage ne jouera pas jusqu'au tour suivant.   
   - Le Voleur
Le Voleur peut voler le trésor du personnage de son choix. Il ne peut voler ni l'Assassin, ni un personnage assassiné. Le vol prendra effet au début du tour du personnage volé.
   - Le Magicien
A n'importe quel moment de son tour, il peut :
    • soit échanger tout son jeu de cartes contre le jeu d'un autre joueur de son choix (même s'il n'a pas de cartes en main il prend alors les cartes de l'autre joueur).
    • soit échanger un certain nombre de cartes de sa main contre le même nombre de cartes de la pioche. Les cartes défaussées sont mises sous la pioche.
   - Le Roi
Le roi prend la carte Couronne et choisira en premier son personnage au prochain tour. Chaque quartier jaune qu'il possède lui rapporte une pièce d'or.
   - L'Évêque
L'Évêque ne peut pas être attaqué par le Condottière. Chaque quartier bleu qu'il possède lui rapporte une pièce d'or.
   - Le Marchand
Le Marchand reçoit une pièce d'or en plus au début de son tour. Chaque quartier vert qu'il possède lui rapporte une pièce d'or.   
   - L'Architecte
L'Architecte pioche deux cartes quartier en plus. il peut bâtir jusqu'à trois quartiers.   
   - Le Condottiere
Le Condottiere peut détruire un quartier de son choix en payant e coût de construction du quartier moins un. Chaque quartier rouge qu'il possède lui rapporte une pièce d'or.   
###### Les cartes de quartier : 
Chaque quartier (CarteCitadelles) a un nom, une couleur, un numero et un coût de construction qui est représenté par l'attribut point.
On distingue 2 types de CarteCitadelles :
   - CarteCitadellesSansPouvoir : sont les quartiers qui ont une des couleurs suivantes : bleue, jaune, vert et rouge.
   - CarteCitadellesAvecPouvoir : sont les quartiers violets ==> Chaque quartier violet a un effet sur le jeu – en faveur du Bot dans la ville duquel il est construit.
###### Les pouvirs des quartier violets : 
   - La cour des miracles : Pour le décompte final des points, elle est considérée comme un quartier de la couleur choisie par le Bot. Ce dernier ne peut pas utiliser cette capacité si il a la cour des miracles au dernier tour de jeu.
   - Le Donjon : Il ne peut pas être détruit par le Condottière.
   - Le laboratoire : Une fois par tour, le Bot peut défausser d'une carte quartier de sa main et recevoir une pièce d'or en contrepartie					
   - Manufacture : Une fois par tour, le Bot peut payer trois pièces d'or pour piocher trois cartes.
   - Observatoire : Si le Bot choisit de piocher des cartes au début de son tour, il en piochez trois, en choisisse une et défausse les deux autres.
   - Cimetière : Lorsque le Condottière détruit un quartier, le Bot peut payer une pièce d'or pour le reprendre dans sa main. Il ne peut pas faire cela s'il est lui-même Condottiere.			
   - Bibliothèque : Si le Bot choisit de piocher des cartes au début de son tour, il en pioche deux et les conserve toutes les deux.
   - Ecole de magie : Pour la perception des revenus, l'école de magie est considérée comme un quartier de la couleur du choix du Bot, elle lui rapporte donc si il est, Roi, Evêque, Marchand ou Condottiere une pièce d'or.
   - Université : Coûte six pièces d'or à bâtir mais vaux huit points dans le décompte de fin de partie.
   - Dracoport : Coûte six pièces d'or à bâtir mais vaut huit points dans le décompte de fin de partie.
##### Modélisation
- Package bots :
![bots](https://user-images.githubusercontent.com/54990083/70319486-b140f380-1822-11ea-8d61-bd6ce7523347.png)
- Package cartes :
![cartes](https://user-images.githubusercontent.com/54990083/70319493-b4d47a80-1822-11ea-9553-abb1bd900ce7.png)
- Package personnages :
![personnages](https://user-images.githubusercontent.com/54990083/70319499-b9009800-1822-11ea-9106-9b16e16bec5e.png)
- Package moteur :
![moteur](https://user-images.githubusercontent.com/54990083/70319520-c28a0000-1822-11ea-8f67-da2c6ad8d9dc.png)
- Package pioches :
![pioches](https://user-images.githubusercontent.com/54990083/70319525-c453c380-1822-11ea-9159-15ce29bbb7b7.png)
- Classes Affichage, Ville et Arbitre :
![Affichage-ville-arbitre](https://user-images.githubusercontent.com/54990083/70319526-c61d8700-1822-11ea-8081-3b9859b1d9ff.png)
##### Décompte des points de victoire
- Coût de construction total des quartiers de la ville + 3 si la ville contient des quartiers des cinq couleurs différentes.
- Coût de construction total des quartiers de la ville  + 4 pour le premier joueur ayant posé son huitième quartier
- Coût de construction total des quartiers de la ville  + 2 pour les autres joueurs ayant huit quartiers.
#### Outils utilisés
 - IntelliJ IDEA 2019.2.2 (Ultimate Edition)
 - Java 11.0.4
 - JUnit5
 - Mockito
 #### Compilation et exécution du programme en ligne de commande
 ```
 user@Yxxx:~/ps5-projet2-1920-jths/src/main/java/fr/unice/polytech/code$ javac Main.java
 user@Yxxx:~/ps5-projet2-1920-jths/src/main/java/fr/unice/polytech/code$ java Main
Bot 1 :
Victoire(s) : 710 sur 1000 parties.
Soit 71.0% de victoires.
Nombre de points moyens par partie : 28.912

Bot 2 :
Victoire(s) : 134 sur 1000 parties.
Soit 13.4% de victoires.
Nombre de points moyens par partie : 19.522

Bot 3 :
Victoire(s) : 48 sur 1000 parties.
Soit 4.8% de victoires.
Nombre de points moyens par partie : 14.117

Bot 4 :
Victoire(s) : 54 sur 1000 parties.
Soit 5.4% de victoires.
Nombre de points moyens par partie : 14.191

Bot 5 :
Victoire(s) : 54 sur 1000 parties.
Soit 5.4% de victoires.
Nombre de points moyens par partie : 14.176

 ```
   

 
 
