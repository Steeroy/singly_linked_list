/*
Siyanda Mvunyiswa
Linked list
*/

import java.util.Scanner;

public class TournamentEntry {
    static Node head;
    static Scanner scan = new Scanner(System.in);
    static int size;

    //This addTeam method adds the team to the linked list when you register the team
    public static void addTeam(Team team){
        Node node = new Node();
        node.team = team;
        node.nextNode = null;

        if(head == null){
            head = node;
        }

        else{
            Node current = head;
            while(current.nextNode != null){
                current = current.nextNode;
            }
            current.nextNode = node;
        }
        size++;
    }

    //This removeLast method, removes the last team from the linked list, when you deregister the last team
    public static String removeLast(){
        if(head.nextNode == null){
            Node temp = head;
            head = null;
            size--;
            return temp.team.teamName;
        }
        else{
            Node current = head;
            while(current.nextNode.nextNode != null){
                current = current.nextNode;
            }
            Node temp = current.nextNode;
            current.nextNode = null;
            size--;
            return temp.team.teamName;
        }
    }

    //This removeParticular method removes the chosen team from the linked list and returns the team name
    public static String removeParticular(int teamNumber){
        if(head.team.teamNumber == teamNumber){
            Node temp = head;
            head = head.nextNode;
            size--;
            return temp.team.teamName;
        }
        else {
            int index = 0;
            Node current = head;
            Node temp = head;
            while (index < size) {
                if (current.nextNode.team.teamNumber == teamNumber) {
                    temp = current.nextNode;
                    current.nextNode = current.nextNode.nextNode;
                    size--;
                    break;
                } else {
                    current = current.nextNode;
                }
            }
            return temp.team.teamName;
        }
    }

    /*This addNewTeam method adds the new registered team before the team with the chosen team number,
    * if it is not found, then it adds the new team at the end of the list*/
    public static void addNewTeam(Team team, int teamNumber){
        Node node = new Node();
        node.team = team;

        Node current = head;
        while(current.nextNode != null){
            if(current.nextNode.team.teamNumber == teamNumber){
                Node temp = current.nextNode;
                current.nextNode = node;
                node.nextNode = temp;
                size++;
                break;
            }
            current = current.nextNode;
        }
        if(current.nextNode == null){
            addTeam(team);
            size++;
        }
    }

    //This Display method displays the teams in the linked list
    public static void Display(){
        Node current = head;
        while(current.nextNode != null){
            System.out.println(current.team.teamName + " "
            + current.team.teamNumber + " " + current.team.regYear+
                    " " + current.team.firstScore + " " +
                    current.team.secondScore + " " + current.team.finalScore);
            current = current.nextNode;
        }
        System.out.println(current.team.teamName + " "
                + current.team.teamNumber + " " + current.team.regYear+
                " " + current.team.firstScore + " " +
                current.team.secondScore + " " + current.team.finalScore);
    }

    //This method uses creates the team and uses the addTeam method to add it on the linked list
    public static void registerTeam(){
        Team team = new Team();
        team.teamName = scan.next();
        team.teamNumber = scan.nextInt();
        team.regYear = scan.nextLong();
        team.firstScore = scan.nextInt();
        team.secondScore = scan.nextInt();
        team.finalScore =  (((double) team.firstScore +team.secondScore)/(double) 2);
        addTeam(team);
    }

    //This method deregisters the last team using the removeLast method
    public static void deregister_Last_team(){
        System.out.println("The last team " + removeLast() + " has been removed");
    }

    //this method deregisters a particular team on the list using the give team number
    //it uses the removeParticular method to remove the team
    public static void deregister_Particular_team(int teamNumber){
        System.out.println(removeParticular(teamNumber));
    }

    //this insertBefore method inserts the new team before a particular team and uses
    //the addNewTeam method to add the team.
    public static void insertBefore(){
        Team team = new Team();
        System.out.println("Please enter the Team name, team number, register year, first score and second score:");
        team.teamName = scan.next();
        team.teamNumber = scan.nextInt();
        team.regYear = scan.nextLong();
        team.firstScore = scan.nextInt();
        team.secondScore = scan.nextInt();
        team.finalScore =  (((double) team.firstScore +team.secondScore)/(double) 2);
        System.out.println("Please enter the team number you want to put it before:");
        int teamNumber = scan.nextInt();
        addNewTeam(team, teamNumber);
    }

    //this method checks the number of teams in the linked list
    public static int tournamentEntry(){
        return size;
    }

    //this method sorts the linked list in different ways
    //It can choose depending on the user to sort by team name, number, register year or final score
    //it uses multiple sorting methods to sort this linked list
    public static void sorting(int number){
        Team[] team = new Team[size];
        int ref = size;
        Node current = head;
        for(int i = 0; i < size; i++){
            team[i] = current.team;
            current = current.nextNode;
        }
        int j = 0;
        while(j < ref){
            removeLast();
            j++;
        }
        if(number == 1){
            inSortTeamName(team);
        }
        else if(number == 2){
            inSortRegYear(team);
        }
        else if(number == 3){
            inSortTeamNumber(team);
        }
        else if(number == 4){
            inSortFinal(team);
        }

        int k = 0;
        while(k < team.length){
            addTeam(team[k]);
            k++;
        }
    }

    //this method sort the team by final scores
    public static void inSortFinal(Team[] team){
        for(int i = 1; i < team.length; i++){
            Team current = team[i];
            int j = i -1;
            while(j >= 0 && team[j].finalScore < current.finalScore){
                if(team[j].finalScore == current.finalScore){
                    if(team[j].teamName.equals(current.teamName)){
                        if(team[j].teamNumber < current.teamNumber){
                            continue;
                        }
                        else{
                            team[j+1] = team[j];
                        }
                    }

                    else{
                        char teamRef = team[j].teamName.charAt(0);
                        char team2 = current.teamName.charAt(0);
                        if(teamRef > team2){
                            team[j + 1] = team[j];
                        }
                        else{
                            break;
                        }
                    }
                }
                else{
                    team[j + 1] = team[j];
                }
                j--;
            }
            team[j+1] = current;
        }
    }

    //this method sorts the linked list by teams
    public static void inSortTeamName(Team[] team){
        for(int i = 1; i < team.length; i++){
            Team current = team[i];
            int j = i -1;
            while(j >= 0 && team[j].teamName.charAt(0) >= current.teamName.charAt(0)){

                if(team[j].teamName.charAt(0) == current.teamName.charAt(0)){
                    if(team[j].teamNumber < current.teamNumber){
                        continue;
                    }
                    else{
                        team[j+1] = team[j];
                    }
                }
                else {
                    team[j + 1] = team[j];
                }

                j--;
            }
            team[j+1] = current;
        }
    }

    //this method sorts the linked list by register years
    public static void inSortRegYear(Team[] team){
        for(int i = 1; i < team.length; i++){
            Team current = team[i];
            int j = i -1;
            while(j >= 0 && team[j].regYear >= current.regYear){

                if(team[j].regYear == current.regYear){
                    if(team[j].teamNumber < current.teamNumber){
                        continue;
                    }
                    else{
                        team[j+1] = team[j];
                    }
                }
                else {
                    team[j + 1] = team[j];
                }

                j--;
            }
            team[j+1] = current;
        }
    }

    //this method sorts the linked by team numbers
    public static void inSortTeamNumber(Team[] team){
        for(int i = 1; i < team.length; i++){
            Team current = team[i];
            int j = i -1;
            while(j >= 0 && team[j].teamNumber > current.teamNumber){
                team[j + 1] = team[j];
                j--;
            }
            team[j+1] = current;
        }
    }

    public static void main(String[] args){
        System.out.println("Hello, please input the number of teams you want to register:");
        int numTeams = scan.nextInt();
        int i = 0;
        System.out.println("Please enter the Team name(with up to 15 characters), team number(between 1 and 1000), register year, first and last scores(both between 0 and 100) separated by spaces, and press enter to register another team: E.g Siyanda 9 2018 45 76");
        while(i < numTeams){
            registerTeam();
            i++;
        }
        System.out.println("These are the teams you registered:");
        Display();
        System.out.println();

        System.out.println("Please enter the attribute you want to sort your list by:");
        System.out.println("E.g regYear(by register years from old to new), finalScore(from high final score to low final score), " +
                "teamName(in order of first letter) and teamNumber(from lowest team number to highest team number");
        String inputUser = scan.next();

        int num;
        switch (inputUser.toLowerCase()) {
            case "teamname" -> {
                num = 1;
                sorting(num);
            }
            case "regyear" -> {
                num = 2;
                sorting(num);
            }
            case "teamnumber" -> {
                num = 3;
                sorting(num);
            }
            case "finalscore" -> {
                num = 4;
                sorting(num);
            }
        }
        System.out.println();
        System.out.println("The sorted list is:");
        Display();
    }
}