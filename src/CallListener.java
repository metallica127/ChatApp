import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by user on 15.11.2015.
 */
public class CallListener{          //начат




   public Connection getConnection(Socket socket) throws IOException {
       Connection c = new Connection(socket);
       c.sendNickHello(Protocol.NICK);

       if (Protocol.isBusy == true) {                              //если при этом у нас занято
           c.sendNickBusy(Protocol.NICK);
           c.disconnect();
           c=null;}



       return c;
   }


    public void setBusy(boolean busy){
        Protocol.isBusy=busy;
    }


}
