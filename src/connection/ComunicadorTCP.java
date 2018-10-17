package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ComunicadorTCP {
    Socket socket;
    DataInputStream entrada;
    DataOutputStream saida;

    
    public ComunicadorTCP(String ipServer, int portaServer) throws IOException {
        socket = new Socket(ipServer, portaServer);
        entrada = new DataInputStream(socket.getInputStream());
        saida = new DataOutputStream(socket.getOutputStream());
    }
    
       
    public void enviarMensagem(String mensagem) throws IOException {
        saida.writeUTF(mensagem);
        saida.flush();
    }
    
    public String receberMensagem() throws IOException {
        return entrada.readUTF();
    }
}