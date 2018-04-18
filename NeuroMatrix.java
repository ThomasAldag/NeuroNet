package tag;

public class NeuroMatrix {
	
	private double[][] net;
	private int rows;
	private int columns;
	
	public NeuroMatrix(int rows, int columns) {
		net = new double[rows][columns];
		this.rows = rows;
		this.columns = columns;
	}
	/**
	 * Erstellt eine Kopie der Matrix m
	 * 
	 * @param m
	 */
	public NeuroMatrix(NeuroMatrix m) {
		net = new double[m.rows][m.columns];
		rows = m.rows;
		columns = m.columns;
		for (int r=0; r < rows; r++)
			for (int c=0; c < columns; c++)
				net[r][c] = m.net[r][c];
	}
	/**
	 * Matrix mit zufälligen Werten zwischen min und max füllen.
	 * 
	 * @param min
	 * @param max
	 */
	public void initRandom(double min, double max) {
		for (int r=0; r < rows; r++) {
			for (int c=0; c < columns; c++) {
				net[r][c] = min + (max - min) * Math.random();
			}
		}
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
	public double[] multInput(double[] in) {
		double[] ret = new double[rows];
		for (int r=0; r < rows; r++) {
			ret[r] = 0.0;
			for (int c=0; c < columns; c++) {
				ret[r] += net[r][c] * in[c];
			}
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
	public void mutant(double prozent, double min, double max) {
		for (int r=0; r < rows; r++)
			for (int c=0; c < columns; c++)
				if (Math.random() < prozent) net[r][c] = min + (max - min) * Math.random(); 
	}
}
