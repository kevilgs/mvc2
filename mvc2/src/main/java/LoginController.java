
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc2.LoginPogo;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String u = request.getParameter("uname");
		String p = request.getParameter("pass");
		String action = request.getParameter("action");

		System.out.println(u);
		System.out.println(p);
		
		LoginPogo loginPogo = new LoginPogo();
		loginPogo.setUname(u);
		loginPogo.setPass(p);
		
		System.out.println("huh"+loginPogo.insertUser());
		System.out.println("bro"+loginPogo.deleteUser());
		System.out.println("lesgo"+loginPogo.updateUser());
		boolean flag = loginPogo.blLogic();

		if (flag) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);

		} else {

			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

		if ("insert".equals(action)) {
			if (loginPogo.insertUser()) {

				PrintWriter printWriter = response.getWriter();
				printWriter.print("insertedddd");
				return;
			} 
		} else if ("delete".equals(action)) {
			if (loginPogo.deleteUser()) {

				response.getWriter().println("user deleted successfullyy φ(*￣0￣)");
				return;
			} 
		} else if ("update".equals(action)) {
			if (loginPogo.updateUser()) {
				response.getWriter().println("user updated successfullyy φ(*￣0￣)");
				return;
			} 
		} else if ("select".equals(action)) {
			if (loginPogo.showAllUsers()) {

				response.getWriter().println("Check your console !! ");
				return;
			} 
		} else {
			PrintWriter printWriter = response.getWriter();
			printWriter.print("help");
			
		}

	}

}
