package test.clientmsg;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class TestOutputHandler implements Runnable {

	private Socket server;
	private OutputStream os;

	public TestOutputHandler(Socket server) {
		this.server = server;
		try {
			os = server.getOutputStream();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			DataOutputStream out = new DataOutputStream(
					server.getOutputStream());
			Scanner sc = new Scanner(System.in);
			while (true) {
				String msg = sc.nextLine();
				if (msg.equalsIgnoreCase("quit"))
					break;
				if(msg.charAt(0) == '1') {
					String snd = msg.substring(1);
					int length = snd.length();
					out.write(1);
					out.writeInt(length);
						out.writeChars(snd);
						System.out.println("sent " + snd + " to server");
					//Send string
				}else if(msg.charAt(0) == '2') {
					int snd = Integer.parseInt(msg.substring(1));
					out.write(2);
					out.writeInt(snd);
					//send int
				}else if(msg.charAt(0) == '3'){
					out.write(3);
					//send byte 3
				}
			}
			System.out.println("client closing");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
