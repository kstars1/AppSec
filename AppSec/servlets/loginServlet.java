package AppSec.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import AppSec.utils.User;
import AppSec.utils.DBUtils;
import AppSec.utils.MyUtils;

@WebServlet(urlPatterns = {"/index.jsp"})
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public LoginServlet()
	{
		super();
	}

	// Show Login page.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		if(isLoggedOff(request))
		{
			// Forward to /WEB-INF/views/loginView.jsp
			// (Users can not access directly into JSP pages placed in WEB-INF)
			RequestDispatcher dispatcher //
			= this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");

			dispatcher.forward(request, response);
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/logoff");
		}

	}

	// When the user enters userName & password, and click Submit.
	// This method will be executed.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = null;
		boolean hasError = false;
		String errorString = null;

		if (username == null || password == null || username.length() == 0 || password.length() == 0)
		{
			hasError = true;
			errorString = "Required username and password!";
		}
		else
		{
			Connection conn = MyUtils.getStoredConnection(request);
			try
			{
				// Find the user in the DB.
				
				user = DBUtils.findUser(conn, username, password);

				if (user == null)
				{
					hasError = true;
					errorString = "username or password invalid";
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}

		// If error, forward to /WEB-INF/views/login.jsp
		if (hasError)
		{
			user = new User();
			user.setEmail(username);
			user.setPassword(password);

			// Store information in request attribute, before forward.
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);

			// Forward to /WEB-INF/views/login.jsp
			RequestDispatcher dispatcher //
			= this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");

			dispatcher.forward(request, response);
		}
		// If no error
		// Store user information in Session
		// And redirect to userInfo page.
		else
		{
			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, user);

			// If user checked "Remember me".
			if (remember)
			{
				MyUtils.storeUserCookie(response, user);
			}
			// Else delete cookie.
			else
			{
				MyUtils.deleteUserCookie(response);
			}

			// Redirect to userInfo page.
			response.sendRedirect(request.getContextPath() + "/success.jsp");
		}
	}

	private boolean isLoggedOff(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		return MyUtils.getLoginedUser(session) == null;
	}

