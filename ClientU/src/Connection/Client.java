package Connection;
import javax.swing.*;
import java.io.*;
import java.net.Socket;

import static java.lang.System.exit;


public class Client {

    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Object message;
    BufferedReader br;
    public Client() {
        try {
            // создаём сокет общения на стороне клиента в конструкторе объекта
            System.out.println("Connecting...");
            socket = new Socket("localhost", 3345);
            System.out.println("socket done");
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("br done");
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("oos done");
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("ois done");
            System.out.println("Client connected to socket");
        } catch (Exception e) {
          //  e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Нет доступа к сервверу" );
            exit(0);
        }
    }
    public void setMessage(Object message) {
        this.message = message;
    }
    public void SendMess()
    {
        try {
            oos.writeObject(message);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object RecvMess() throws ClassNotFoundException {
        Object in = null;
        try {
            in = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
    public void StopClient()
    {
        message = "quit";
        try {
            oos.writeObject(message);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}