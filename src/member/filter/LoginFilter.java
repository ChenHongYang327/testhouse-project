package member.filter;

import java.io.IOException;
import java.net.URI;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		System.out.println("HI Filter");
		final String uri="/member/html/edit.html";
		if (request.getSession(false) != null) {
			request.changeSessionId(); // ←產生新的Session ID
		}
		HttpSession session=request.getSession();
		
		super.doFilter(request, response, chain);
//		System.out.println(session.getAttribute("ROLEID"));
		if (session.getAttribute("ROLEID") == "1") {
//			System.out.println("END1");
//			request.getRequestDispatcher("member/html/admin.html").forward(request, response);	
			response.sendRedirect(request.getContextPath() + "/member/html/admin.html");
			return;
		}
		if (session.getAttribute("ROLEID") == "2") {
//			System.out.println("END2");
//			request.getRequestDispatcher("member/html/edit.html").forward(request, response);			
//			System.out.println(request.getContextPath());		
			response.sendRedirect(request.getContextPath() + "/member/html/edit.html");
			return;
		}
	}

}
