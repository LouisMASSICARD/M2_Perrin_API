# M2_Perrin_API

Mise en pratique du cours API : API permettant à des utilisateurs inscrits d'acheter des cours vidéo, comme peuvent le faire des sites tels que Udacity ou Coursera.

## Use docker 

### consul

On utilise consul comme regitre de services.  
Les services vont s'enregistrer auprès de consul.  
Si un service a besoin d'un autre service, alors il demande à consul où trouver ce service.

`docker run --name consul -p 8500:8500 consul`

### postgre

On s'assure qu'il n'y a pas déjà un postgres qui tourne sur la machine :  
(si on est certain qu'il n'y a pas de postgres qui tourne sur la machine on peut sauter cette étape)

`sudo pkill -u postgres`

On va faire persister nos données sur une base postgres :  

`docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=riovas -p 5432:5432 -d postgres`

## Gestion de la configuration du projet

### config-service repo

`cd config-service`

` nano src/main/resources/application.properties `

S'assurer qu'e l'on a bien :  

```
server.port=8888  
spring.cloud.config.server.git.uri=file:///home/louis/Documents/M2/S9/Perrin_API/M2_Perrin_API/config
```

Il faut faire matcher le path précisé dans `spring.cloud.config.server.git.uri` avec la localisation du **repo configs**.  

### configs repo

On va créer un repo lié à un dépôtr Git.  
Le but est de facilement switcher entre deux configuration en changeant simplement de branche.  
Dans notre cas on se place sur une version de dev : `version=dev`.  

```
cd config
more intervenants-service.properties ===> version=dev

git init
git add intervenants-service.properties
git commit -am "Version initial"
```