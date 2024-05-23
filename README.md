# Tris
Gioco del tris fatto su java

## Installazione

È NECESSARIO AVERE JAVA INSTALLATO!
### per installare java su debian
#### per installare l'ultimo jre
```bash
sudo apt update
sudo apt install default-jre
```
#### per installare l'ultimo jdk
```bash
sudo apt update
sudo apt install default-jdk
```
#### per controllare l'installazione
```bash
java --version
```
### per installare java su arch linux
#### per installare l'ultimo jre
```bash
sudo pacman -Syu
sudo pacman -Sy jre-openjdk
```
#### per installare il jdk
```bash
sudo pacman -Syu
sudo pacman -S jdk-openjdk
```
#### per controllare l'installazione
```bash
java --version
```

### installazione da Source 

```bash
git clone git@github.com:arthur875/Tris.git
cd Tris
javac src/main/GUI.java
java src/main/GUI.java
```

