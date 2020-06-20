package kcsj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Chats {
    public static void main(String[] args) throws Exception {
        Chats cs=new Chats();
        cs.setUpServer(8572);
    }

    private void setUpServer(int port) throws Exception {
        ServerSocket server=new ServerSocket(port);
        while(true) {
            Socket socket=server.accept();
            Server st=new Server(socket);
            st.start();
        }
    }
}
