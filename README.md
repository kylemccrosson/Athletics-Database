# Athletics-Database

This project was created to be a simple database that could store information for the Northeastern Athletics Department. In order to run the program you would need to install MySQL and set up a local server, and have a driver installed to use MySQL with Java.

Run the SQL file to create the athletics database on your local server.
You may choose to import data into the server but this is not necessary.

Running the Java program will now prompt you to enter a username and password to access your database (these you should have set up when creating your server).
Once the server is accessed you will have the ability to add, remove, and search aspects of the database.
The program will lead you with prompts to determine which actions you wish to take.

The Add function lets you add athletes, teams, coaches, and games to the database.
If, for example, you enter 'add athlete' the program will prompt you to enter each attribute an athlete has within the database and then will enter the athlete object into the database. This works similarly for all other objects.
(A database structure has been added to this repository, it is worth noting that certain objects must exist in order to add others-- for example, a team must exist in order for a player to be added to that team)

The Search function gives the user the option to search for games, athletes, coaches, or teams stored in the database by a variety of parameters including name, sport, and ID.

The Remove function lets you remove an athlete, coach, team, or game from the database by entering the ID of that particular item.
(It is also worth looking over database dependencies before using this function, so that for example, you don't delete a team that has a bunch of players that you want to keep)



