
public class Main {

	public static void main(String[] args) {
		init1(5, 100000);
	}
	
	public static void init1(int N, int k) {
		Chessboard cb = new Chessboard(N, 6);
		int iter = 0;
		do {
			if (iter == k)
				break;
			cb.cleanBoard();
			for (int i = 0; i < N; i++) {
//				if (i == 0 && iter != 0) {
//					continue;
//				}
				double r = Math.random();
				while (cb.putFigure(r)) {
					r = Math.random();
				}
			}
			iter++;
		} while (cb.countScore());
		System.out.println(iter);
//		for (int i = 0; i < N; i++) {
//			if (i == 0) {
//				continue;
//			}
//			double r = Math.random();
//			while (cb.putFigure(r)) {
//				r = Math.random();
//			}
//		}
		System.out.println(cb);
	}

}
