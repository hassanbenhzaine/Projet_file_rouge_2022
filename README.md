# Project file rouge 2020 - YouCode

# Contexte (principe du projet)

Une application simple qui peut vous aider à améliorer la relation client pour développer votre entreprise plus efficacement. Vous pouvez gérer les associations de votre entreprise dans une interface conviviale et avec une faible courbe d'apprentissage en l'utilisant.

La gestion des clients est l'objectif principal et cette application vous aide de nombreuses façons.

# Périmètre (type de l'application)

Une application web RESTfull

# Spécifications fonctionnelles (les diagrammes, utilisateurs, dictionnaire des données)

**Diagramme de classe**

![alt text](diagrams/class_diagram.svg)


**Utilisateurs** 

Administrateur:
    - L'administrateur de l'application effectue toutes les activités d'administration et dispose d'un accès complet à la gestion. Peut également ajouter et configurer les paramètres de l'application, créer des utilisateurs et attribuer un rôle avec des autorisations d'application

    L'application doit avoir au moins un administrateur d'application assigné individuellement. Si vous n'avez qu'un seul super administrateur assigné individuellement, vous ne pouvez pas modifier ou révoquer ses autorisations d'administrateur.

Manageur:
    - Les manageurs effectuent des activités liées à l'utilisateur pour des modules spécifiques de l'application. Les gestionnaires peuvent afficher et gérer (ajouter/modifier/supprimer) ainsi que tous les factures, employés, produits...

Employé:
    - L'employé dispose d'un ensemble fixe d'autorisations, mais il existe également des restrictions sur ce que ce rôle peut faire.

    Peut afficher le tableau de bord (des statistics général).
    Peut afficher et utiliser des products, départements, employées, factures, absences...
    Peut ajouter pour le plupart de temps mais pas modifier.


**Dictionnaire des données** 

![alt text](diagrams/data_dictionary.svg)

**Les technologies utilisées**
UML, Spring, Docker, Jenkins, PostgreSQL, ReactJS, HTML, CSS
