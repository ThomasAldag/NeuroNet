package tag;

public class NeuroNet {
	
	protected int nInput;		// Anzahl der Input-Neuronen
	protected int nHidden;	// Anzahl der Hidden-Neuronen
	protected int nOutput;	// Anzahl der Output-Neuronen
	
	protected NeuroMatrix wih;
	protected NeuroMatrix who;
	
	public NeuroNet(int input, int hidden, int output) {
		nInput = input;
		nHidden = hidden;
		nOutput = output;
		wih = new NeuroMatrix(hidden,input);
		who = new NeuroMatrix(output,hidden);
		wih.initRandom(-0.9, 0.9);
		who.initRandom(-0.9, 0.9);
	}
	
	public NeuroNet(NeuroNet n) {
		nInput = n.nInput;
		nHidden = n.nHidden;
		nOutput = n.nOutput;
		wih = new NeuroMatrix(n.wih);
		who = new NeuroMatrix(n.who);
	}

	private static double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	
	}
	
	private void aktivate(double[] dlist) {
		for (int i=0; i < dlist.length; i++) dlist[i] = sigmoid(dlist[i]);
	}
	
	public double[] query(double[] input) {
		double[] inputHidden = wih.multInput(input);
		aktivate(inputHidden);
		double[] hiddenOutput = who.multInput(inputHidden);
		aktivate(hiddenOutput);
		return hiddenOutput;
	}
	
	public void mutant(double prozent) {
		wih.mutant(prozent,-0.9,0.9);
		who.mutant(prozent,-0.9,0.9);
	}
	
	public String dlistToString(double[] dlist) {
		String s = new String();
		for (int i=0; i < dlist.length; i++) s += dlist[i]+" ";
		return s;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NeuroNet nn = new NeuroNet(4,4,4);
		double[] input = {1, 2, 3, 4};
		double[] output = nn.query(input);
		System.out.println(nn.dlistToString(output));

	}

}
