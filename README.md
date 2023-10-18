# Enjoy
Casi d’uso ENJOYFITNESS CLUB:
-Descrizione del modello di dominio:
Al circolo "Enjoy Fitness Club" possono iscrivere più clienti. È presente una segreteria in cui lavorano uno o più segretari. Ogni Segretario è dotato di Nome, Cognome e può inserire nuove tipologie di campo e visualizzare i campi prenotati da uno o più clienti. 

Un Cliente può iscriversi al circolo sportivo fornendo Nome, Cognome, E-mail, Numero di Telefono. Un socio può effettuare una o più Prenotazioni. Una Prenotazione, con Data, Fascia-Oraria e commento, è associata a un solo socio. Ogni Prenotazione riguarda una e una sola tipologia di Campo. Per ogni tipologia di Campo sono di interesse Nome, numero  di persone prezzo e descrizione.
Caso d’uso UC1: Registrazione Cliente – Attore Primario: un Cliente
1.	Un cliente vuole registrarsi sulla piattaforma EnjoyFit
2.	Il cliente sceglie l’attività “Registrazione”
3.	Il cliente inserisce Username password nome, cognome,  mail e numero di telefono. Il sistema verifica se questi dati sono stati già inseriti, in caso contrario mostra che l’operazione è andata a buon fine.

Caso d’uso UC2: Registrazione Segretario – Attore Primario: un Segretario(Admin)
1.	Un segretario vuole registrarsi sulla piattaforma enjoyFit
2.	Il segretario sceglie l’attività “Registrazione”
3.	Il segretario inserisce Username Password nome e cognome. Il sistema verifica se questi dati sono stati già inseriti, in caso contrario mostra che l’operazione è andata a buon fine.

Caso d’uso UC1: Prenotazione campi cliente-Attore Primario: Cliente 
1.	Un Cliente vuole effettuare la prenotazione di un campo.
2.	Il Cliente inserisce l’Username e Password. Il sistema verifica la correttezza dei dati immessi, e autentica il Cliente. Il sistema mostra una pagina.
3.	Il Cliente seleziona l’attività “Prenota campo”.
4.	Il Cliente seleziona la tipologia del campo che vuole prenotare selezionando la fascia oraria e il giorno. Il sistema verifica che in quell’orario e giorno non è avvenuta già una prenotazione. Il sistema se l’operazione è andata a buon fine mostra il recap di prenotazione altrimenti segnala che l’operazione non è andata a buon fine.
5.	Il cliente può poi proseguire nell’uso del sistema.






Caso d’uso UC2: Gestisci Prenotazioni--Attore Primario: cliente
1.	Un Cliente vuole visualizzare le prenotazioni da lui prenotazioni effettuate
2.	Il Cliente inserisce l’Username e Password. Il sistema verifica la correttezza dei dati immessi, e autentica il Cliente. Il sistema mostra una pagina.
3.	Il Cliente sceglie l’attività “Gestisci Prenotazioni”.
4.	Il Sistema mostra le varie prenotazioni effettuate dal cliente. il Cliente seleziona la prenotazione a cui è interessato.
5.	Il sistema mostra i dettagli della prenotazione.
6.	Il socio può poi proseguire nell’uso del sistema.
4a Il socio può eliminare l’abbonamento ad un’attività.

Caso d’uso UC3: Modifica Prenotazione --Attore Primario: cliente
1.	Un Cliente vuole modificare una prenotazione da lui effettuata
2.	Il Cliente inserisce l’Username e Password. Il sistema verifica la correttezza dei dati immessi, e autentica il Cliente. Il sistema mostra una pagina.
3.	Il Cliente sceglie l’attività “Gestisci Prenotazioni”.
4.	Il Sistema mostra le varie prenotazioni effettuate dal cliente. Il Cliente seleziona la voce “Modifica”.
5.	Il sistema mostra una form dove è possibile modificare ora e data di prenotazione.
6.	Il Cliente può poi proseguire nell’uso del sistema.

Caso d’uso UC4: Inserimento Tipologia Campo--Attore Primario: Segretario
1.	Un segretario vuole effettuare l’inserimento di un nuovo orario di prenotazione per un campo.
2.	Il Segretario inserisce L’Username e Password. Il sistema verifica la correttezza dei dati immessi, e autentica il Segretario come amministratore della pagina. Il sistema mostra una pagina.
3.	Il Segretario sceglie l’attività “inserisci una nuova tipologia di campo”. Il sistema mostra una form.
4.	Il Segretario inserisce nome, numero (massimo) di persone, prezzo (calcolato fisso su una fascia oraria di massimo 2h), una piccola descrizione sulle caratteristica del campo e in immagine rappresentativa.
5.	Il Segretario seleziona la voce “invio”. Il sistema mostra che l’operazione è andata a buon fine mostrando il nuovo campo aggiunto alla pagina delle tipologie dei campi.

Caso d’uso UC5: Visualizzazione Campi--Attore Primario: Utente non registrato
1.	Un Utente non registrato vuole vedere quali sono le tipologie di campi che offre “Enjoy fitness Club”.
2.	L’ utente non registrato seleziona l’attività “Campi”
3.	Il sistema mostra un l’elenco di tutti i possibili campi presenti nella struttura.
4.	L’utente seleziona la tipologia di campo che vuole visionare.
5.	Il sistema mostra la descrizione del campo selezionato con relativa immagine identificativa.



Caso d’uso UC6: Visualizza  tutte le Prenotazioni--Attore Primario: Admin
1.	Un Segretario (loggato come admin) vuole visualizzare tutte le prenotazioni effettuate sul portale.
2.	Il Segretario inserisce l’Username e Password. Il sistema verifica la correttezza dei dati immessi, e autentica il Segretario. Il sistema mostra una pagina.
3.	Il Cliente sceglie l’attività “Visualizza tutte le Prenotazioni”.
4.	Il Sistema mostra le varie prenotazioni effettuate dai vari clienti iscritti ad Enjoy Fitness-Club. il Segretario seleziona la prenotazione a cui è interessato.
5.	Il sistema mostra i dettagli della prenotazione.
6.	Il Segretario può poi proseguire nell’uso del sistema.




