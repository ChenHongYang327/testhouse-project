package member.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request.getSession(false) != null) {
			request.changeSessionId();
		}
		HttpSession session=request.getSession();		
		
		if (session.getAttribute("ROLEID") != "2") {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}		
		super.doFilter(request, response, chain);

}
}
