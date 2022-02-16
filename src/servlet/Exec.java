package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Exec")
public class Exec extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Exec() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		String language = request.getParameter("language");
		System.out.println(language);
		System.out.println(code);
		String random = UUID.randomUUID().toString();
		System.out.println(random);
		String file = "C:\\Users\\souhir\\Desktop\\eclipse jee\\editor\\WebContent\\WEB-INF\\temp\\" + random + "."
				+ language;
		try {
			File f = new File(file);
			if (f.createNewFile())
				System.out.println("File created");
			else
				System.out.println("File already exists");
		} catch (Exception e) {
			System.err.println(e);
		}
		try {
			FileWriter myWriter = new FileWriter(
					"C:\\Users\\souhir\\Desktop\\eclipse jee\\editor\\WebContent\\WEB-INF\\temp\\" + random + "."
							+ language);
			myWriter.write(code);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		if (language.equals("python")) {

			ProcessBuilder pb = new ProcessBuilder("python", file).inheritIO();
			Process p = pb.start();
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while ((line = bfr.readLine()) != null) {
				System.out.println(line);
			}
		} else if (language.equals("php")) {
			ProcessBuilder pb = new ProcessBuilder("php", file).inheritIO();
			Process p = pb.start();
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while ((line = bfr.readLine()) != null) {
				System.out.println(line);
			}
		} else if (language.equals("c")) {
			 
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
