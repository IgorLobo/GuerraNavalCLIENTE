package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket socket = null;
	//private String endereço = "localhost";	
	
	public Client(int port){		
		//this.endereço = endereço;		
		try {
			this.socket = new Socket("localhost", port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void objectOutputStream(Socket socket) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public void objectInputStream(Socket socket) throws IOException {
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	}
	
	
}
