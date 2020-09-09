# biblio_batch

## Contexte
Ce projet a été développé en 2020 dans le cadre du cursus "Développeur d'application Java" d'OpenClassrooms et correspond à la partie Batch du projet 7.
Cette API permet d'exposer les informatinos concernant le catalogues et la gestion des prêts d'une bibliothèque.

## Pré-requis
Version de java : 11 (jdk utilisé : jdk11.0.7)
Maven 3.6

## Installation et déploiement
1.Configuration
une base PostgresSQL (pré-installation nécessaire) à paramétrer et peupler
Les paramètres de connection à la base sont à modifier dans le fichier src\resources\application-prod.properties (spring.datasource.url, spring.datasource.username et spring.datasource.password)

2.Déploiement

- Création du package via la commande suivante à la racine du repo :
     
        mvn clean package

- un fichier "bibliotheque-0.0.1-SNAPSHOT.jar" dans le sous-repertoire target doit être généré

3.Utilisation

- Après avoir démarré le serveur SMTP et l'API BibliOC

- Ordonnancer avec le scheduler de votre choix la commande :

        java -jar <chemin de votre fichier>\bibliotheque-0.0.1-SNAPSHOT.jar
