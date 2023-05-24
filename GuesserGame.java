package ineuronAssesment3;

import java.util.Scanner;


/* Enhancements I did:
 * 
 * 1.We can give range for which we can select min value and max value.
 * 2.If Guessser , guesses out of range we will give him another chance.
 * 3.we can have give input for no of players(No of players not constant).
 * 4.If any player guess out of range we will ask only respective person to guess again.
 * 5.A enhancement provided to ask that he want to play next game or not.
 * 6.A person can select "NoOfRounds" he want to play and then finally after all rounds 
 *   display all members score and accounce the winner.
 * 		
 */
class Guesser{
	int guessNum;
	int guessNum() {
		Scanner sc= new Scanner(System.in);
		//System.out.println("Guesser pls guess the number:");
		int guessNum = sc.nextInt();
		return guessNum;
		
	}
}

class Player{
	int playerGuessNum;
	int playerGuessNum() {
		Scanner sc= new Scanner(System.in);
		int playerGuessNum = sc.nextInt();
		return playerGuessNum;
	}
}
class Umpire{
	int numfromguesser;
	int playernum[];
	int noofpalyers;
	int scoreboard[];
	int collectNumfromGuesser(int min,int max) {
		Guesser g = new Guesser();
		System.out.println("Guesser Please guess in given range "+min+" and "+max);
		numfromguesser=g.guessNum();
		if(numfromguesser<min || numfromguesser>max) {
			System.out.println("OUT OF RANGE TRY AGAIN");
			collectNumfromGuesser(min,max);
		}
		return numfromguesser;
	}
	void playerchoice(int i,int[] playernum,int min,int max) {
		Player p= new Player();
		System.out.println("Player"+(i+1)+" guess the number guessed by Guesser:");
		playernum[i]=p.playerGuessNum();
		if(playernum[i]<min || playernum[i]>max) {
			System.out.println("Guessed number is out of given range TRY AGAIN");
			playerchoice(i,playernum,min,max);
		}
	}
	int[] collectnumfromPlayers(int min,int max,int noofpalyers) {
		int playernum[] = new int[noofpalyers];
		for(int i=0;i<noofpalyers;i++) {
			playerchoice(i,playernum,min,max);
		}
		return playernum;
	}
	int[] compare(int numfromguesser,int[] playernum,int noofrounds) {
		String winners="";
		int count=0;
		int scoreboard[] = new int[playernum.length];
		for(int i=0;i<playernum.length;i++) {
			if(playernum[i]==numfromguesser) {
				scoreboard[i]=1;
				winners = winners+(i+1)+" ";
				count++;
			}
		}
		if(noofrounds==1) {
			if(count==0)
				System.out.println("Game Lost by all");
			else if(count==1)
				System.out.println("Winner is player:"+winners);
			else
				System.out.println("Winners are players:"+winners);
		}
		return scoreboard;
	}
}
public class GuesserGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean flag=true;
		Umpire u = new Umpire();
		do {
			System.out.println();
			Scanner sc= new Scanner(System.in);
			System.out.println("1.Play the Game");
			System.out.println("2.Exit");
			System.out.println("Please choose a option above:");
			switch(sc.nextInt()) {
				case 1:
					System.out.println("No of rounds you want to play");
					int noofrounds = sc.nextInt();
					int dupnoofrounds =noofrounds;
					System.out.println("No of players u want:");
					int noofpalyers = sc.nextInt();
					if(noofrounds==1) {
						System.out.println("Range in which guesser num should be");
						System.out.println("Min Value:");
						int minvalue = sc.nextInt();
						System.out.println("Max Value:");
						int maxvalue = sc.nextInt();
						int numfromguesser=u.collectNumfromGuesser(minvalue,maxvalue);
						int[] playernum=u.collectnumfromPlayers(minvalue,maxvalue,noofpalyers);
						int[] scoreboard=u.compare(numfromguesser,playernum,noofrounds);
					}
					else {
						int[] finalscoreboard= new int[noofpalyers];
						int countwinner=0;
						String winners="";
						while(noofrounds>0) {
							System.out.println("Round "+(dupnoofrounds-noofrounds+1));
							System.out.println("Range in which guesser num should be");
							System.out.println("Min Value:");
							int minvalue = sc.nextInt();
							System.out.println("Max Value:");
							int maxvalue = sc.nextInt();
							int numfromguesser=u.collectNumfromGuesser(minvalue,maxvalue);
							int[] playernum=u.collectnumfromPlayers(minvalue,maxvalue,noofpalyers);
							int[] scoreboard=u.compare(numfromguesser,playernum,dupnoofrounds);
							for(int i=0;i<finalscoreboard.length;i++) {
								finalscoreboard[i]=finalscoreboard[i]+scoreboard[i];
							}
							noofrounds--;
						}
						System.out.println("The final score after all rounds for all players is:");
						System.out.println();
						for(int i=0;i<finalscoreboard.length;i++) {
							System.out.println("Player"+(i+1)+" score:"+finalscoreboard[i]+" points");
						}
						int highestscore=finalscoreboard[0];
						int highestindex =0;
						for(int i=1;i<finalscoreboard.length;i++) {
							if(finalscoreboard[i]>highestscore) {
								highestscore=finalscoreboard[i];
								highestindex=i;
							}
						}
						for(int i=0;i<finalscoreboard.length;i++) {
							if(finalscoreboard[i]==highestscore && highestscore!=0) {
								countwinner++;
								winners = winners+(i+1)+" ";
							}
						}
						System.out.println();
						if(countwinner==0)
							System.out.println("Game Lost by all");
						else if(countwinner==1)
							System.out.println("Winner is player:"+winners);
						else
							System.out.println("Winners are players:"+winners);
					}
					break;
				case 2:
					flag=false;
					break;
				default:
					System.out.println("Pls choose a valid option");
			}
		}while(flag==true);
		
	}
}
