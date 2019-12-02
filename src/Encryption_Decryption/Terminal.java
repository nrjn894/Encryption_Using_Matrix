package Encryption_Decryption;

import java.util.Scanner;
import java.util.Vector;

public class Terminal {
	StringBuilder message;
	StringBuilder encryptedMsg;
	StringBuilder decryptedMsg;
	int stepSize;
	Vector<Vector<Integer>> keyMatrix = new Vector<Vector<Integer>>();
	int determinant = 0;
	Scanner in = new Scanner(System.in);
	Matrix matrix = new Matrix();
	AlphaNumeric29Chart chart = new AlphaNumeric29Chart();
	
	
	
	public void setMessage(StringBuilder message) {
		this.message = message;
	}
	
	public void setKeyMatrix() {
		System.out.println("Enter the Key Matrix of size "+stepSize+"X"+stepSize);
		int det = 1;
		do {
			if(det == 0) {
				System.out.println("Matrix is Invalid as Key.Enter some other Key Matrix.");
			}
			keyMatrix.removeAllElements();
			for(int i = 0; i < stepSize; i++) {
				Vector<Integer>keyRow = new Vector<Integer>();
				for(int j = 0; j < stepSize; j++) {
					keyRow.add(in.nextInt());
				}
				keyMatrix.add(keyRow);
			}
			
			det = matrix.calculateDeterminant(keyMatrix , stepSize);
			System.out.println("Determinant is"+ det);
		}while(det == 0 || det % 29 == 0);
		
		this.determinant = det;
		
	}
	
	
	public void encrypt() {
		if(message.length() == 0) {
			System.out.println("No Message to Encrypt");
			return;
		}
		System.out.println("Enter the step size");
		this.stepSize = in.nextInt();
		while(message.length() % stepSize != 0) {
			message.append(message.charAt(message.length()-1));
		}
		
		setKeyMatrix();
		
		Integer midArray[] = chart.getAlpha2Num(message);
		
		Integer encryptedArray[] = encryptDecryptArray(midArray , keyMatrix);
		
		encryptedArray = applyModulo29(encryptedArray , 1);
		
		encryptedMsg = chart.getNum2Alpha(encryptedArray) ;
	}
	
	
	
	public void decrypt() {
		System.out.println("Enter the step size");
		this.stepSize = in.nextInt();
		if(message.length() % stepSize != 0) {
			System.out.println("Encrypted message is invalid");
			return;
		}
		
		setKeyMatrix();
		Integer midArray[] = chart.getAlpha2Num(message);
		
		Vector<Vector<Integer>> adjointMatrix = matrix.getMatrixAdjoint(keyMatrix, stepSize);
		
		Integer decryptedArray[] = encryptDecryptArray(midArray , adjointMatrix);
		
		decryptedArray = applyModulo29(decryptedArray , determinant);
		
		decryptedMsg = chart.getNum2Alpha(decryptedArray);
		
	}
	
	
	
	public Integer [] encryptDecryptArray(Integer midArray[] , Vector<Vector<Integer>> m) {
		int length = midArray.length;
		Integer encryptedArray[] = new Integer[length];
		encryptedArray = new Integer[length];
		for(int i = 0; i < length; i+=stepSize) {
			for(int j = 0; j < stepSize; j++) {
				encryptedArray[i+j] = 0;
				for(int k = 0; k < stepSize; k++) {
					encryptedArray[i+j] += midArray[i+k]*m.get(k).get(j);
				}
			}
		}
		return encryptedArray;
	}
	
	
	public Integer[] applyModulo29(Integer arr[], int det) {
		int length = arr.length;
		int detInverseMod = 1;
		if(det != 1) {
			for(int i = 0; i < 27; i++) {
				detInverseMod = (detInverseMod * det) % 29;
			}
		}
		for(int i = 0; i < length; i++) {
			arr[i] = (int) ((arr[i] * detInverseMod) % 29);
			if(arr[i] < 0) {
				arr[i] += 29;
			}
		}
		return arr;
	}
	
	
	void sendEncryptedMsg(Terminal obj) {
		obj.setMessage(this.encryptedMsg);
	}
	
	void showDecryptedMsg() {
		if(decryptedMsg.length() == 0) {
			System.out.println("Sorry! No Decryption took place yet.");
			return;
		}
		System.out.println(decryptedMsg);
	}
	
}
