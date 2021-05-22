import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class Match{

	Scanner sc = new Scanner(System.in); 
	//Method for CPU to determine the match point situation
	//Method checks if win situation is created and returns the next move position 
	public static int matchPoint(char[][] matchBlock){
		char sign = 'O';
		int round = 0;
		while(round++ < 2){
			//Case 1 Coulmn [x][0] -> filled
			for(int i = 0; i < 3; i++){
				if(matchBlock[i][0] == sign){
					if((matchBlock[i][0] == matchBlock[i][1]) && (matchBlock[i][2] != 'X' && matchBlock[i][2] != 'O')){
						return 10*i + 2;
					}
					else if ((matchBlock[i][0] == matchBlock[i][2]) && (matchBlock[i][1] != 'X' && matchBlock[i][1] != 'O')){
						return 10*i + 1;
					}
				}
			}

			//Case 2 Column[x][0] -> not filled
			for(int i = 0; i < 3; i++){
				if(matchBlock[i][1] == sign){
					if((matchBlock[i][0] != 'X' && matchBlock[i][0] != 'O') && matchBlock[i][1] == matchBlock[i][2])
						return 10*i;
				}
			}

			//Case 3 Row[0][x] -> filled
			for(int i = 0; i < 3; i++){
				if(matchBlock[0][i] == sign){
					if((matchBlock[0][i] == matchBlock[1][i]) && (matchBlock[2][i] != 'X' && matchBlock[2][i] != 'O')){
						return 10*i + 2;
					}
					else if ((matchBlock[0][i] == matchBlock[2][i]) && (matchBlock[1][i] != 'X' && matchBlock[1][i] != 'O')){
						return 10*i + 1;
					}
				}
			}

			//Case 4 Row[0][x] -> not filled
			for(int i = 0; i < 3; i++){
				if(matchBlock[1][i] == sign){
					if((matchBlock[0][i] != 'X' && matchBlock[0][i] != 'O') && matchBlock[1][i] == matchBlock[2][i])
						return 10*i;
				}
			}

			//Case 5 Forward diagonal
			if(matchBlock[0][0] == sign){
				if(matchBlock[0][0] == matchBlock[1][1] && (matchBlock[2][2] != 'X' && matchBlock[2][2] != 'O')){
					return 22;
				}
				else if(matchBlock[0][0] == matchBlock[2][2] && (matchBlock[1][1] != 'X' && matchBlock[1][1] != 'O')){
					return 11;
				}
			}

			if(matchBlock[1][1] == sign){
				if((matchBlock[0][0] != 'X' && matchBlock[0][0] != 'O') && matchBlock[1][1] == matchBlock[2][2])
					return 0;
			}

			//Case 6 Backward diagonal
			if(matchBlock[0][2] == sign){
				if(matchBlock[0][2] == matchBlock[1][1] && (matchBlock[2][0] != 'X' && matchBlock[2][0] != 'O')){
					return 20;
				}
				else if(matchBlock[0][2] == matchBlock[2][0] && (matchBlock[1][1] != 'X' && matchBlock[1][1] != 'O')){
					return 11;
				}
			}

			if(matchBlock[1][1] == sign){
				if((matchBlock[0][2] != 'X' && matchBlock[0][2] != 'O') && matchBlock[1][1] == matchBlock[2][0])
					return 2;
			}

			sign = 'X';
		}

		return -1;
	}
	
	Random rand = new Random();
	public char[][] matchBlock = new char[3][3];	//Creating Game Grid
		

	public char turn = 'X';

	public Match(){
		int assignmentVal = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				this.matchBlock[i][j] = (char)(assignmentVal + '0');
				assignmentVal++;
			}
		}

		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				System.out.print(matchBlock[i][j]);
				if(j != 2){
					System.out.print(" | ");
				}
			}
			if(i != 2)
				System.out.println("\n--  --  --");
			else
				System.out.println("");
		}
	}

	//Method to check if match is won or not
	public static boolean matchWin(char[][] matchBlock){
		for(int i = 0; i < 3; i++){
			if(matchBlock[i][0] == matchBlock[i][1] && matchBlock[i][1] == matchBlock[i][2]){
				return true;
			}
		}

		for(int i = 0; i < 3; i++){
			if(matchBlock[0][i] == matchBlock[1][i] && matchBlock[1][i] == matchBlock[2][i]){
				return true;
			}
		}

		if(matchBlock[0][0] == matchBlock[1][1] && matchBlock[1][1] == matchBlock[2][2]){
			return true;
		}

		if(matchBlock[0][2] == matchBlock[1][1] && matchBlock[1][1] == matchBlock[2][0]){
			return true;
		}

		return false;
	}

	public int PlayerMove(){
		int flag;
		System.out.println("\nPlayer " + turn + " :");
		System.out.println("	=> Enter the Move Locaton [0-8] :");
		int moveInput = sc.nextInt();
		if(this.matchBlock[moveInput/3][moveInput%3] != 'X' && this.matchBlock[moveInput/3][moveInput%3] != 'O'){
			if(this.turn == 'X'){
				this.matchBlock[moveInput/3][moveInput%3] = 'X';
				this.turn = 'O';
			}
			else{
				this.matchBlock[moveInput/3][moveInput%3] = 'O';
				this.turn = 'X';
			}
			flag = 1;
		}
		else{
			System.out.println("\n    => Locaton already Filled, Select a different Locaton :");
			flag = 0;
		}

		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				System.out.print(this.matchBlock[i][j]);
				if(j != 2){
					System.out.print(" | ");
				}
			}
			if(i != 2)
				System.out.println("\n--  --  --");
			else
				System.out.println("");
		}

		return flag;
	}

	public int PlayerVsCPUmove(){
		int flag;
		System.out.println("\nPlayer => Enter the Move Locaton [0-8] :");
		int moveInput = sc.nextInt();
		if(this.matchBlock[moveInput/3][moveInput%3] != 'X' && this.matchBlock[moveInput/3][moveInput%3] != 'O'){
			this.matchBlock[moveInput/3][moveInput%3] = 'X';
			this.turn = 'O';
			flag = 1;
		}
		else{
			System.out.println("\nPlayer => Locaton already Filled, Select a different Locaton :");
			flag = 0;
		}

		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				System.out.print(this.matchBlock[i][j]);
				if(j != 2){
					System.out.print(" | ");
				}
			}
			if(i != 2)
				System.out.println("\n--  --  --");
			else
				System.out.println("");
		}
		return flag;
	}

	public void CPUmove(){
		System.out.println("CPU MOVE =>");
		int cpuMove = matchPoint(this.matchBlock);
		if(cpuMove >= 0){
			this.matchBlock[cpuMove/10][cpuMove%10] = 'O';
			this.turn = 'X';
		}
		else{
			ArrayList<Integer> validLocation = new ArrayList<Integer>();

			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if(this.matchBlock[i][j] != 'X' && this.matchBlock[i][j] != 'O'){
						validLocation.add(i*10 + j);
					}
				}
			}
			int MoveLocation = rand.nextInt(validLocation.size());
			cpuMove = validLocation.get(MoveLocation);
			validLocation.clear();
			this.matchBlock[cpuMove/10][cpuMove%10] = 'O';
			this.turn = 'X';
		}

		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				System.out.print(this.matchBlock[i][j]);
				if(j != 2){
					System.out.print(" | ");
				}
			}
			if(i != 2)
				System.out.println("\n--  --  --");
			else
				System.out.println("");
		}
	}

	public void PlayerVsPlayerResult(int moveCount){
		if(moveCount < 9 || matchWin(matchBlock)){
			if(this.turn == 'X'){
				System.out.println("\nRESULT => Player O Wins");
			}
			else{
				System.out.println("\nRESULT => Player X Wins");
			}
		}
		else{
			System.out.println("\nRESULT => Draw");
		}
	}

	public void PlayerVsCPUResult(int moveCount){
		if(moveCount < 9 || matchWin(matchBlock)){
			if(this.turn == 'X'){
				System.out.println("\nRESULT => CPU Wins");
			}
			else{
				System.out.println("\nRESULT => Player Wins");
			}
		}
		else{
			System.out.println("\nRESULT => Draw");
		}
	}		
}

public class TicTacToe{
	public static void main(String[] args) {
		System.out.println("Choose the Match Type :");
		System.out.println("1. Player vs Player");
		System.out.println("2. Player vs Computer");

		Scanner sc = new Scanner(System.in); 
		int matchType = sc.nextInt();

		Match game = new Match();
		int moveCount = 0;
		int flag;

		switch(matchType){
			case 1 :	//Player vs Player
						while(moveCount < 9 && game.matchWin(game.matchBlock) == false){
							flag = game.PlayerMove();
							if(flag != 0)
								moveCount++;
						}

						game.PlayerVsPlayerResult(moveCount);
						break;

			case 2 :	//Player vs CPU
						while(moveCount < 9 && game.matchWin(game.matchBlock) == false){
							flag = game.PlayerVsCPUmove();
							if(flag != 0)
								moveCount++;

							if(game.matchWin(game.matchBlock) == false){
								game.CPUmove();
								moveCount++;
							}
						}

						game.PlayerVsCPUResult(moveCount);
						break;
		}

	}
}