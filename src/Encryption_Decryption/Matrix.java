package Encryption_Decryption;

import java.util.Vector;

public class Matrix {	
	public int calculateDeterminant(Vector<Vector<Integer>> m, int s) {
		int det = 0;
		int multi = 1;
		if(s == 2) {
		    det = m.get(0).get(0) * m.get(1).get(1) - m.get(0).get(1) * m.get(1).get(0);
			return det;
		}
		Vector <Vector<Integer>> tempMatrix = new Vector<Vector<Integer>>();
		for(int k = 0; k < s ; k++) {
			for(int i = 1; i< s ;i++) {
				Vector<Integer> r = new Vector<Integer>();
				for(int j = 0; j< s; j++) {
					if(k != j) {
						r.add(m.get(i).get(j));
					}
				}
				if(!r.isEmpty()) {
					tempMatrix.add(r);
				}
				
			}
			det = det + m.get(0).get(k) * calculateDeterminant(tempMatrix , s -1) * multi;
			multi *= -1;
			tempMatrix.removeAllElements();
		}
		return det;
	}
	
	public Vector <Vector<Integer>> getMatrixAdjoint(Vector <Vector<Integer>> matrix , int stepSize) {
		Vector <Vector<Integer>> matrixOfMinors = makeMatrixOfMinors(matrix ,stepSize);
		Vector <Vector<Integer>> matrixOfCoFactors = makeMatrixOfCoFactors(matrixOfMinors , stepSize);
		Vector <Vector<Integer>> matrixAdjoint = makeAdjointMatrix(matrixOfCoFactors , stepSize);
		return matrixAdjoint;
	}
	
	public Vector <Vector<Integer>> makeMatrixOfMinors(Vector <Vector<Integer>> matrix , int stepSize) {
		Vector <Vector<Integer>> matrixOfMinors = new Vector<Vector<Integer>>();
		if(stepSize == 2) {
			matrixOfMinors.add(new Vector<Integer>());
			matrixOfMinors.add(new Vector<Integer>());
			matrixOfMinors.get(0).add(matrix.get(1).get(1));
			matrixOfMinors.get(0).add(matrix.get(1).get(0));
			matrixOfMinors.get(1).add(matrix.get(0).get(1));
			matrixOfMinors.get(1).add(matrix.get(0).get(0));
			
			return matrixOfMinors;
		}
		
		Vector <Vector<Integer>> tempMatrix = new Vector<Vector<Integer>>();
		for(int i = 0 ;i < stepSize ; i++) {
			Vector<Integer> minorRow = new Vector<Integer>();
			for(int j = 0; j < stepSize; j++) {
				for(int x = 0; x < stepSize; x++) {
					Vector<Integer> r = new Vector<Integer>();
					for(int y = 0; y < stepSize; y++) {
						if(i != x && j != y) {
							r.add(matrix.get(x).get(y));
						}
					}
					if(! r.isEmpty()) {
						tempMatrix.add(r);
					}
				}
				int det = calculateDeterminant(tempMatrix , stepSize-1);
				minorRow.add(det);
				tempMatrix.removeAllElements();
			}
			matrixOfMinors.add(minorRow);
		}
		return matrixOfMinors;
	}
	
	public Vector <Vector<Integer>> makeMatrixOfCoFactors(Vector <Vector<Integer>> matrixOfMinors, int stepSize) {
		Vector <Vector<Integer>> matrixOfCoFactors = new Vector<Vector<Integer>>();
		int multi = 1;
		for(int i = 0; i < stepSize; i++) {
			if(i%2 == 0) {
				multi = 1;
			}
			else {
				multi = -1;
			}
			Vector<Integer> coFactorRow = new Vector<Integer>();
			for(int j = 0; j < stepSize; j++) {
				coFactorRow.add(matrixOfMinors.get(i).get(j) * multi);
				multi *= -1;
			}
			matrixOfCoFactors.add(coFactorRow);
		}
		return matrixOfCoFactors;
	}
	
	public Vector <Vector<Integer>> makeAdjointMatrix(Vector <Vector<Integer>> matrixOfCoFactors , int stepSize) {
		Vector <Vector<Integer>> matrixAdjoint = new Vector<Vector<Integer>>();
		for(int i = 0; i < stepSize; i++) {
			for(int j = 0; j< stepSize; j++) {
				if(i == 0) {
					Vector<Integer> coFactorRow = new Vector<Integer>();
					matrixAdjoint.add(coFactorRow);
				}
				matrixAdjoint.get(j).add(matrixOfCoFactors.get(i).get(j));
			}
		}
		return matrixAdjoint;
	}
	
	
	
}
