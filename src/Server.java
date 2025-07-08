import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    static final CopyOnWriteArrayList<PrintWriter> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {

        AtomicInteger counter = new AtomicInteger(1);

        ServerSocket serverSocket = null;
        Socket socket = null;

        try{
            serverSocket = new ServerSocket(8000);
            while(true){
                System.out.println("Waiting for connection...");
                socket = serverSocket.accept();
                System.out.println("Connection accepted");

                String clientName = "client " + counter.getAndIncrement();

                System.out.println(clientName);

                ClientThread client = new ClientThread(socket, clientName);
                client.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally{
            socket.close();
            serverSocket.close();
        }
    }
}

class ClientThread extends Thread{
    private final Socket socket;
    private final String clientName;
    private final BufferedReader in;
    private final PrintWriter out;

    ClientThread(Socket socket, String clientName) throws IOException {
        this.socket = socket;
        this.clientName = clientName;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        Server.clients.add(out);
    }

    String getClientName(){
        return this.clientName;
    }

    @Override
    public void run(){
        try{
            String inputMessage;
            while((inputMessage = in.readLine()) != null){
                String outputMessage = getClientName() + ": " + inputMessage;

                for(PrintWriter client : Server.clients){
                    client.println(outputMessage);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            Server.clients.remove(this.out);
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
