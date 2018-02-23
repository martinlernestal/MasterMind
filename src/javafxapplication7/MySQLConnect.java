/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import javafx.scene.paint.Color;
/**
 *
 * @author Elev
 */
public class MySQLConnect {

    private static MySQLConnect instance = null;
    private Connection conn;
    private Statement statement, statement2;
    private String url = "jdbc:mysql://localhost:3306/mastermind";
    private String user = "root";
    private String passwd = "password";
    private ArrayList<Game> recreatedGames = new ArrayList<>();
    
    private MySQLConnect(){
        try {
            conn = DriverManager.getConnection(url, user, passwd);
            // här är själva connectionen till databasen
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public static MySQLConnect connect(){
        if(instance == null){
            instance = new MySQLConnect();
        }
        return instance;
    }
    
    public ArrayList<PropertyRenderedGame> getHighScore() throws SQLException{
    
        ArrayList<PropertyRenderedGame> returnAL = new ArrayList<>();

        String query = "select * from games order by score";
        statement = instance.conn.createStatement();
        ResultSet res = statement.executeQuery(query);

        while(res.next()){
            returnAL.add(new PropertyRenderedGame(res.getString("userName"), res.getInt("score"), res.getTimestamp("endDate")));
        }
        statement.close();
        return returnAL;
    }
    
    
    public Game recreateGame(Timestamp gameTimeStamp) throws SQLException{
    
        String queryGames = "SELECT * FROM games LEFT JOIN rounds "
                + "ON games.endDate=rounds.timeofgame "
                + "WHERE games.endDate=? "
                + "ORDER BY rounds.id DESC";

        PreparedStatement pstmt = instance.conn.prepareStatement(queryGames);
        pstmt.setTimestamp(1, gameTimeStamp);

        ResultSet res = pstmt.executeQuery();
        
        ArrayList<String[]> allColorGuesses = new ArrayList<>();
        ArrayList<String[]> allColorResults = new ArrayList<>();
    
        ArrayList<Round> rounds = new ArrayList<>();
        
        ArrayList<Color[]> colorGuessRow = new ArrayList<>();
        ArrayList<Color[]> colorResultsRow = new ArrayList<>();

        ArrayList<String> colorGuessed = new ArrayList<>();
        ArrayList<String> colorResults = new ArrayList<>();
        ArrayList<Timestamp> roundGuessTime = new ArrayList<>();
        
        Timestamp currGame = new Timestamp(0);
        Timestamp testGameVar = new Timestamp(0);
        
        long currPlayTime = 0;
        String currUser = "";
        int currScore = 0;
        ArrayList<Color> currCompListColors = new ArrayList<>();
        
        while(res.next()){
            System.out.println("inne i while-loopen");
            if(currGame.equals(testGameVar)){
                currGame = new Timestamp(res.getTimestamp("endDate").getTime());
                currPlayTime = res.getLong("playTime");
                currUser = res.getString("userName");
                currScore = res.getInt("score");
                currCompListColors.add(Color.web(res.getString("color1")));
                currCompListColors.add(Color.web(res.getString("color2")));
                currCompListColors.add(Color.web(res.getString("color3")));
                currCompListColors.add(Color.web(res.getString("color4")));
            }
            roundGuessTime.add(res.getTimestamp("timeofguess"));
            colorGuessed.add(res.getString("guessedcolors"));
            colorResults.add(res.getString("resultcolors"));
        }

        // NU ÄR DEN DESCENDING.... eftersom iv har addat så blir allt spegelvänt

        for(String currString : colorGuessed){
            allColorGuesses.add(currString.split("\\@"));
        }
        for(String currString : colorResults){
            allColorResults.add(currString.split("\\@"));
        }
        
        int i = 0;
        for(String[] currStringArray : allColorGuesses){
            Color[] tmpColorHolder = new Color[currStringArray.length];
            i = 0;
            for(String currString: currStringArray){
                // här kan man omvandla till färg!
                tmpColorHolder[i] = Color.web(currString);
                i++;
            }
            // här har vi en row med färger i arrayform
            colorGuessRow.add(tmpColorHolder);
            // här kan man adda till arraylisten
        }
        for(String[] currStringArray : allColorResults){
            Color[] tmpColorHolder = new Color[currStringArray.length];
            i = 0;
            for(String currString: currStringArray){
                // här kan man omvandla till färg
                tmpColorHolder[i] = Color.web(currString);
                i++;
            }
            // här har vi en row med färger i arrayform
            // här kan man adda till arraylisten
            colorResultsRow.add(tmpColorHolder);
        }
            
        // resultrown är en kortare än guessrow så det kan bli nullpointer exception        
                
        for(i = 0; i < colorGuessRow.size(); i++){
            if(colorResultsRow.get(i) == null){
                Color[] allBlack = new Color[4];
                for(Color currColor : allBlack){
                    currColor = Color.BLACK;
                }
                // så ska alla vara svarta...
                // för nu borde dom vara spegelvända igen
                rounds.add(new Round(colorGuessRow.get(i), allBlack, roundGuessTime.get(i)));
            }
            rounds.add(new Round(colorGuessRow.get(i), colorResultsRow.get(i), roundGuessTime.get(i)));
        }

        Game returnGame = new Game((int) currPlayTime, currGame, currUser, currCompListColors, rounds);

        return returnGame;
    }
    
    public void close() throws SQLException{
    
        if(instance != null){
            instance.conn.close();
        }
    }
    
    public int insertNewGame(Game newGame) throws SQLException{
    
        PreparedStatement stmt= instance.conn.prepareStatement("INSERT INTO games (endDate, playTime, userName, score, color1, color2, color3, color4) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setTimestamp(1, newGame.getEndTime());
        // PLAYTIME FÅR GENERERAS under spelets gång, typ att man 
        stmt.setLong(2, newGame.getPlayTime());
        stmt.setString(3, newGame.getUser());
        stmt.setInt(4, newGame.getScore());
        
        int i = 5;
        for(Color currColor: newGame.getComputerColors()){
            stmt.setString(i, currColor.toString());
            i++;
        }
        
        int insertStatus = stmt.executeUpdate();
        
        return insertStatus;
        
    }
    
    public void insertNewRound(Timestamp timeOfGame, Round round) throws SQLException{
    
        
        
        String guessedColors = "";
        String resultColors = "";
        
        for(Color currColor: round.getGuess()){
            guessedColors += currColor.toString() + "@";
        }
        if(round.getResult()!=null){
            // om den är tom så är det för att det är första rundan...
            for(Color currColor: round.getResult()){
                resultColors += currColor.toString() + "@";
            }
        } else {
        
            resultColors = Color.BLACK.toString() + "@" + 
                    Color.BLACK.toString() + "@" + 
                    Color.BLACK.toString() + "@" + 
                    Color.BLACK.toString() + "@";
        
        }
        PreparedStatement stmt = instance.conn.prepareStatement("INSERT INTO rounds (timeofgame, timeofguess, guessedcolors, resultcolors) VALUES (?, ?, ?, ?)");
        stmt.setTimestamp(1, timeOfGame);
        stmt.setTimestamp(2, round.getTime());
        stmt.setString(3, guessedColors);
        stmt.setString(4, resultColors);
        stmt.executeUpdate();
    
    }
    
}
