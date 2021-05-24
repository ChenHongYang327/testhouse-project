package member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import member.bean.Member;
import member.service.MemberService;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Gson gson = new Gson();
	private MemberService service;

	@Override
	public void init() throws ServletException {
		service = new MemberService();
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//前端JSON讀入，並傳至controller
		req.setCharacterEncoding("UTF-8");
		Member memberRegist = json2MemberRegist(req);
		
		//判斷是否有相同帳號
		if(service.registInsert(memberRegist) == 1) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			try (PrintWriter pw = resp.getWriter()){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("Response", "SUCCESS");
				pw.append(jsonObject.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			try (PrintWriter pw = resp.getWriter()){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("Response", "FAIL");
				pw.append(jsonObject.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//讀入前端json資料
	private Member json2MemberRegist(HttpServletRequest req) {
		try (BufferedReader br = req.getReader()){
			return gson.fromJson(br, Member.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	


}
