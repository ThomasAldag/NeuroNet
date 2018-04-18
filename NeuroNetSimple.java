package tag;

public class NeuroNetSimple {
	
	protected int nInput;		// Anzahl der Input-Neuronen
	protected int nHidden;	// Anzahl der Hidden-Neuronen
	protected int nOutput;	// Anzahl der Output-Neuronen
	
	protected NeuroIntMatrix wih;
	protected NeuroIntMatrix who;
	
	public NeuroNetSimple(int input, int hidden, int output) {
		nInput = input+1;
		nHidden = hidden+1;
		nOutput = output;
		wih = new NeuroIntMatrix(hidden,input);
		who = new NeuroIntMatrix(output,hidden);
		wih.initRandom();
		who.initRandom();
	}
	/**
	 * -1, 0 und 1
	 * 
	 * @param n
	 */
	public NeuroNetSimple(NeuroNetSimple n) {
		nInput = n.nInput;
		nHidden = n.nHidden;
		nOutput = n.nOutput;
		wih = new NeuroIntMatrix(n.wih);
		who = new NeuroIntMatrix(n.who);
	}

	private void aktivate(int[] dlist) {
		for (int i=0; i < dlist.length; i++) 
			if (dlist[i] > 0)	dlist[i] = 1;
			else 				dlist[i] = 0;
	}

	public int[] query(int[] input) {
		int[] inputHidden = wih.multInput(input);
		aktivate(inputHidden);
		int[] hiddenOutput = who.multInput(inputHidden);
		aktivate(hiddenOutput);
		return hiddenOutput;
	}
	
	public void mutant(double prozent) {
		wih.mutant(prozent);
		who.mutant(prozent);
	}
	
	public String dlistToString(int[] dlist) {
		String s = new String();
		for (int i=0; i < dlist.length; i++) s += dlist[i]+" ";
		return s;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NeuroNetSimple nn = new NeuroNetSimple(4,4,4);
		int[] input = {1, 0, 0, 1};
		int[] output = nn.query(input);
		System.out.println(nn.dlistToString(output));

	}
	
	
}
