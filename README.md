# BeeSim - Simulation Multi Agents d'Abeilles

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

## Description

BeeSim est une simulation 2D représentant le comportement des abeilles dans un environnement avec des ruches et des fleurs. Ce projet a pour but de démontrer l'interaction entre les abeilles, les fleurs et les ruches, en mettant en œuvre des concepts comme le déplacement des abeilles, la collecte de nectar et la gestion des ruches.

## Fonctionnalités

- **Création d'un environnement** : Un environnement composé de fleurs et de ruches.
- **Déplacement des abeilles** : Les abeilles se déplacent dans l'environnement à la recherche de fleurs.
- **Collecte de nectar** : Les abeilles collectent du nectar pour remplir les ruches.
- **Gestion des ruches** : Les ruches stockent le nectar collecté par les abeilles.

## Technologies utilisées

- **Java** : Langage principal pour la simulation.
- **JavaFX** : Utilisé pour l'interface graphique 2D.
- **JUnit** : Pour les tests unitaires.

## Prérequis

Pour exécuter ce projet, vous devez avoir installé les outils suivants sur votre machine :

- **Java 17+** : Pour compiler et exécuter l'application.
- **Maven** : Pour gérer les dépendances et les tests.

## Installation

1. **Clonez le projet** :
   ```bash
   git clone https://github.com/ton-utilisateur/BeeSim.git
2. **Accédez au dossier du projet** :
    ```bash
    cd BeeSim
3. **Compilez le projet avec Maven** :
    ```bash
    mvn clean install
4. **Lancez l'application avec Maven** :
    ```bash
    mvn javafx:run

## Utilisation

1. À l'exécution, une fenêtre 2D s'ouvre avec un environnement où des abeilles cherchent des fleurs pour collecter du nectar.
2. Les ruches sont visibles sur la grille, et les abeilles se déplacent pour collecter du nectar et le déposer dans les ruches.
3. Vous pouvez voir l'interaction en temps réel entre les abeilles, les fleurs et les ruches.

## Tests 
Des tests unitaires ont été implémentés pour vérifier le bon fonctionnement de certaines fonctionnalités :

Test de déplacement des abeilles.
Test de collecte de nectar.
Test de gestion des ruches.

Pour exécuter les tests unitaires, utilisez la commande suivante :
    ```bash
    mvn test
