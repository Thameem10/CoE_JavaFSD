package week1;
import java.io.*;

public class UserManager 
{
		String name,email;
		public void adduser(String name,String email)
		{
			this.name = name;
			this.email = email;
		}
		public void saveUsersToFile(String filename)
		{
			try
			{
			FileOutputStream fin = new FileOutputStream(filename);
			String s1 = name,s2 = email;
			byte[] b1 = s1.getBytes();
			byte[] b2 = s2.getBytes();
			fin.write(b1);
			fin.write(b2);
			fin.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
		public void loadUsersFromFile(String filename)
		{
			try
			{
			FileInputStream fout = new FileInputStream(filename);
			int i = fout.read();
			System.out.println((char)i);
			fout.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserManager um = new UserManager();
		um.adduser("thameem","thameem@gmail.com");
		um.saveUsersToFile("C:\\testfile.txt");
		um.loadUsersFromFile("C:\\testfile.txt");	

	}

}
