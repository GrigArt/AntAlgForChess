import java.util.Arrays;

public class Chessboard {
	
	private boolean[][] board;
	private double[][] boardScore;
	private double sum;
	private int n;
	private double buff;
	
	Chessboard(int n, double buff) {
		board = new boolean[n][n]; 
		boardScore = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				boardScore[i][j] = 1.0/(n*n);
		sum = 1;
		this.n = n;
		this.buff = buff;
	}
	
	public boolean putFigure(double r) {
		double rn = 0.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				rn += boardScore[i][j];
				if (rn >= r) {
					if (board[i][j])
						return true;
					board[i][j] = true;
					return false;
				}
			}
		}
		return true;
	}
	
	public void cleanBoard() {
//		int q = ((int) (n * Math.random())) + 1;
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				if (board[i][j]) q = q - 1;
//				if (q == 0 && board[i][j])
//					continue;
//				board[i][j] = false;	
//			}
//		}
		board = new boolean[n][n];
	}
	
	public boolean countScore() {
		double[][] newScoreBoard = new double[n][n];
		int count = 0;
		double sumN = 0.0;
		for (int x = 0; x < n; x++) { //селекция...................................
			for (int y = 0; y < n; y++) {
				if (board[x][y]) {
					for (int i = 0; i < n; i++) {
						if (i == x)
							continue;
						if (board[i][y])
							count++;
						newScoreBoard[i][y] += 1;
					}
					for (int i = 0; i < n; i++) {
						if (i == y)
							continue;
						if (board[x][i])
							count++;
						newScoreBoard[x][i] += 1;
					}
					int x1 = x > y ? x-y : 0;
					int y1 = x1 == 0 ? y-x : 0;
					int n1 = n-Math.max(x1, y1);
					int x2 = x + Math.min(n-x-1, y);                                
					int y2 = y - x2 + x;
					int n2 = Math.min(x2, n-y2);
					x1 -= 1;
					x2 += 1;
					y1 -= 1;
					y2 -= 1;
					for (int i = 0; i < n1; i++) {
						x1 += 1;
						y1 += 1;
						if (x1 == x)
							continue;
						if (board[x1][y1])
							count++;
						newScoreBoard[x1][y1] += 1;
					}
					for (int i = 0; i < n2; i++) {
						x2 -= 1;
						y2 += 1;
						if (x2 == x)
							continue;
						if (board[x2][y2])
							count++;
						newScoreBoard[x2][y2] += 1;
					}
					
				}
			}
		}//селекция...................................................
		if (count == 0) {
			//return false;
		}
		for (int x = 0; x < n; x++) { //мутация+скрещивание............................................. 
			for (int y = 0; y < n; y++) {
				if (newScoreBoard[x][y] == 0) {
					boardScore[x][y] = boardScore[x][y]*buff;
				} else {
					boardScore[x][y] = boardScore[x][y]/(newScoreBoard[x][y]+1);
				}
				if (boardScore[x][y] >= 1.0/n) {
					boardScore[x][y] = 1.0/n;
				}
				//if (boardScore[x][y] == 0.0) {
					//boardScore[x][y] = 0.01;
				//}
				sumN += boardScore[x][y];
			}
		}
		double r = (sum-sumN)/Math.pow(n, 2);
		if (r > 0) {
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					boardScore[x][y] += r;
				}
			}
		} else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					boardScore[i][j] = boardScore[i][j]/sumN;
				}
			}
		} //мутация........................................................
		return true;
	}
	public String toString() {
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < n; i++) {
			s1 += Arrays.toString(board[i])+"\n";
			s2 += Arrays.toString(boardScore[i])+"\n";
		}
		return s1 + "\n" + s2;
	}

}
 