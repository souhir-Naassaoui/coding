package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
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
		String output = null;
		String code = request.getParameter("code");
		String expected;
		String language = request.getParameter("language");
		//System.out.println(language);
		//System.out.println(code);
		String random = UUID.randomUUID().toString();
		//System.out.println(random);
		String file = "C:\\Users\\souhir\\Desktop\\eclipse jee\\editor\\WebContent\\WEB-INF\\temp\\" + random + "."
				+ language;
		String fileWithoutFull = random + "." + language;
		String fileWithoutExt = "C:\\Users\\souhir\\Desktop\\eclipse jee\\editor\\WebContent\\WEB-INF\\temp\\" + random;
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
			//System.out.println("Successfully wrote to the file.");
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
			output = "";
			while ((output = bfr.readLine()) != null) {
				System.out.println(output);
				
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
			output = "";
			while ((output = bfr.readLine()) != null) {
				System.out.println(output);
			}
		} else if (language.equals("js")) {
			ProcessBuilder pb = new ProcessBuilder("node", file).inheritIO();
			Process p = pb.start();
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
			output = "";
			while ((output = bfr.readLine()) != null) {
				System.out.println(output);
			}
		} else if (language.equals("c")) {
			String ex=random+".exe";
			String[] command = { "CMD", "/C", "gcc -o "+ex+ " "+ fileWithoutFull + " && "+ex };
			ProcessBuilder probuilder = new ProcessBuilder(command);
			probuilder
					.directory(new File("C:\\Users\\souhir\\Desktop\\eclipse jee\\editor\\WebContent\\WEB-INF\\temp"));

			Process process = probuilder.start();

			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			output= "";;
			//System.out.printf("Output of running %s is:\n", Arrays.toString(command));
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			// Wait to get exit value
			try {
				int exitValue = process.waitFor();
				System.out.println("\n\nExit Value is " + exitValue);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
