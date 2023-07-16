import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class VsockServer { 
	public static void main(String[] args) {
		Thread thread = new Thread(runServer);
		thread.start();
	}

	static Runnable runServer = new Runnable() {
		public void run() {
			try {
				System.out.println("Start create vsock server");
                ServerSocket server = new ServerSocket(9999);

				System.out.println("Waiting connect to the client");
				Socket socket = server.accept();
				if(socket.isConnected()){
					System.out.println("socket connected");
					} else {
					System.out.println("Failed connect socket");
				}
				System.out.println("Connection from " + socket);

				InputStream inputStream = socket.getInputStream();
				DataInputStream dataInputStream = new DataInputStream(inputStream);
				String message = dataInputStream.readUTF();
				System.out.println("message received: " + message);

				System.out.println("Closing socket");
				server.close();
				socket.close();
			} catch(IOException error) {
                System.out.println(error.getMessage());
            }
		}
	};
}
