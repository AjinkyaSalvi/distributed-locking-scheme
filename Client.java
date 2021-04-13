import java.io.*;
import java.net.*;
import java.util.*;

public class Client
{
	public static void main(String args[]) throws Exception
	{
		// Request connection to server
		Socket s = new Socket("127.0.0.1", 1404);

		// Create text output and input streams
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());

		String c, r;
		int i=0, q=0;

		// Receive text message
		System.out.print(dis.readUTF());

		Scanner t = new Scanner(System.in);

		// Receive count
		System.out.print(dis.readUTF());

		while(true)
		{

			if(q==0)
			{
				// Receive question

				System.out.print(dis.readUTF());
				c = t.nextLine();

				// Send choice
				dos.writeUTF("1");
				dos.flush();

				// Receive Lock Result
				c=dis.readUTF();
				if(c.equals("-1"))
				{
					// Receive fail message
					System.out.print(dis.readUTF());
				}
				if(c.equals("0"))
				{
					// Receive fail message
					System.out.print(dis.readUTF());

					// Receive queue result
					r=dis.readUTF();
					if(r.equals("1"))
					{
						// Part 2
						System.out.print(dis.readUTF());
						q=1;
					}
					if(r.equals("0"))
					{
						// Part 2
						System.out.print(dis.readUTF());
					}
				}
				if(c.equals("1"))
				{
					q=1;
				}
			}
			if(q==1)
			{
				// Receive Lock Result
				c=dis.readUTF();
				if(c.equals("1"))
				{
					// Receive access message
					System.out.print(dis.readUTF());
					// Receive success message
					System.out.print(dis.readUTF());
					q=0;
				}
				if(c.equals("0"))
				{
					// Receive waiting message
					System.out.print(dis.readUTF());
				}
			}
			i++;
		}
	}
}
