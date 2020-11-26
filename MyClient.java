import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient{

    public static void main(String args[]) throws Exception{
	
	Scanner scan=new Scanner(System.in);
	String strC=new String();
	String strS=new String();
	strC="";strS="";
    	Socket s=new Socket("localhost",9999);
	DataInputStream dis=new DataInputStream(s.getInputStream());
	DataOutputStream dos=new DataOutputStream(s.getOutputStream());
	exitLoops:
	while(!strC.equals("stop")){

	    strC="";strS="";
	    System.out.println("\nType your message (Type \"done\" on completing your message):");
	    while(!strC.equals("done")){
		strC=scan.nextLine();
		dos.writeUTF(strC);
		dos.flush();
		if(strC.equals("stop")){
		    System.out.println("\nThe Client has Stopped!");
		    break exitLoops;
		}
	    }
	    
	    System.out.println("\nServer says :");
	    while(!strS.equals("done")){
		strS=(String)dis.readUTF();
		if(strS.equals("stop")){
		    System.out.println("\nThe Server has Stopped!");
		    strC="stop";
		    break exitLoops;
		}
		System.out.println(strS);
	    }
	    
	} 
      
	dos.close();
        dis.close();
	s.close();   
    }
}