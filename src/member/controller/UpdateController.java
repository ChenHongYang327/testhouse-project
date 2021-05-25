package member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import member.bean.Member;
import member.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/edit")
public class UpdateController extends HttpServlet {
	private MemberService memberService;
	private static final Gson GSON = new Gson();

	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		try (
			BufferedReader reader = request.getReader(); 
			 PrintWriter writer = response.getWriter();) 
		
		{	
			JsonObject jsonObject = GSON.fromJson(reader, JsonObject.class);
			String password = jsonObject.get("password1").getAsString();
			String nickname = jsonObject.get("nickname").getAsString();
			
			
			Member member = new Member();
			member.setPassword(password);
			member.setNickname(nickname);
			member.setLastUpdateDate(new Timestamp(System.currentTimeMillis()));
			member.setRoleId(2);
			member.setId((int) request.getSession().getAttribute("ID"));

			Map<String, Integer> result = new HashMap<String, Integer>();
			if (memberService.update(member) > 0) {
				result.put("result_code", 1);
				writer.write(GSON.toJson(result));
			} else {
				result.put("result_code", 0);
				writer.write(GSON.toJson(result));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
