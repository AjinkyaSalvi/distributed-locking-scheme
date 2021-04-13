Distributed Locking Scheme

01.	Program Details:

	(i)	Date: 24th October, 2019
	(ii)	Language: Java

02.	Problem Statement:

	(i)	Distributed file locking scheme which gives access of the database file to only one client at a time.

03.	Code Execution:

	(i)	To compile the above programs,
		change the directory of the Terminal or Command Prompt to the folder containing these files.

	(ii)	Then, enter 'javac Server.java', 'javac Client.java' and 'javac ServerThread' to compile these files.
		You can only run these files after compiling them.

	(iii)	Finally, to run these files correctly,
		first, run 'Server.class' by entering 'java Server' then run 'Client.class' by entering 'java Client'.

	(iv)	Do not run 'ServerThread.class' file.
		As it will be called by 'Server.class'.

04.	Note:

	(i)	For array passing,
		byte array can only process a small amount of data at a time.
		It fails when large data is passed to it.

	(ii)	This program is coded for fixed number of clients.
          Coding variable number of clients is also simple.
          It can just be done by adding while loops.
