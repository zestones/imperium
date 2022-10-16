# Imperium

---

Imperium est un logiciel de gestion de projets permettant de gérer ses tâches et ses projets.

L'utilisateur peu ainsi créér des tableaux personalisable auquel il pourra invité, des collaborateurs qui pourront selon les accès qui leurs seront donnée, éditer le tableau, les listes et les tâches, ou alors tout simplement le lire.

# Démarche pour tester le projet

Le projet Imperium est déployé sur Heroku.

De fait, il est accessible à l'adresse suivante: <a href="https://imperiums.herokuapp.com/" target="_blank">Imperium.co</a>

## Récupération des sources

Tout les sources du projet sont disponible sur gitlab vous pouvez donc clone le repo :

### Clone avec SSH

`git clone git@gitlab.com:remiemonet/2022-pwa-a.git`

### Clone avec HTTPS

`https://gitlab.com/remiemonet/2022-pwa-a.git`

## Execution du projet

Le projet peut également être executer via le terminal a l'aide de la commande suivant :

`java $JAVA_OPTS -Dserver.port=$PORT -jar imperium/target/*.jar`
