import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyServer{
    public static void main(String args[]) throws Exception{
	
	Scanner scan=new Scanner(System.in);
	ServerSocket ss=new ServerSocket(9999);
        Socket s=ss.accept();
        String strS=new String();
	String strC=new String();
	DataInputStream dis=new DataInputStream(s.getInputStream());
	DataOutputStream dos=new DataOutputStream(s.getOutputStream());
	strC="";strS="";
	
	exitLoops:
	while(!strC.equals("stop"))
	{
        strC="";strS="";
	System.out.println("\nClient Says:");    
        while(!strC.equals("done")){			
		strC= (String)dis.readUTF();
		if(strC.equals("stop")){
		    System.out.println("\nThe Client has Stopped!");
		    break exitLoops;
		}     	
		System.out.println(strC);
	}
	System.out.println("\nType your message (Type \"done\" on completing your message):");
	
	while(!strS.equals("done")){			
	    strS=scan.nextLine();
	    dos.writeUTF(strS);
	    dos.flush();
	    if(strS.equals("stop")){
		    System.out.println("\nThe Server has Stopped!");
		    break exitLoops;
		}
	
	    }
	}	
	dis.close();
	dos.close();		
	ss.close();
	s.close();

    }
} 