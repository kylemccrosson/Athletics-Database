
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

/**
 * Allows for the connection to a local server, and access to this particular athletics database.
 */
public class AthleticsDatabase {

  Scanner scan = new Scanner(System.in);

    // Creates connection with the Server
  public Connection getConnection() throws SQLException {
    Connection conn;
    Properties props = new Properties();

    System.out.print("Enter username: ");
    props.put("user", scan.next());

    System.out.print("Enter password: ");
    props.put("password", scan.next());

    conn = DriverManager.getConnection("jdbc:mysql://"
        + "127.0.0.1:3306/athletics", props);
//3306
    return conn;
  }

  Connection conn;

    // Runs main program
  public void run() {


      // creates connection
    try {
      conn = getConnection();
      System.out.println("Connected to database");
    } catch (SQLException e) {
      System.out.println("ERROR: Could not connect to the database");
      e.printStackTrace();
      return;
    }

    boolean cont = true;
      /** "Main menu" so to speak, prompts for desired action
       * a proper entry would be something like 'add coach' or 'search team'
       */
    while (cont) {
      System.out.println("\nWhat would you like to do?");
      System.out.println("[add/remove/search] [athlete/coach/game/team], or [exit]");

      String action = scan.next().toLowerCase();

      if (action.equals("exit")) {
        cont = false;
        break;
      }

      String object = scan.next().toLowerCase();

      switch (action) {
        case "add": add(object);
          break;
        case "remove": remove(object);
          break;
        case "search": search(object);
          break;
        default: break;
      }
    }


  }

    // Method to add an object to the database
  public void add(String object) {

    switch (object) {
      case "athlete":
        scan.useDelimiter("\n");
        System.out.print("Athlete's name: ");
        String Aname = scan.next();
        System.out.print("Home town: ");
        String homeTown = scan.next();
        System.out.print("School year: ");
        String year = scan.next();
        System.out.print("Height: ");
        String height = scan.next();
        System.out.print("Team ID: ");
        String teamID = scan.next();
        System.out.print("Athlete ID: ");
        String athleteID = scan.next();
        scan.reset();
        try {
          Statement stmt = conn.createStatement();
          stmt.execute("INSERT INTO athlete VALUE ('" +  athleteID  + "', '"
              + Aname + "', '" + homeTown + "', '" + year + "', '" + height
              + "', '" + teamID  + "');");
          System.out.println("Athlete added.");
        } catch (SQLException e) {
          e.printStackTrace();
          System.out.print("Error in add.");
        }
        break;
      case "coach":
        scan.useDelimiter("\n");
    	  System.out.print("Coach's name: ");
    	  String Cname = scan.next();
    	  System.out.print("Position: ");
    	  String position = scan.next();
    	  System.out.print("CoachID ");
    	  String coachId = scan.next();
    	  System.out.print("teamID: ");
    	  String teamId = scan.next();
        scan.reset();
    	  try {
    		  Statement stmtC = conn.createStatement();
    		  stmtC.execute("INSERT INTO coach VALUE('"+ coachId + "','" + Cname +
    				  "', '" + position + "', '" + teamId + "');");
    	    System.out.println("Coach added.");
    	  } catch (SQLException e) {
    		  e.printStackTrace();
              System.out.print("Error in add."); 
    	  }
    	  break;
      case "team":
        scan.useDelimiter("\n");
    	  System.out.print("Teams name: ");
    	  String tName = scan.next();
    	  System.out.print("TeamID: ");
    	  String teamIdT = scan.next();
    	  System.out.print("Number of members: ");
    	  String num_members = scan.next();
    	  System.out.print("Division: ");
    	  String division = scan.next();
    	  System.out.print("Gender: ");
    	  String gender = scan.next();
    	  System.out.print("Wins: ");
    	  String wins = scan.next();
    	  System.out.print("Loses: :");
    	  String loses = scan.next();
        scan.reset();
    	  try{
    		  Statement stmtT = conn.createStatement();
    		  stmtT.execute("INSERT INTO team VALUE('" + tName + "', '" + teamIdT +
    				  "', '" + gender + "', '" + num_members +  "', '" + division +  "', '"
    				  + wins +  "', '" + loses + "');");
    		  System.out.println("Team added.");
    	  } catch (SQLException e) {
    		  e.printStackTrace();
              System.out.print("Error in add."); 
    	  }
    	  break;
      case "game":
        scan.useDelimiter("\n");
    	  System.out.print("Game ID: ");
    	  String gameID = scan.next();
    	  System.out.print("Location: ");
    	  String location = scan.next();
    	  System.out.print("Date: ");
    	  String date = scan.next();
    	  System.out.print("Time: ");
    	  String time = scan.next();
    	  System.out.print("Team ID: ");
    	  String teamIDg = scan.next();
        scan.reset();
    	  try{
    		  Statement stmtB = conn.createStatement();
    		  stmtB.execute("INSERT INTO game VALUE('" + gameID + "', '" + location +
    				  "', '" + date + "', '" + time + "', '" + teamIDg + "');");
          System.out.println("Team added.");
    	  }catch (SQLException e) {
    		  e.printStackTrace();
              System.out.print("Error in add."); 
    	  }
    	  break;
    }
  }
    
    // Method to remove an object from the database
  public void remove(String object) {

    switch (object) {
      case "athlete":
        System.out.print("ID of athlete to be removed: ");
        String athleteID = scan.next();
        try {
          Statement stmtA = conn.createStatement();
          stmtA.execute("DELETE FROM athlete WHERE athlete_id = " + athleteID + ";");
          System.out.println("Athlete removed.");
        } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Error in remove.");
          break;
        }
        break;
      case "coach":
        System.out.print("ID of coach to be removed: ");
        String coachID = scan.next();
        try {
          Statement stmtC = conn.createStatement();
          stmtC.execute("DELETE FROM coach WHERE coach_id = " + coachID + ";");
          System.out.println("Coach removed.");
        } catch (SQLException e) {
          System.out.println("Error in remove.");
          break;
        }
        break;
      case "game":
        System.out.print("ID of game to be removed: ");
        String gameID = scan.next();
        try {
          Statement stmtG = conn.createStatement();
          stmtG.execute("DELETE FROM game WHERE game_id = " + gameID + ";");
          System.out.println("Game removed.");
        } catch (SQLException e) {
          System.out.println("Error in remove.");
          break;
        }
        break;
      case "team":
        System.out.print("ID of team to be removed: ");
        String teamID = scan.next();
        try {
          Statement stmtT = conn.createStatement();
          stmtT.execute("DELETE FROM team WHERE team_id = " + teamID + ";");
          System.out.println("Team removed.");
        } catch (SQLException e) {
          System.out.println("Error in remove.");
          break;
        }
        break;
    }
	  
  }

        // Method to search for an object in the database
  public void search(String object) {

    switch(object) {
      case "athlete":
        System.out.print("Search by name, sport, or ID?: ");
        String searchParamA = scan.next().toLowerCase();
        athleteSearch(searchParamA);
        break;
      case "coach":
        System.out.print("Search by name or ID?: ");
        String searchParamC = scan.next().toLowerCase();
        coachSearch(searchParamC);
        break;
      case "game":
    	  System.out.print("Search by sport or ID?: ");
          String searchParamG = scan.next().toLowerCase();
          gameSearch(searchParamG);
    	  break;
      case "team":
    	  System.out.print("Search by name or ID?: ");
          String searchParamT = scan.next().toLowerCase();
          teamSearch(searchParamT);
          break;
    }
  }

    // Method to search for an athlete
  void athleteSearch(String searchParam) {
    ResultSet rs = null;
    switch(searchParam) {
      case "name":
        System.out.print("Name to search: ");
        String name = scan.next();
        try {
          Statement stmtN = conn.createStatement();
          rs = stmtN.executeQuery("CALL name_search('" + name + "');");
         
        } catch (SQLException e) {
          System.out.println("Error in search.");

          return;
        }
        break;
      case "sport":
        System.out.print("Sport to search: ");
        String sport = scan.next();
        try {
          Statement stmtS = conn.createStatement();
          rs = stmtS.executeQuery("CALL return_sport('" + sport + "');");
        } catch (SQLException e) {
          System.out.println("Error in search.");
          return;
        }
        break;
      case "id":
        System.out.print("ID to search: ");
        String athleteID = scan.next();
        try {
          Statement stmtI = conn.createStatement();
          rs = stmtI.executeQuery("SELECT athlete_id, athlete_name FROM athlete WHERE athlete_id = " + athleteID + ";");
        } catch (SQLException e) {
          System.out.print("Error in search.");
          return;
        }
        break;
    }

    String Aid;
    String Aname;

    try {
      while (rs.next()) {
        Aid = rs.getString("athlete_id");
        Aname = rs.getString("athlete_name");

        System.out.println("Athlete #" + Aid + ": " + Aname);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Error in search print.");
    }
  }

    // Method to search for a coach
  void coachSearch(String searchParam) {
    ResultSet rs = null;
    switch (searchParam) {
      case "name":
        System.out.print("Name to search: ");
        String name = scan.next();
        try {
          Statement stmtN = conn.createStatement();
          rs = stmtN.executeQuery("CALL coach_search('" + name + "');");
        } catch (SQLException e) {
          System.out.println("Error in search.");
          return;
        }
        break;
      case "id":
        System.out.print("ID to search: ");
        String id = scan.next();
        try {
          Statement stmtI = conn.createStatement();
          rs = stmtI.executeQuery("SELECT coach_id, coach_name FROM coach WHERE coach_id = " + id + ";");
        } catch (SQLException e) {
          System.out.println("Error in search.");
          return;
        }
        break;
    }

    String coachID;
    String coachName;


    try {
      while (rs.next()) {
        coachID = rs.getString("coach_id");
        coachName = rs.getString("coach_name");

        System.out.println("Coach #" + coachID + ": " + coachName);
      }
    } catch (SQLException e) {
      System.out.println("Error in search print.");
      return;
    }
  }

    // Method to search for a team
  void teamSearch(String searchParam) {
	  ResultSet rs = null;
	    switch (searchParam) {
	      case "name":
	        System.out.print("Name to search: ");
	        String sport = scan.next();
	        try {
	          Statement stmtZ = conn.createStatement();
	          rs = stmtZ.executeQuery("CALL team_search('" + sport + "');");
	        } catch (SQLException e) {
	          System.out.println("Error in search.");
	          return;
	        }
	        break;
	      case "id":
	        System.out.print("ID to search: ");
	        String id = scan.next();
	        try {
	          Statement stmtK = conn.createStatement();
	          rs = stmtK.executeQuery("SELECT team_id, sport FROM team WHERE team_id = " + id + ";");
	        } catch (SQLException e) {
	          System.out.println("Error in search.");
	          return;
	        }
	        break;
	    }

	    String teamID;
	    String teamName;


	    try {
	      while (rs.next()) {
	        teamID = rs.getString("team_id");
	        teamName = rs.getString("sport");

	        System.out.println("Team #" + teamID + ": " + teamName);
	      }
	    } catch (SQLException e) {
	      System.out.println("Error in search print.");
	      return;
	    }
	    //break;
	    
  }
 
    // Method to search for a game
  void gameSearch(String searchParam) {
	  ResultSet rs = null;
	    switch (searchParam) {
	      case "id":
	        System.out.print("ID to search: ");
	        String game_id = scan.next();
	        try {
	          Statement stmtE = conn.createStatement();
	          rs = stmtE.executeQuery("SELECT game_id, location FROM game WHERE game_id = " + game_id + ";");
	        } catch (SQLException e) {
	          System.out.println("Error in search.");
	          return;
	        }
	        break;
	      
	    }

	    String gameID;
	    String location;
	    //String teamName;


	    try {
	      while (rs.next()) {
	        gameID = rs.getString("game_id");
	      //  teamName = rs.getString("team_name");
	        location = rs.getString("location");

	        System.out.println("Game # " + gameID + " " + location); 
	      }
	    } catch (SQLException e) {
	      System.out.println("Error in search print.");
	      return;
	  }
  }
 
    // Runs the program
  public static void main(String[] args) {
    AthleticsDatabase app = new AthleticsDatabase();
    app.run();
  }

  


}

