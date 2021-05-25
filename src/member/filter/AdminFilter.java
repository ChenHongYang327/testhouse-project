package member.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.MemberService;
import member.bean.Member;

@WebFilter("/member/html/admin.html")
public class AdminFilter  extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;
    
    private MemberService memberService;

    public void init(FilterConfig fConfig) throws ServletException {
        memberService = new MemberService();
        super.init();
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String rootPath = request.getContextPath();
		HttpSession session = request.getSession();
        Integer member_id = (Integer)session.getAttribute("ID");
        
        if (member_id == null) {
            response.sendRedirect(rootPath + "/index.jsp");
            return;
        } else {
            Member member = memberService.selectById(member_id);
            if (member == null) {
                response.sendRedirect(rootPath + "/index.jsp");
                return;
            }
            
            if (member.getRoleId() == 2) {
                response.sendRedirect(rootPath + "/index.jsp");
                return;
            }
        }

        chain.doFilter(request, response);
	}
}
