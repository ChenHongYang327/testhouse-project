package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import member.service.MemberService;
import member.bean.Member;

public class LoginCheck extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String username, password;
	MemberService Member=new MemberService();
	//自訂JSON內容
	JsonObject jsonObject=new JsonObject();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson GSON = new GsonBuilder().create();
		Member tmpMember = GSON.fromJson(request.getReader(), Member.class);
		HttpSession session = request.getSession();
		List<Member> allMember = Member.selectAll();
		for (Member tmp : allMember) {
			if (tmpMember.getAccount().equals(tmp.getAccount()) && tmpMember.getPassword().equals(tmp.getPassword())
					&& tmp.getRoleId() == 1) {// 管理員
				try (PrintWriter pw = response.getWriter()) {
					
					jsonObject.addProperty("Status", "SUCCESS");
					pw.write(jsonObject.toString());
					
//					pw.print(GSON.toJson(Member.selectById(tmp.getId())));
					session.setAttribute("nickname", tmp.getNickname());
					session.setAttribute("ROLEID", "1");
					session.setAttribute("ID",tmp.getId());
//					session.setAttribute("username", tmp.getAccount());
//					session.setAttribute("password", tmp.getPassword());
//					System.out.println("admin");			
//					System.out.println(GSON.toJson(Member.selectById(tmp.getId())));
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return;
			}
			if (tmpMember.getAccount().equals(tmp.getAccount()) && tmpMember.getPassword().equals(tmp.getPassword())
					&& tmp.getRoleId() == 2) {// 一般使用者		
				try (PrintWriter pw = response.getWriter()) {
					jsonObject.addProperty("Status", "SUCCESS");
					pw.write(jsonObject.toString());
//					pw.print(GSON.toJson(Member.selectById(tmp.getId())));
					session.setAttribute("nickname", tmp.getNickname());
					session.setAttribute("ROLEID", "2");
					session.setAttribute("ID",tmp.getId());
//					session.setAttribute("username", tmp.getAccount());
//					session.setAttribute("password", tmp.getPassword());
//					System.out.println("user");		
//					System.out.println(GSON.toJson(Member.selectById(tmp.getId())));
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try(PrintWriter pw=response.getWriter()) {
			jsonObject.addProperty("Status", "FAIL");
			pw.write(jsonObject.toString());
//			System.out.println(jsonObject.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

}
