package Encryption_Decryption;
public class AlphaNumeric29Chart {
	public Integer[] getAlpha2Num(StringBuilder msg) {
		int length = msg.length();
		Integer arr[] = new Integer[length];
		for(int i = 0 , j = 0 ; i < length ; i++ , j++) {
			if(msg.charAt(i) >= 'A' && msg.charAt(i) <= 'Z') {
				arr[j] = msg.charAt(i)- 'A';
			}
			else if(msg.charAt(i) >= 'a' && msg.charAt(i) <= 'z') {
				arr[j] = msg.charAt(i)- 'a';
			}
			else if(msg.charAt(i) == '.'){
				arr[j] = 26;
			}
			else if(msg.charAt(i) == '?') {
				arr[j] = 27;
			}
			else {
				arr[j] = 28;
			}
		}
		return arr;
	}
	
	public StringBuilder getNum2Alpha(Integer arr[]) {
		StringBuilder s = new StringBuilder();
		int length = arr.length;
		for(int i = 0; i < length; i++) {
			if(arr[i] >= 0 && arr[i] <= 25) {
				s.append((char)(arr[i]+'A'));
			}
			else if(arr[i] == 26) {
				s.append('.');
			}
			else if(arr[i] == 27) {
				s.append('?');
			}
			else if(arr[i] == 28) {
				s.append(' ');
			}
		}
		
		return s;
	}
	
}
