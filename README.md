# API - Laboratoire SMTP - Egger Magali, Nunez Tania
## Introduction
<p>Dans ce laboratoire nous avons implémenté une application permettant de faire des
pranks à des personnes de votre choix. Il est possible de définir une liste d'emails
qui seront les victimes de ces pranks. Notre application premettra de choisir un nombre
de groupe à qui envoyer des pranks et communiquera avec un serveur mail Mockmock grâce
au protocole SMTP.</p>

## Notre programme
<p>Nous avons conçu notre application de la manière suivante : </p>
<p>Une première classe ConfigurationManager s'occupe d'aller récupérer les
victimes, messages et paramètres de configuration. Une classe Personne 
permet de représenter nos victimes. Une personne est affilié à un Groupe, 
une autre classe, permettant de définir les recepteurs et l'envoyeur.
Une Prank est caractérisée par un groupe de personnes, nos victimes.
La classe PrankGenerator s'occupe d'instancier les pranks souhaitées et 
de créer également une instance de note classe SMTPClient, qui s'occupe
de la communication avec le serveur Mockmock. Le PrankGenerator s'occupe donc de
générer les Pranks et de les envoyer au serveur.</p>
<p>Finalement notre application crée un PrankGenerator et lui fera générer autant de Pranks
que de groupes.</p>

## Docker
<p>Par practicité, nous allons utiliser docker pour exécuter notre serveur en
arrière-plan. Pour ceci nous avons du créer une image docker. Pour se faire,
il faut créer un Dockerfile au même endroit que le fichier .jar du serveur.
Afin de dire à docker comment exécuter ce programme, les directives exécutives
suivante doivent figurer dans le Dockerfile</p>

FROM openjdk:11

EXPOSE 8282
EXPOSE 25

COPY MockMock-1.4.0.one-jar.jar /

WORKDIR /

CMD ["java", "-jar", "MockMock-1.4.0.one-jar.jar"]

<p>Une fois le Dockerfile rempli, il faut construire l'image à l'aide de la commande :
</p>

docker build -t mockmock .

<p>Maintenant Mockmock est prêt à être lancé avec Docker avec la commande :</p>

docker run -d --rm --name mockmock -p 8282:8282 -p 25:25 mockmock

<p>Il est important de mapper les ports correctement pour avoir accès à Mockmock
depuis un navigateur. Pour accéder aux mail il suffit d'aller sur localhost:8282.</p>

## Utilisation
<p>Tout d'abord, le serveur Mockmock doit être lancé au préalable sur Docker grâce à la
commande run citée plus haut. Une fois le serveur lancé, il suffit de lancer le programme principal
de la classe App et les Pranks s'enverront sur Mockmock.</p>
<p>Les fichier de configuration se trouvent dans le package config.config et peuvent être modifié
à souhait si des victimes ou des messages sont à ajouter. À noter que l'introduction d'un email de 
victime sans le caractère '@' ne sera pas pris en compte. Les messages dans le fichier messages.utf8, si
il y en a plusieurs, doivent être séparés par les caractères "--"</p>

## À améliorer
<p>Nous n'avons pas passé beaucoup de temps sur les fichiers de configuration et leur syntaxe.
Il s'agit d'un point qui peut être grandement amélioré afin de personnaliser mieux les messages et potentielles
victimes.</p>
