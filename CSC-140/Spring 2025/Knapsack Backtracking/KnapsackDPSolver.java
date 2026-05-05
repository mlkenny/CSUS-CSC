// Dynamic programming solver
public class KnapsackDPSolver implements java.io.Closeable
{
	private KnapsackInstance inst;
	private KnapsackSolution soln;

	public void FindSolutions() {
		int itemCount = inst.GetItemCnt();
		int cap = inst.GetCapacity();
		int[][] DPtable = new int[itemCount + 1][cap + 1];
		// Fill DP table.
		for (int i = 0; i < cap; i++) {
			DPtable[0][i] = 0;
		}
		// DP Algorithm.
		for (int j = 1; j <= itemCount; j++) {
			for (int k = 0; k <= cap; k++) {
				if (inst.GetItemWeight(j) > k) {
					DPtable[j][k] = DPtable[j - 1][k];
				} else {
					if (inst.GetItemValue(j) + DPtable[j - 1][k - inst.GetItemWeight(j)] > DPtable[j - 1][k]) {
						DPtable[j][k] = inst.GetItemValue(j) + DPtable[j - 1][k - inst.GetItemWeight(j)];
					}else {
						DPtable[j][k] = DPtable[j - 1][k];
					}
				}
			}
		}
		// Determine Taken Values.
		int k = cap;
		for (int j = itemCount; j > 0; j--) {
			int crntVal = soln.ComputeValue();
			// System.out.println("\nChecking solution ");
			// soln.Print(" ");
			if (crntVal == DefineConstants.INVALID_VALUE) {
				break;
			}
			if (DPtable[j][k] != DPtable[j - 1][k]) {
				soln.TakeItem(j);
				k -= inst.GetItemWeight(j);
			}
		}
	}

	public KnapsackDPSolver()
	{
		soln = null;
	}
	public void close()
	{
		if (soln != null) {
			soln = null;
		}
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		soln = soln_;
		FindSolutions();
	}
}