Singleton f�r MySQL connectionen, l�ttare att sk�ta, kan h�lla en connection 	uppe, ex. under hela app-tiden

G�r UI:t i scene builder, hitta n�gon l�mplig kurs f�r att jobba med FXML och 	JavaFX (Java):
		https://app.pluralsight.com/library/courses/java-se-java-fx-		application-building-your-first/table-of-contents

Enum klass f�r alla dom olika f�rgade blupparna, eller �s kan man man anv�nda
	Colors.ENUM f�r att h�lla koll p� dom etc.

Kolla igenom den h�r tutorialen:
	https://www.youtube.com/watch?	v=cgv63JD7pfc&list=PLS1QulWo1RIaUGP446_pWLgTZPiFizEMq&index=6


////////////////////////////////////////////////////////////////////////
//				TODO				      //
////////////////////////////////////////////////////////////////////////

BUGGAR:
	- invocationException p� spelviewen orsakas antagligen av att den 		f�rs�ker anv�nda klassen innan den har instansats, man hade
		kunnat ha en button som liksom laddar allting allt �r 		invisible fr�n b�rjan s� laddas det n�r man k�nner sig "redo"

ATT G�RA:

	spelsidan
		- ska komma upp en s�n "action" ruta som fr�gar om man vill 		spela igen (n�r spelet n�tt sitt slut)
		- man ska kunna navigera sig d�rifr�n, och d� bli promptad
		om man verkligen vill l�mna

	fixa highscore sida
		- kanske n�gon slags dropdown list s� man kan se vilka drag 	
		- lista som �r ett ascending resultset fr�n tablen sorterat p�
		score, m�ste s�tta score
		

	fixa en f�rsta sida
		- man registrerar sitt namn
		- man v�ljer hur m�nga f�rger man ska ha (6 / 8)
			- detta sparas i en variabel i sj�lva spel FXML:en
		

	
	



F�rger: 6 eller 8
H�l: 4


Eftersom alla rader kommer visas fr�n b�rjan s� f�r man styra det hela genom 		att till�ta / inte till�ta att man s�tter p� raden man f�rs�ker s�tta 		en cirkel i

Det ska finnas tv� views, en som �r om man vill LOAD:a ett tidigare spel, 	kolla p� HIGHSCORE:n



Enum klass f�r alla dom olika f�rgade blupparna, eller �s kan man man anv�nda
	Colors.ENUM, men om man vill ha egna f�rger s� f�r man g�ra en egen 		ENUM klass med alla RGB v�rden


EVENTUELLT: 
	Anv�nd 3D objekt, typ sphere och dyl.





