package tag;

public class NeuroIntMatrix {
	
	private int[][] net;
	private int rows;
	private int columns;

	int[] w = { -2, 0, 2 };
	int[] b = { 3, 1, -1, -3 };
	
	public NeuroIntMatrix(int rows, int columns) {
		net = new int[rows][columns];
		this.rows = rows;
		this.columns = columns;
	}
	/**
	 * Erstellt eine Kopie der Matrix m
	 * 
	 * @param m
	 */
	public NeuroIntMatrix(NeuroIntMatrix m) {
		net = new int[m.rows][m.columns];
		rows = m.rows;
		columns = m.columns;
		for (int r=0; r < rows; r++)
			for (int c=0; c < columns; c++)
				net[r][c] = m.net[r][c];
	}
	/**
	 * Initialisierung mit w und b Werten
	 */
	public void initRandom() {
		for (int r=0; r < rows-1; r++) {
			for (int c=0; c < columns; c++) {
				net[r][c] = w[(int) Math.floor(Math.random()*3)];
			}
		}
		for (int c=0; c < columns; c++)
			net [rows-1][c] = b[(int) Math.floor(Math.random()*4)];
	}
	/**
	 * Die Matrix mit einer Inputliste multiplizieren
	 * 
	 * 1 2 3  01       1*01 + 2*02 + 3*03
	 * 4 5 6  02       4*01 + 5*02 + 6*03
	 * 7 8 9  03       7*01 + 8*02 + 9*03
	 * 
	 * @param in
	 * @return
	 */
	public int[] multInput(int[] in) {
		int[] ret = new int[rows];
		for (int r=0; r < rows; r++) {
			ret[r] = 0;
			for (int c=0; c < columns-1; c++) {
				ret[r] += net[r][c] * in[c];
			}
			ret[r] += net[r][columns-1]; // in immer 1
		}
		return ret;		
	}
	/**
	 * Das bestehende Netz zufällig verändern.
	 * 
	 * 0.1 10 % maximal 10 % werden verändert
	 * 
	 * @param prozent als Gleitkommazahl
	 * @param min minimalwert
	 * @param max maximalwert
	 */
	public void mutant(double prozent) {
		for (int r=0; r < rows; r++)
			for (int c=0; c < columns; c++)
				if (Math.random() < prozent)
					if (r == rows-1)
						net [rows-1][c] = b[(int) Math.floor(Math.random()*4)];
					else
						net[r][c] = w[(int) Math.floor(Math.random()*3)];; 
	}
}
