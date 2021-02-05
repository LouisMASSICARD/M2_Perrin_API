# M2_Perrin_API

Mise en pratique du cours API : API permettant à des utilisateurs inscrits d'acheter des cours vidéo, comme peuvent le faire des sites tels que Udacity ou Coursera.

Online Git :  
https://github.com/LouisMASSICARD/M2_Perrin_API  

--- 

## Images et containers Docker utilisées

### consul : port 8500

On utilise consul comme regitre de services.  
Les services vont s'enregistrer auprès de consul.  
Si un service a besoin d'un autre service, alors il demande à consul où trouver ce service.

Si vous avez déjà réalisé cette étape auparavant,  
alors vous avez uniquement à lancer la commande suivante :  

```
docker start consul
```

sinon la commande suivante :  

```
docker run --name consul -p 8500:8500 consul
```

### postgre : port 5432

On va faire persister nos données sur une base postgres.  

 * USER=postgres
 * PASSWORD=riovas

On s'assure qu'il n'y a pas déjà un postgres qui tourne sur la machine :  
(si on est certain qu'il n'y a pas de postgres qui tourne sur la machine on peut sauter cette étape)

```
sudo pkill -u postgres
```

Si vous avez déjà réalisé cette étape auparavant,  
alors vous avez uniquement à lancer la commande suivante :  

```
docker start postgres
```  

sinon la commande suivante :  

```
docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=riovas -p 5432:5432 -d postgres
```

### rabbitMQ : port 5672 (Management Plugin : port 15 672)

On utilise rabbitMQ pour gérer les queues de messages.  

```
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Pour les services qui consomment des massages, on a dans leur `application.properties` :  

```
# bindings stream
spring.cloud.stream.bindings.input.destination = cours
spring.cloud.stream.bindings.input.group = cours-group
spring.cloud.stream.bindings.input.durableSubscription = true
```

Pour les services qui produisent des massages, on a dans leur `application.properties` :  

```
# bindings stream
spring.cloud.stream.bindings.output.destination = cours
```

On a un plugin de management sur le port 15 672 :  

 * username : guest
 * password : guest

Modification du mot de passe et du login avec : 

```
docker run -it --rm --name rabbitmq -p 5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password -p 15672:15672 rabbitmq:3-management
```

---  

## Utilisation des containers Docker : interaction avec Consul et Postgres

### Consul monitoring

On peut voir tout les services enregistrés auprès de consul :  

http://localhost:8500/ui/dc1/services

### Postgres management

On peut accéder à la base de données Postgres.  
En effet, on peut manager notre base dans le contenair docker postgres.  

Le mot de passe par défaut (renseigné plus haut) est : `riovas`.  

```
docker run -it --rm --link postgres:postgres postgres psql -h postgres -U postgres
```

Pour lister les databases : `\l`  


Exemple de requête :   

```
select * from utilisateur;
```

Pour quitter l'invite de commande postgres : `\q`

---  

## Gestion de la configuration du projet : initialisation

### config repo

On va créer un repo lié à un dépôtr Git.  
Le but est de facilement switcher entre deux configuration en changeant simplement de branche.  
Dans notre cas on se place sur une version de dev : `version=dev`.  

```
cd config
more utilisateurs-service.properties

git init
git add utilisateurs-service.properties
git commit -am "Version initial"
```

### config-service repo : port 8888

```
cd config-service
```

```
nano src/main/resources/application.properties
```

S'assurer que l'on a bien :  

```
server.port=8888  
spring.cloud.config.server.git.uri=file:///home/louis/Documents/M2/S9/Perrin_API/M2_Perrin_API/config
```

Il faut faire matcher le path précisé dans `spring.cloud.config.server.git.uri` avec la localisation du **repo config**.  


---  

## Gestion de la configuration du projet : modificaiton à chaud des configurations

Si vous n'avez pas encore démarré de service, veuillez vous référer au paragraphe suivant `Run services`.  

On se place dans le dossier de gestion des configurations :  

```
cd config
```

On regarde quelle est la configuration actuellement utilisée.  
Si vous avez réalisez (exactement) les manipulations précédentes,  
alors vous devriez avoir comme retour `version=dev` à la commande suivante :  

```
more intervenants-service.properties
```

Vous pouvez modifiez les configurations avec la commande suivante.  
Par exemple, vous pouvez remplacer `version=prod` par `version=prod`.

```
nano intervenants-service.properties
```

Pour rafraichir la configuration du service tournant sur le port 8082 :

```
curl localhost:8082/actuator/refresh -d {} -H "Content-Type: application/json"
```

Vous pouvez vérifier le changement de configuraiton avc les deux liens suivants :  

http://localhost:8888/cours-service/default  
http://localhost:8082/version  

---  

## Run services

### config-service repo : port 8888

```
cd config-service
mvn clean package -DskipTests
java -jar target/config-service-1.0.jar
```

Vous pouvez vérifier que le service est bien up :  

http://localhost:8888/cours-service/default  

On doit avoir un résultat équivalant à celui-ci :  

```
{
    "name": "cours-service",
    "profiles": [
        "default"
    ],
    "label": null,
    "version": "19940e21e681418097f9be9c6673dd22d0129de8",
    "state": null,
    "propertySources": [
        {
            "name": "file:////home/louis/Documents/M2/S9/Perrin_API/M2_Perrin_API/config/cours-service.properties",
            "source": {
                "version": "dev"
            }
        }
    ]
}
```

### intervenants-service repo : port 8082

```
cd intervenants-service/
mvn clean package -DskipTests
java -jar target/intervenants-service-1.0.jar
```

Vous pouvez vérifier que le service est bien up :  

http://localhost:8082/intervenants

On est doit avoir le résultat `Version: dev` sur le lien suivant :  

http://localhost:8082/version  


---  

## Loadbalancing

### Lancer une autre instance du service intervenants-service

On peut lancer une autre instance sur un port qui n'est pas le port par défaut.  
Le port par défaut est renseigné dans le fichier `application.properties` du service.  
Par exemple : `/home/louis/Documents/M2/S9/Perrin_API/M2_Perrin_API/users-service/src/main/resources/application.properties`  


```
java -Dserver.port=8083 -jar target/intervenants-service-1.0.jar
```

On peut vérifier via l'interface web de Consul que la nouvelle instance est bien en place :  

http://localhost:8500/ui/dc1/services

---  

## API Gateway + Circuit Breaker

### cours-gateway repo : port 9000

```
cd cours-gateway
mvn clean package -DskipTests
java -jar target/cours-gateway-1.0.jar
```

Cette passerelle utilise/redirige vers l'API cours-service.  
On le voit à l'utilisation de l'annotaion `@FeignClient("cours-service")`.  

### Hystrix

Pour ne pas avoir de message d'erreur génant l'utilisation des API en bloquant tout en remontant en chaine des erreurs,  
on utilise `Hystrix` comme `Circuit Breaker`.  

Par exemple, pour course-service, on a l'annotaion `@EnableCircuitBreaker` au niveau de la classe `CoursServiceApplication.java`.  

Au niveau de l'API Gateway, on a des fallback au cas où l'API cours-service ne réponde pas :  
```
@HystrixCommand(fallbackMethod = "fallback")
@RequestMapping(method = RequestMethod.GET, value = "/noms")
public CollectionModel<EntityModel<Cours>> noms() {
    ...
}

public CollectionModel<EntityModel<Cours>> fallback() {
    ...
}
```

---  

## Monitoring : spring-boot-admin & actuator + monitor + zipkin + hystrix

### spring-boot-admin repo : port 8762

```
cd spring-boot-admin
mvn clean package -DskipTests
java -jar target/spring-boot-admin-1.0.jar
```

Utilisation de l'annotaion `@EnableAdminServer` pour définir ce service comme sserver d'administration.  
Il utilise les informations émises par les autres services utilisant `actuator` :  
Dans les fichiers `application.propoerties` on peut avoir :  

```
management.endpoints.beans.enabled=false
management.endpoints.web.exposure.include=*
``` 

http://localhost:8762	

http://localhost:8762/applications  


Réutilisation d'informations fournies par actuator : on peut pour chaque service :  

http://localhost:8080/actuator  
http://localhost:8080/actuator/health  
http://localhost:8080/actuator/metrics  
http://localhost:8080/actuator/...  

http://localhost:8082/actuator  
http://localhost:8082/actuator/health  
...  

http://localhost:8083/actuator  
http://localhost:8083/actuator/health  
...  

### monitor repo : port 9903

Monitor récupère les infos Hystrix pour les annalyser.  

```
cd monitor
mvn clean package -DskipTests
java -jar target/monitor-1.0.jar
```

http://localhost:9903/hystrix

Pour analyser un cours-service tournant sur le port 8080, on peut par exemple remplir le champ `Title` avec :  
```
localhost:8080/actuator/hystrix.stream
```

Puis cliquer sur le bouton `Monitor Stream`.  

### zipkin : port 9411

http://localhost:9411/zipkin

Par exemple, utilisation des tags suivant :  

 * `serviceName=cours-gateway`  
 * `spanName=hystrix`  

#### zipkin repo

```
cd zipkin
java -jar zipkin.jar
```

#### zipkin Docker

On peut utiliser une image Docker à la place du *repo zipkin* si on le souhaite :  

```
docker run -p 9411:9411 openzipkin/zipkin
```

### Hystrix 

Si on a `circuit close` c'est que tout va bien.  

http://localhost:9903/hystrix  
http://localhost:8080/actuator/hystrix.stream  