# Gestion des dossiers étudiants

## Configuration de la base de données

Pour ce projet, on utilise une base de données MySQL et JPA Hibernate pour gérer les communications avec cette dernière. La base de données n'a pour le moment pas vocation à tourner sur un serveur distant, mais sur un serveur locale. Chaque membre doit donc configurer un serveur local pour pouvoir utiliser la base de données convenablement. Le tutoriel ci-dessous permettra d'instancier cette base de données si vous ne l'avez jamais fait:

### Installation de MySQL

Suivez ce lien https://dev.mysql.com/downloads/mysql/ puis télécharger le MSI Installer. Une fois télécharger, ouvrez-le, faites next, acceptez les termes d'utilisation puis next. On vous demandera ensuite le type d'installation. Choisissez Custom puis installez MySQL Server.

### Configurer MySQL

Une fois le serveur installé, ouvrez MySQL Configurator. Il vous permettra de configurer MySQL. Vous pouvez laisser les paramètres par défaut. Les paramètres les plus importants seront le port et le mot de passe. Hibernate va utiliser un fichier persistence.xml pour communiquer avec la base de données, dans ce fichier, il y aura les informations du port et du mot de passe. Pour éviter de futurs potentiels conflits, je conseille de définir le numéro de port à 3306 et le mot de passe à password.
Assurez-vous de bien effectuer toutes les étapes de configurations avant de quitter MySQL Configurator.

### Creation de la base de données

Une fois MySQL configurée, on va devoir créer une base de données pour le projet. Ouvrez MySQL Command Line Client et entrez le mot de passe définit lors de la configuration. Entrez ensuite la commande ***CREATE DATABASE gestion_etudiant_dev;*** Pour vous assurer que la base de données à bien été crée, faites la commande ***SHOW DATABASES;***. Si vous voyez apparaître gestion_etudiant_dev, tout est bon

### Dépendances

Toutes les dépendances sont normalement directement inclus dans le répertoire git