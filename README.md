# M223: Punchclock
Dies ist eine Beispielapplikation für das Modul M223. Die hilfe dieser Applikation kann man Zeiteinträge erfassen.
Die erfassten Zeiteinträge haben eine Kategorie und einen Standort.

## Loslegen
Folgende Schritte befolgen um loszulegen:
1. Sicherstellen, dass JDK 12 installiert und in der Umgebungsvariable `path` definiert ist.
1. Ins Verzeichnis der Applikation wechseln und über die Kommandozeile mit `./gradlew bootRun` oder `./gradlew.bat bootRun` starten
1. Unittest mit `./gradlew test` oder `./gradlew.bat test` ausführen.
1. Ein ausführbares JAR kann mit `./gradlew bootJar` oder `./gradlew.bat bootJar` erstellt werden.

## Frontend Starten
1. Projekt https://github.com/Luca-Guettinger/UEK-223-Punchclock-Frontend klonen.
1. anweisungen in der README vom Frontend befolgen.

Folgende Dienste stehen während der Ausführung im Profil `dev` zur Verfügung:
- REST-Schnittstelle der Applikation: http://localhost:8081
- Dashboard der H2 Datenbank: http://localhost:8081/h2-console