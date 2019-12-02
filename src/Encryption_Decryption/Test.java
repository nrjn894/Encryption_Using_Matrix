package Encryption_Decryption;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Terminal sender = new Terminal();
		Terminal receiver = new Terminal();
		StringBuilder message = new StringBuilder();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the Message.");
		String msg = in.nextLine();
		in.close();
		message.append(msg);
		sender.setMessage(message);
		sender.encrypt();
		System.out.println(sender.encryptedMsg);
		
		sender.sendEncryptedMsg(receiver);
		receiver.decrypt();
		receiver.showDecryptedMsg();
	}

}
