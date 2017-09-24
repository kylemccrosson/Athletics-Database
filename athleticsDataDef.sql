CREATE SCHEMA IF NOT EXISTS athletics;
USE athletics;

CREATE TABLE IF NOT EXISTS team (
  team_id      INT     PRIMARY KEY   AUTO_INCREMENT,
  sport        VARCHAR(45)   NOT NULL,
  gender       ENUM('men', 'women'),
  num_members  INT,
  division     VARCHAR(20),
  wins         INT     NOT NULL,
  losses       INT     NOT NULL
);

CREATE TABLE IF NOT EXISTS coach (
  coach_id          INT     PRIMARY KEY     AUTO_INCREMENT,
  coach_name        VARCHAR(45)     NOT NULL,
  coach_position    VARCHAR(45)     NOT NULL,
  team_id           INT     NOT NULL,
  CONSTRAINT coach_team_fk
    FOREIGN KEY (team_id) REFERENCES team (team_id)
);


CREATE TABLE IF NOT EXISTS game (
  game_id       INT     PRIMARY KEY     AUTO_INCREMENT,
  location      VARCHAR(45),
  game_date     VARCHAR(20)     NOT NULL, 
  game_time     TIME     NOT NULL,
  team_id       INT      NOT NULL,
  CONSTRAINT game_team_fk
    FOREIGN KEY (team_id) REFERENCES team (team_id)
);

CREATE TABLE IF NOT EXISTS athlete (
  athlete_id     INT     PRIMARY KEY     AUTO_INCREMENT,
  athlete_name   VARCHAR(45)     NOT NULL,
  home_town     VARCHAR(2),
  school_year    VARCHAR(10),
  height         VARCHAR(10),
  team_id        INT     NOT NULL,
  CONSTRAINT athlete_team_fk
    FOREIGN KEY (team_id) REFERENCES team (team_id)
);

CREATE TABLE IF NOT EXISTS baseball_player (
  player_position     VARCHAR(45),
  bats                ENUM('left', 'right'),
  throws              ENUM('left', 'right'),
  athlete_id          INT   UNIQUE   NOT NULL,
  CONSTRAINT baseball_athlete_fk
    FOREIGN KEY (athlete_id) REFERENCES athlete (athlete_id)
);


CREATE TABLE IF NOT EXISTS ice_hockey_player (
  player_position     VARCHAR(45),
  shoots              ENUM('left', 'right'),
  athlete_id          INT   UNIQUE   NOT NULL,
  CONSTRAINT ice_hockey_athlete_fk
    FOREIGN KEY (athlete_id) REFERENCES athlete (athlete_id)
);

CREATE TABLE IF NOT EXISTS swim_dive_track_field (
  player_event     VARCHAR(45),
  athlete_id       INT   UNIQUE   NOT NULL,
  CONSTRAINT swim_dive_athlete_fk
    FOREIGN KEY (athlete_id) REFERENCES athlete (athlete_id)
);

CREATE TABLE IF NOT EXISTS basketball_soccer_fieldhockey_volleyball (
  player_position     VARCHAR (45),
  athlete_id          INT   UNIQUE   NOT NULL,
  CONSTRAINT bsfv_athlete_fk
    FOREIGN KEY (athlete_id) REFERENCES athlete (athlete_id)
);

-- Returns the names of all the athletes, male and female, who play a given sport.
DROP PROCEDURE IF EXISTS return_sport;
DELIMITER $$
CREATE PROCEDURE return_sport(given_sport VARCHAR(45))
  BEGIN 
	
  SELECT athlete_id, athlete_name 
    FROM athlete JOIN team ON team.team_id = athlete.team_id
    WHERE sport LIKE CONCAT('%', given_sport, '%');
	
  END$$
DELIMITER ;

-- Adjusts the number of players on a team appropriately
DROP TRIGGER IF EXISTS increase_team;
DELIMITER $$
CREATE TRIGGER increase_team
  AFTER INSERT ON athlete
  FOR EACH ROW
  BEGIN
    DECLARE team_to_inc INT;
    
    SELECT NEW.team_id INTO team_to_inc;
    
    UPDATE team
      SET num_members = num_members + 1
      WHERE team_id = team_to_inc;
	END $$
DELIMITER ;

DROP TRIGGER IF EXISTS decrease_team;
DELIMITER $$
CREATE TRIGGER decrease_team
  AFTER DELETE ON athlete 
  FOR EACH ROW
  BEGIN
    DECLARE team_to_dec INT;
    
    SELECT OLD.team_id INTO team_to_dec;
    
    UPDATE team
      SET num_members = num_members - 1
      WHERE team_id = team_to_dec;
	END $$
DELIMITER ;

-- Returns the names of all the players on a given team
DROP PROCEDURE IF EXISTS roster;
DELIMITER $$
CREATE PROCEDURE roster(sport_name VARCHAR(45), team_gender VARCHAR(45))
  BEGIN
    SELECT athlete_name
      FROM athlete JOIN team ON athlete.team_id = team.team_id
      WHERE sport = sport_name AND team_gender = gender;
	END $$
DELIMITER ;

-- Returns the names of all the players that match a name search
DROP PROCEDURE IF EXISTS name_search;
DELIMITER $$
CREATE PROCEDURE name_search(given_name VARCHAR(45))
  BEGIN
    SELECT athlete_id, athlete_name
      FROM athlete
      WHERE athlete_name LIKE CONCAT('%', given_name, '%');
	END $$
DELIMITER ;

-- Returns the names of all the coaches that match a name search
DROP PROCEDURE IF EXISTS coach_search;
DELIMITER $$
CREATE PROCEDURE coach_search(given_name VARCHAR(45))
  BEGIN
    SELECT coach_id, coach_name 
    FROM coach
    WHERE coach_name LIKE CONCAT('%', given_name, '%');
	END$$
DELIMITER ;


