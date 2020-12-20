public class Team1vsTeam2 {

    private static double Team1PF, Team1PA, Team1YPG, Team1YAPG, Team1SOS, Team1Points, Team1Yards, Team2PF, Team2PA, Team2YPG, Team2YAPG, Team2SOS, Team2Points, Team2Yards;
    private static String statsPath = "./Stats/";
    private static String statsWeek = "Week 12/";
    private static String sosPath = statsPath + "SOS.xlsx";
    private static String end = "Stats.xlsx";
    private static String team1 = "49ers";
    private static String team2 = "Cowboys";
    private static String team1File = statsPath + statsWeek + team1 + end;
    private static String team2File = statsPath + statsWeek+ team2 + end;

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

    //TODO Use Scanner to ask user what teams they'd like to compare
    //TODO Use an average (team1PF + team2PA)/2 and then average that with the ratio algo to get more accuracy

    public static double ratioAlgo( double team1Stat, double team2Stat, double teamSOS) {
        if( team1Stat > team2Stat )
            return teamSOS*Math.pow(team1Stat, 2)/team2Stat;
        return teamSOS*team1Stat;
    }

    public static void prediction(){
        Team1Points = ratioAlgo( Team1PF, Team2PA, Team1SOS );
        Team2Points = ratioAlgo( Team2PF, Team1PA, Team2SOS );
        Team1Yards = ratioAlgo( Team1YPG, Team2YAPG, Team1SOS );
        Team2Yards = ratioAlgo( Team2YPG, Team1YAPG, Team2SOS );
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