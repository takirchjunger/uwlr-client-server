ECK2 library
=============

Library om het ontwikkelen van een UWLR (voorheen: eck2) webservice te vereenvoudigen.

Deze library is nu bijgewerkt tot versie 2.2 van de standaard.

Releasen
=========

Voor het releasen wordt gebruik gemaakt van de release-plugin van Maven. Deze regelt automatisch het wijzigen van 
de pom en het committen en pushen van versie-wijzigingen naar Git. Erg handig, je gebruikt het als volgt:

* mvn release:prepare -DignoreSnapshots=true
* mvn release:perform 
