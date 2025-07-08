import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket socket = null;
        Scanner sc = new Scanner(System.in);

        try{
            socket = new Socket("127.0.0.1", 8000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Thread recv = new Thread(new MessageRecv(in));
            recv.start();

            while(true){
                System.out.print("send message : ");
                String outputMessage = sc.nextLine();
                out.println(outputMessage);
                if(outputMessage.equals("exit")) break;
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally {
            sc.close();
            if(socket != null) socket.close();
        }
    }
}

class MessageRecv implements Runnable {
    private BufferedReader in;
    MessageRecv(BufferedReader in) {
        this.in = in;
    }

    public void run(){
        try{
            String inputMessage;
            while((inputMessage = in.readLine()) != null){
                System.out.println();
                System.out.println(inputMessage);
                System.out.println("send message : ");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
