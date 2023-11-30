pkg_pth="src/up/mi/ssdjha/projet/"
bin="bin/"


villeInexistanteException:
	javac -cp bin/ ${pkg_pth}VilleInexistanteException.java -d bin/

SyntaxErrorException:
	javac -cp bin/ ${pkg_pth}SyntaxErrorException.java -d bin/
	
routeInexistanteException:
	javac -cp bin/ ${pkg_pth}RouteInexistanteException.java -d bin/

unorderedPair :
	javac -cp bin/ ${pkg_pth}UnorderedPair.java -d bin/

ville:
	javac -cp bin/ ${pkg_pth}Ville.java -d bin/

commu:
	javac -cp bin/ ${pkg_pth}CommunauteAgglomeration.java -d bin/

inter:
	javac -cp bin/ ${pkg_pth}InterfaceTextuelle.java -d bin/
	
main:
	javac -cp bin/ ${pkg_pth}Main.java -d bin/

all: ville villeInexistanteException routeInexistanteException SyntaxErrorException unorderedPair commu inter main

pack :
	mkdir -p release/
	jar cvf release/$$(date +release_%d_%m.jar) $(filter-out release , $(wildcard *))
