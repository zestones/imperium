# Rétrospective

Dans ce document nous allons effectuer une rétrospective sur le développement de l'application Imperium afin de faire un bilan de notre travail personnel.

## Comparaison aux objectifs
### Objectifs
Réalisation d'un outil de gestion de projet permettant à plusieurs utilisateurs de collaborer et de s'organiser sur des projets professionnels ou personnels. Les objectifs initiaux étaient : la création de planches listant toutes les tâches d'un projet, éditables par les utilisateurs. La mise en place de calendriers personnels pour chaque utilisateur lui permettant de visualiser les tâches qui lui sont attribuées sur chaque projet en cours. Mise en place d'un système de réseau social permettant d'accéder aux comptes utilisateur et de visualiser leurs informations et projets réalisés.     

### Objectifs atteints
- [x] Réalisation d'un outil de gestion de projet
- [x] La création de planches listant toutes les tâches d'un projet
- [ ] La mise en place de calendriers personnels 
- [x] Mise en place d'un système de réseau social

### Pratiques et technologies supplémentaires :
- [x] Utilisation de Spring Security 
- [x] Mise en place d'une API Rest 
- [x] Mise en place de la sécurité CSRF 
- [x] Mise en place d'un certificat Https
- [ ] Utilisation de WebSocket pour la gestion des profils utilisateurs 
- [x] CI/CD (serveur Heroku)
- [x] Mise en place d'un reverse proxy NGINX 
- [x] Automatisation des tests (SAST) 
- [x] Utilisation de SonarCloud

## Difficultés rencontrées
- Communication Spring-VueJs
- Ajout d'avatar personnalisé pour les comptes utilisateur
- Prise en main de vuejs

## Les acquis
- Utilisation de spring boot / spring security / thymeleaf / vue js.
- Mise en place d'une API REST / manipulation de l'API
- Manipulation du DOM à l'aide de Javascript
- Utilisation du Repository CRUD / JPA

## Améliorations possibles
- Élargissement de l'utilisation de VueJs sur d'autres pages.
- Ajout d'un calendrier récapitulatif des projets en cours de l'utilisateur.
- Ajout d'un onglet statistiques résumant l'activité de l'utilisateur (nombre de tâches assignées, nombre de tâches cloturées, nombre de projets achevés etc...)

## Ce que vous feriez différemment la prochaine fois

Nous penserons le projet dans son ensemble avant de l'ammorcer, nous déterminerons quelles parties pourront être plus ergonomiques en vuejs et lesquelles seront plus interessante à réaliser en thymeleaf.

## Ce que vous garderiez à tout prix la prochaine fois

Ce projet nous à permis de découvrir spring boot, ce framework nous à tous conquis et si il y a bien une chose que nous garderont la prochaine fois c'est bien ce framework. 