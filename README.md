# Project file rouge 2020 - YouCode

# Contexte (principe du projet)

Une application simple qui peut vous aider à améliorer la relation client pour développer votre entreprise plus efficacement. Vous pouvez gérer les associations de votre entreprise dans une interface conviviale et avec une faible courbe d'apprentissage en l'utilisant.

La gestion des clients est l'objectif principal et cette application vous aide de nombreuses façons.

# Périmètre (type de l'application)

Une application web RESTfull

# Spécifications fonctionnelles (les diagrammes, utilisateurs, dictionnaire des données)

**Diagramme de classe**

![alt text](https://github.com/hassanbenhzaine/Projet_file_rouge_2022/blob/dev/diagrams/class_diagram.svg)


**Utilisateurs** 

Administrateur:
    - L'administrateur de l'application effectue toutes les activités d'administration et dispose d'un accès complet à la gestion. Peut également ajouter et configurer les paramètres de l'application, créer des utilisateurs et attribuer un rôle avec des autorisations d'application

    L'application doit avoir au moins un administrateur d'application assigné individuellement. Si vous n'avez qu'un seul super administrateur assigné individuellement, vous ne pouvez pas modifier ou révoquer ses autorisations d'administrateur.

Manageur:
    - Les manageurs effectuent des activités liées à l'utilisateur pour des modules spécifiques de l'application. Les gestionnaires peuvent afficher et gérer (ajouter/modifier/supprimer) ainsi que tous les prospects, offres, propositions.

Agent:
    - L'agent dispose d'un ensemble fixe d'autorisations, mais il existe également des restrictions sur ce que ce rôle peut faire.

    Peut afficher le tableau de bord (uniquement ses données liées aux offres).
    Peut ajouter une personne responsable et une organisation (gérer sa propre personne et organisation créées).
    Peut afficher et utiliser des groupes de prospects.
    Peut afficher et utiliser les Pipelines.
    Peut ajouter des offres pour sa personne et son organisation.
    Peut gérer et commenter uniquement sa propre offre créée.
    Peut envoyer des propositions à son propre responsable d'une transaction.

Client:
    - Les clients (Leads) ont un accès visuel et de communication à la plupart des données de l'application. Vous pouvez gérer les Leads en invitant en tant que "Client" des utilisateurs à partir de l'action du module Leads (Personne).

    Peut afficher le tableau de bord (uniquement ses données liées aux offres).
    Peut voir les organisations auxquelles il appartient.
    Peut voir ses propres offres et commenter ces offres.
    Peut afficher la liste des propositions.


**Dictionnaire des données** 

![alt text](https://github.com/hassanbenhzaine/Projet_file_rouge_2022/blob/dev/diagrams/data_dictionary.svg)

**Les technologies utilisées**
UML, Spring Boot 2, Docker, Jenkins, JUnit, PostgreSQL, Angular, HTML, CSS
