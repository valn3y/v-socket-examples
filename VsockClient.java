import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class VsockClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 9999);
			System.out.println("Connected to server");

			OutputStream outputStream = socket.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

			System.out.println("Sending message to server");
			dataOutputStream.writeUTF("Hello Server!");
			dataOutputStream.flush();
			dataOutputStream.close();

			System.out.println("Closing socket");
			socket.close();	
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}
	}
}
