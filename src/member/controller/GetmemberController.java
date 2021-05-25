package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.bean.Member;
import member.service.MemberService;

@WebServlet("/getmember")
public class GetmemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Gson GSON = new Gson();
	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberService();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		//獲取登入的session,再藉由session的key取ID的values
		int  member = (int) req.getSession().getAttribute("ID");
		
		//取得ID後呼叫memberService裡的方法去比對ID
		Member id = memberService.selectById(member);
	
		try (PrintWriter pw = resp.getWriter()) {
			String jsonStr = GSON.toJson(id);
			pw.print(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
