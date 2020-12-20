public class Team1vsTeam2 {

    static double Team1PF, Team1PA, Team1YPG, Team1YAPG, Team1SOS, Team2PF, Team2PA, Team2YPG, Team2YAPG, Team2SOS;
    static String statsPath = "/Users/adityakhanna/Desktop/Football/";
    static String sosPath = statsPath + "SOS.xlsx";
    static String end = "Stats.xlsx";
    static String team1File = statsPath + "Packers" + end;
    static String team2File = statsPath + "Panthers" + end;
    static String team1 = team1File.substring(team1File.indexOf("ll") + 3, team1File.indexOf("Stats"));
    static String team2 = team2File.substring(team2File.indexOf("ll") + 3, team2File.indexOf("Stats"));
    public static void main( String[] args ){

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

    public static double Team1Points(){
        if ( Team1PF > Team2PA )
            return Team1SOS*Math.pow(Team1PF, 2)/Team2PA;
        return Team1PF*Team1SOS;
    }

    public static double Team2Points(){
        if ( Team2PF > Team1PA )
            return Team2SOS*Math.pow(Team2PF, 2)/Team1PA;
        return Team2PF*Team2SOS;
    }

    public static double Team1Yards(){
        if ( Team1YPG > Team2YAPG )
            return (Team1YPG + Team2YAPG)/2;
        return Team1YPG;
    }

    public static double Team2Yards(){
        if ( Team2YPG > Team1YAPG )
            return (Team2YPG + Team1YAPG)/2;
        return Team2YPG;
    }

    public static void prediction(){
        if ( Team1Points() > Team2Points() )
            System.out.println("The " + team1 + " vs " + team2 + " score will be: " + String.format("%.2f", Team1Points()) +
                    " to " + String.format("%.2f", Team2Points()) +
                    "\nThe Point Difference is " + String.format("%.2f", Team1Points() - Team2Points()) +
                    "\nThe Total Points Scored is " + String.format("%.2f", Team1Points() + Team2Points()) +
                    "\nThe " + team1 + " vs " + team2 + " yards will be: " +
                    String.format("%.2f", Team1Yards()) + " to " + String.format("%.2f", Team2Yards()));
        else
            System.out.println("The " + team2 + " vs " + team1 + " score will be: " + String.format("%.2f", Team2Points()) +
                    " to " + String.format("%.2f", Team1Points()) +
                    "\nThe Point Difference is " + String.format("%.2f", Team2Points() - Team1Points()) +
                    "\nThe Total Points Scored is " + String.format("%.2f", Team1Points() + Team2Points()) +
                    "\nThe " + team2 + " vs " + team1 + " yards will be: "
                    + String.format("%.2f", Team2Yards()) + " to " + String.format("%.2f", Team1Yards()));
    }
}
