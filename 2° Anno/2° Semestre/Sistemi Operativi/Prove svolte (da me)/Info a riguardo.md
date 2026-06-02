Tutte le prove che ho svolto in prossimità dello scritto e corrette da claude.

**Tipologie di esercizi:** 
1. Scheduling della CPU, chiede di usare l'algoritmo RR (Round Robin) dato un certo quanto di tempo, SJF (Shortest Job First), SRJF (Shortest Remaining Job First, semplicemente la versione preemptive del SJF), Priorità con prelazione
2. Dà un piccolo programma ed i pid dei processi, si eseguono tutti i processi a partire da quello padre e qui è importante ricordare che la fork() restituisce 0 al processo figlio ed al padre fornisce il pid del figlio che ha creato
3. Anche qui c'è un programma ma cn thread e mutex, con pthread_mutext_lock il mutex è in sezione critica e vi esce con l'unlock quando ha finito ciò che doveva fare, spesso il prof mette pthread_cond_wait e qui il mutex viene rilasciato mentre il thread si "addormenta"
in attesa di ricevere il pthread_cond_signal. quando il thread ha una condizione di wait si ferma lì e si passa al thread successivo, quando il thread successivo genera il signal allora si torna al thread che era rimasto in attesa.
4. Deadlock: si usa l'algoritmo del banchiere, tutto spiegato passo passo nei pdf per questo esercizio
5. Memoria paginata, si convertono i dati in potenze di 2 (non l'ho fatto ma è consigliabile usare i logaritmi in base due) in base a ciò che chiede la domanda
6. Gestione dei Page Fault, chiede di usare l'algoritmo FIFO (First In First Out, difficile che lo chieda), LRU (Least Recently Used), OPT (Ottimo) 
7. Dati TOT cilindri e la testina sul disco chiederà di usare C-LOOK (Circolare e arriva all'ultima richiesta, non all'estremità del disco), LOOK (come il C-LOOK ma non è circolare quindi torna indietro), SCAN, C-SCAN (arriva all'estremità del disco), SSTF (và alla richiesta più vicina), FCFS (si segue l'ordine di arrivo delle richieste)
8 e 9 sono domande di teoria 
