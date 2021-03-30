package cc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class FileOperation {
	
	public static void main(String[] args) {
		String path = "ast/assignment.txt";
		String str = "";
		write(path, str);
	}
	
	public static String read(String path) {

		String str = "";

		try {
			File file = new File(path);

			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			while ((st = br.readLine()) != null) {
				// System.out.println(st);
				str = str + st + "\n";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return str;
	}

	public static void write(String path, String str) {

		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.println(str);
			// writer.println("The second line");
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
