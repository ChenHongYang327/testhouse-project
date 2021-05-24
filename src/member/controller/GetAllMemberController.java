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
import com.google.gson.GsonBuilder;

import member.bean.Member;
import member.service.MemberService;


@WebServlet("/member/getallmember")
public class GetAllMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new GsonBuilder().create();
	
	private MemberService memberService;
   
	@Override
	public void init() throws ServletException {
	    memberService = new MemberService();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 透過service取得member資料
	    List<Member> members = memberService.selectAll();
	    
	    // 再透過json送到前端
	    response.setContentType("application/json; charset=UTF-8");
	    try (
            PrintWriter pw = response.getWriter();
        ) {
	        pw.write(GSON.toJson(members));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
