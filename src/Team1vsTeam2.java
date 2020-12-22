import java.util.*;

public class Team1vsTeam2 {

    private static double Team1PF, Team1PA, Team1YPG, Team1YAPG, Team1SOS, Team1Points, Team1Yards, Team2PF, Team2PA, Team2YPG, Team2YAPG, Team2SOS, Team2Points, Team2Yards;
    private static String statsPath = "./Stats/";
    private static String statsWeek = "Week 12/";
    private static String sosPath = statsPath + "SOS.xlsx";
    private static String end = "Stats.xlsx";
    private static String team1, team2, team1File, team2File;

    //TODO update stats sheets for next week or find way to automatically pull
    //TODO add functionality for user to enter which week they want to view a matchup for

    public static void main( String[] args ){

        Scanner console = new Scanner(System.in);
        System.out.print("What is the name of the first team? ");
        team1 = console.next();
        team1File = statsPath + statsWeek + team1 + end;
        System.out.print("What is the name of the second team? ");
        team2 = console.next();
        team2File = statsPath + statsWeek+ team2 + end;

        Team Team1 = new Team(team1File, sosPath, team1);
        Team1PF = Team1.TeamPF;
        Team1PA = Team1.TeamPA;
        Team1YPG = Team1.TeamYPG;
        Team1YAPG = Team1.TeamYAPG;
        Team1SOS = Team1.TeamSOS;

        Team Team2 = new Team(team2File, sosPath, team2);
        Team2PF = Team2.TeamPF;
        Team2PA = Team2.TeamPA;
        Team2YPG = Team2.TeamYPG;
        Team2YAPG = Team2.TeamYAPG;
        Team2SOS = Team2.TeamSOS;

        prediction();
    }

    //TODO Use yardage and effective drive percentage to update score for accuracy
    //TODO Use average points to update score based on closest score to get whole number estimates
    //TODO Create GUI interface for user to select teams

    public static double ratioAlgo( double team1Stat, double team2Stat, double teamSOS) {
        if( team1Stat > team2Stat )
            return teamSOS*Math.pow(team1Stat, 2)/team2Stat;
        return teamSOS*team1Stat;
    }

    public static double average( double firstNum, double secondNum ) {
        return (firstNum+secondNum)/2;
    }

    public static double avgAlgo( double team1Stat, double team2Stat, double teamSOS ) {
        if( team1Stat > team2Stat )
            return teamSOS*average(team1Stat, team2Stat);
        return teamSOS*team1Stat;
    }


    public static void prediction(){
        Team1Points = average( ratioAlgo( Team1PF, Team2PA, Team1SOS ), avgAlgo( Team1PF, Team2PA, Team1SOS ) );
        Team2Points = average( ratioAlgo( Team2PF, Team1PA, Team2SOS ), avgAlgo( Team2PF, Team1PA, Team2SOS ) );
        Team1Yards = average( ratioAlgo( Team1YPG, Team2YAPG, Team1SOS ), avgAlgo( Team1YPG, Team2YAPG, Team1SOS ) );
        Team2Yards = average( ratioAlgo( Team2YPG, Team1YAPG, Team2SOS ), avgAlgo( Team2YPG, Team1YAPG, Team2SOS ) );
        if ( Team1Points > Team2Points )
            System.out.println("The " + team1 + " vs " + team2 + " score will be: " + String.format("%.2f", Team1Points) +
                    " to " + String.format("%.2f", Team2Points) +
                    "\nThe Point Difference is " + String.format("%.2f", Team1Points - Team2Points) +
                    "\nThe Total Points Scored is " + String.format("%.2f", Team1Points + Team2Points) +
                    "\nThe " + team1 + " vs " + team2 + " yards will be: " +
                    String.format("%.2f", Team1Yards) + " to " + String.format("%.2f", Team2Yards));
        else
            System.out.println("The " + team2 + " vs " + team1 + " score will be: " + String.format("%.2f", Team2Points) +
                    " to " + String.format("%.2f", Team1Points) +
                    "\nThe Point Difference is " + String.format("%.2f", Team2Points - Team1Points) +
                    "\nThe Total Points Scored is " + String.format("%.2f", Team1Points + Team2Points) +
                    "\nThe " + team2 + " vs " + team1 + " yards will be: "
                    + String.format("%.2f", Team2Yards) + " to " + String.format("%.2f", Team1Yards));
    }
}