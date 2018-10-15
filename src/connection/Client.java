package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.spec.MGF1ParameterSpec;

public class Client {
	private Socket socket = null;
	// private String endereço = "localhost";

	public Client(int port) {
		// this.endereço = endereço;
		try {
			this.socket = new Socket("localhost", port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*-----------Fechamento de conexão-----------*/
	public void fecharSocket(Socket socket) throws IOException {
		socket.close();
	}

	/*--------Validação de chegada e sáida (protocolo)-------*/
	public void tratamentoConexao(Socket socket) throws IOException {
		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			String msg = "Opa";
			output.writeUTF(msg);

			msg = input.readUTF();
			System.out.println("Resposta: " + msg);

			// fecha entrada/saída de dadados
			output.close();
			input.close();
		}

		catch (IOException e) {
			// tratamento de falhas
		} finally {
			fecharSocket(socket);
		}
	}

}
