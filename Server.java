import java.net.*;

public class Server
{
	public static void main(String args[]) throws Exception
	{
		// Create server socket
		ServerSocket ss = new ServerSocket(1404);

		// Accept client's connection
		Socket s1 = ss.accept();
		Socket s2 = ss.accept();
		Socket s3 = ss.accept();

		// Call thread
		ServerThread st = new ServerThread(s1,s2,s3);
		st.start();
	}
}
