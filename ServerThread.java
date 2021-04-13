import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread
{
	Socket s1, s2, s3;

	ServerThread3(Socket insocket1, Socket insocket2, Socket insocket3)
	{
		s1 = insocket1;
		s2 = insocket2;
		s3 = insocket3;
	}

	public void run()
	{
		try
		{
			// Create text output and input streams
			DataInputStream dis1 = new DataInputStream(s1.getInputStream());
			DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());
			DataInputStream dis2 = new DataInputStream(s2.getInputStream());
			DataOutputStream dos2 = new DataOutputStream(s2.getOutputStream());
			DataInputStream dis3 = new DataInputStream(s3.getInputStream());
			DataOutputStream dos3 = new DataOutputStream(s3.getOutputStream());

			// 01. Send text message
			dos1.writeUTF("\nConnection Established\nWelcome Client 1");
			dos1.flush();
			dos2.writeUTF("\nConnection Established\nWelcome Client 2");
			dos2.flush();
			dos3.writeUTF("\nConnection Established\nWelcome Client 3");
			dos3.flush();

			String ch1="0", ch2="0", ch3="0", f="C:\\Users\\Ajinkya Salvi\\Desktop\\Study\\02. Distributed Systems\\Projects\\Dis Sys - Project 02\\Assignment 2\\File.txt";
			int i=0, j=0, c1=4, c2=5, c3=6, k=0, q1=0,q2=0,q3=0,case1=0, case2=0, case3=0;
			int[] a=new int[13];

			for(i=0;i<13;i++)
			{
				a[i]=0;
			}

			// 02. Send count
			dos1.writeUTF("\n\nYou will acquire lock three times.");
			dos1.flush();
			dos2.writeUTF("\n\nYou will acquire lock four times.");
			dos2.flush();
			dos3.writeUTF("\n\nYou will acquire lock five times.");
			dos3.flush();

			i=0;
			while((c1+c2+c3)>0) {

				if((q1==0)&&(case1==0))
				{

					// Ask access
					dos1.writeUTF("\n\nAccess file (Enter '1').");
					dos1.flush();

					// Receive choice
					ch1 = dis1.readUTF();

					if(ch1.equals("1"))
					{
						if(c1==0)
						{
							// Send Lock Result
							dos1.writeUTF("-1");
							dos1.flush();

							// Send Fail Message
							dos1.writeUTF("You already have used acquired number of locks.");
							dos1.flush();
							case1=1;
						}
						if((a[i]!=0)&&(case1==0))
						{
							// Send Lock Result
							dos1.writeUTF("0");
							dos1.flush();
							// Send Fail Message
							dos1.writeUTF("Currently, lock is acquired by Client "+a[i]+".\nAdding your request in queue.");
							dos1.flush();

							j=i;
							while(j<13)
							{
								if(a[j]==0)
								{
									a[j]=1;
									k=j-i;
									// Send Queue Result
									dos1.writeUTF("1");
									dos1.flush();
									// Part 2
									dos1.writeUTF("\n\nSuccessfully enrolled in queue.\nCurrent position: "+k);
									dos1.flush();
									q1=1;
									j=999;
								}
								j++;
							}
							if(q1==0)
							{
								// Send Queue Result
								dos1.writeUTF("0");
								dos1.flush();
								//  Part 2
								dos1.writeUTF("\n\nQueue is full");
								dos1.flush();
							}
						}
						if(a[i]==0)
						{
							a[j]=1;
							// Send Queue Result
							dos1.writeUTF("1");
							dos1.flush();
							q1=1;
						}
					}
				}
				if((q1==1)&&(case1==0))
				{
					if(a[i]==1)
					{
						// Send Lock message
						dos1.writeUTF("1");
						dos1.flush();
						//  Lock access
						dos1.writeUTF("\n\nYou have acquired lock.");
						dos1.flush();

						// Write to file
						BufferedWriter writer1 = new BufferedWriter(new FileWriter(f));
					    writer1.write(""+i);
					    writer1.close();

					    //  Send success message
					    dos1.writeUTF("\n\nSuccessfully inserted "+i+" in file.");
						dos1.flush();
					    c1--;
						q1=0;
					}
					else
					{
						// Send Lock message
						dos1.writeUTF("0");
						dos1.flush();
						j=i;
						while(j<13)
						{
							if(a[j]==1)
							{
								k=j-i;
								// Send Current position
								dos1.writeUTF("\n\nCurrent position: "+k);
								dos1.flush();
								j=999;
							}
							j++;
						}
					}
				}
				if((q2==0)&&(case2==0))
				{
					//  Ask access
					dos2.writeUTF("\n\nEnter '1'.");
					dos2.flush();

					// Receive choice
					ch2 = dis2.readUTF();


					if(ch2.equals("1"))
					{
						if(c2==0)
						{
							//  Send Lock Result
							dos2.writeUTF("-1");
							dos2.flush();

							//  Send Fail Message
							dos2.writeUTF("You already have used acquired number of locks.");
							dos2.flush();
							case2=1;
						}
						if((a[i]!=0)&&(case2==0))
						{
							//  Send Lock Result
							dos2.writeUTF("0");
							dos2.flush();
							//  Send Fail Message
							dos2.writeUTF("Currently, lock is acquired by Client "+a[i]+".\nAdding your request in queue.");
							dos2.flush();

							j=i;
							while(j<13)
							{
								if(a[j]==0)
								{
									a[j]=2;
									k=j-i;
									//  Send Queue Result
									dos2.writeUTF("1");
									dos2.flush();
									//  Part 2
									dos2.writeUTF("\n\nSuccessfully enrolled in queue.\nCurrent position: "+k);
									dos2.flush();
									q2=1;
									j=999;
								}
								j++;
							}
							if(q2==0)
							{
								// Send Queue Result
								dos2.writeUTF("0");
								dos2.flush();
								//  Part 2
								dos2.writeUTF("\n\nQueue is full");
								dos2.flush();
							}
						}
						if(a[i]==0)
						{
							a[j]=2;
							//  Send Queue Result
							dos2.writeUTF("1");
							dos2.flush();
							q2=1;
						}
					}
					else
					{
						ch2="1";
					}
				}
				if((q2==1)&&(case2==0))
				{
					if(a[i]==2)
					{
						// Send Lock message
						dos2.writeUTF("1");
						dos2.flush();
						// Lock access
						dos2.writeUTF("\n\nYou have acquired lock.");
						dos2.flush();

						// Write to file
						BufferedWriter writer2 = new BufferedWriter(new FileWriter(f));
					    writer2.write(""+i);
					    writer2.close();

					    // 11. Send success message
					    dos2.writeUTF("\n\nSuccessfully inserted "+i+" in file.");
						dos2.flush();
					    c2--;
						q2=0;
					}
					else
					{
						//  Send Lock message
						dos2.writeUTF("0");
						dos2.flush();
						j=i;
						while(j<12)
						{
							if(a[j]==2)
							{
								k=j-i;
								// Send Current position
								dos2.writeUTF("\n\nCurrent position: "+k);
								dos2.flush();
								j=999;
							}
							j++;
						}
					}
				}
				if((q3==0)&&(case3==0))
				{
					//  Ask access
					dos3.writeUTF("\n\nEnter '1'.");
					dos3.flush();

					// Receive choice
					ch3 = dis3.readUTF();

					if(ch3.equals("1"))
					{
						if(c3==0)
						{
							//  Send Lock Result
							dos3.writeUTF("-1");
							dos3.flush();

							//  Send Fail Message
							dos3.writeUTF("You already have used acquired number of locks.");
							dos3.flush();
							case3=1;
						}
						if((a[i]!=0)&&(case3==0))
						{
							//  Send Lock Result
							dos3.writeUTF("0");
							dos3.flush();
							//  Send Fail Message
							dos3.writeUTF("Currently, lock is acquired by Client "+a[i]+".\nAdding your request in queue.");
							dos3.flush();

							j=i;
							while(j<12)
							{
								if(a[j]==0)
								{
									a[j]=3;
									k=j-i;
									//  Send Queue Result
									dos3.writeUTF("1");
									dos3.flush();
									//  Part 2
									dos3.writeUTF("\n\nSuccessfully enrolled in queue.\nCurrent position: "+k);
									dos3.flush();
									q3=1;
									j=999;
								}
								j++;
							}
							if(q3==0)
							{
								// Send Queue Result
								dos3.writeUTF("0");
								dos3.flush();
								// Part 2
								dos3.writeUTF("\n\nQueue is full");
								dos3.flush();
							}
						}
						if(a[i]==0)
						{
							a[i]=3;
							// Send Queue Result
							dos3.writeUTF("1");
							dos3.flush();
							q3=1;
						}
					}
				}
				if((q3==1)&&(case3==0))
				{
					if(a[i]==3)
					{
						//  Send Lock message
						dos3.writeUTF("1");
						dos3.flush();
						//  Lock access
						dos3.writeUTF("\n\nYou have acquired lock.");
						dos3.flush();

						// Write to file
						BufferedWriter writer3 = new BufferedWriter(new FileWriter(f));
					    writer3.write(""+i);
					    writer3.close();

					    //  Send success message
					    dos3.writeUTF("\n\nSuccessfully inserted "+i+" in file.");
						dos3.flush();
						q3=0;
						c3--;
					}
					else
					{
						//  Send Lock message
						dos3.writeUTF("0");
						j=i;
						while(j<12)
						{
							if(a[j]==3)
							{
								dos3.flush();
								k=j-i;
								// 12. Send Current position
								dos3.writeUTF("\n\nCurrent position: "+k);
								dos3.flush();
								j=999;
							}
							j++;
						}
					}
				}
				i++;
			}}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		finally
		{
		}}}
