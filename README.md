# Project Title

Simulate a game of Mancala using traditional Kalah rules, this time with a GUI!!

## Description

This Online Game of Mancala uses Object Oriented Programming to maintain readability within the code as well as 
improve maintainability. This is a fun game for 2 players to go head to head in!

## Getting Started

### Dependencies

Must have a JavaJDK installed to compile program. 

That said, if users want to build the project from scratch, modify the code, or run tests using Gradle tasks, they will need Gradle installed.

### Executing program

Firstly clone the project.
```
git clone [https://gitlab.socs.uoguelph.ca/2430F23/amoin/GP4.git]
```
Then compile the project using:
```
java -cp build/classes/java/main ui.GUI
```
Or if you want to edit the code, build with gradle and then compile as said: 
```
gradle build
gradle echo - will give executable script
java -cp build/classes/java/main ui.GUI
```
Expected output:
```
Prompt To Select Game Mode -> MUST SELECT OPTION, CLOSING WILL END APPLICATION
--------------------

Prompt To Login Or Register -> Press X To Play Without A User Profile

--------------------

Play Game As Normal! Press Pits To Make Move, It's Simple!

--------------------

Save Game Button -> Save The Game At Any Point, Enter A Filename And Save

--------------------

Exit Game Button -> Exit The Current Game, Bringing You To A Fresh Menu And Board

--------------------

Choose File Button -> Select A Game File To Load And Play. If A User File Is Selected Will Continue Current Game

--------------------

```
## Limitations

No limitations or features excluded from program.

## Author Information

Ashar Moin
amoin@uoguelph.ca

## Development History

Differences between AI and my code:
The Ai is able to create the code in a much less robust manner, allowing for simple code, however, my code is easier to maintain as things are spread between multiple classes. The Ai took more of a procedural approach. Besides that, the programs function the same. 

* 0.1
    * Initial Release

## Acknowledgments

* [assignmentThree.pdf](https://moodle.socs.uoguelph.ca/pluginfile.php/67802/mod_assign/introattachment/0/assignmentThree.pdf?forcedownload=1)
* [Moodle Javadocs](https://moodle.socs.uoguelph.ca/pluginfile.php/67801/mod_assign/intro/javadocs.zip)
* [GPT-4 For AI Generated Version](https://chat.openai.com/)



