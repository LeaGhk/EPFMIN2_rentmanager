# Rent Manager
#### Projet Java EPF 4A MIN


## Fonctionnalités
- Créer un client / une voiture / une réservation 
- Supprimer un client / une voiture / une réservation
- Modifier un client / une voiture / une réservation
- Afficher la liste des clients / des voitures / des réservations
- Afficher la liste des réservations et des voitures associées à un client
- Afficher la liste des réservations et des clients associés à une voiture
- Afficher le nombre de clients / de voitures / de réservations sur la page d'accueil

##### Caractéristiques 
- Client : possède un identifiant unique, un nom, un prénom, une date de naissance et une adresse mail.
- Voiture : possède un identifiant unique, un constructeur, un modèle et un nombre de places.
- Réservation : possède un identifiant unique, une date de début, une date de fin et est associée à un client et un véhicule.

## Contraintes
- Un client n'ayant pas 18 ans ne peut pas être créé
- Un client ayant une adresse mail déjà prise ne peut pas être créé
- Le nom et le prénom d'un client doivent faire au moins 3 caractères
- La date de début de reservation doit être avant la date de fin de réservation
- Une voiture ne peut pas être réservée 2 fois le même jour
- Une voiture ne peur pas être réservée plus de 7 jours en une seule réservation
- Une voiture ne peut pas être réservée 30 jours de suite sans pause
- Si un client ou un véhicule est supprimé, les réservations associées sont supprimées
- Une voiture ne peut avoir que 2 à 9 places

