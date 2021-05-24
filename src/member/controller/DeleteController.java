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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import member.service.MemberService;

@WebServlet("/member/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MemberService memberService;
	private static final Gson GSON = new Gson();
	
	@Override
	public void init() throws ServletException {
	    memberService = new MemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Integer deleteId = Integer.parseInt(request.getParameter("deleteId"));
	    
	    if (memberService.deleteById(deleteId) > 0) {
	        response.setContentType("application/json; charset=UTF-8");
	        try (
                PrintWriter writer = response.getWriter();
            ) {
	            JsonObject result = new JsonObject();
                result.addProperty("result_code", 1);
                writer.write(GSON.toJson(result));
            } catch (Exception e) {
                e.printStackTrace();
            }
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json; charset=UTF-8");
	    
	    try (
            BufferedReader reader = request.getReader();
            PrintWriter writer = response.getWriter();
	    ) {
	        // 收到沒有對應bean的JSON資料抓法
            JsonObject jsonObject = GSON.fromJson(reader, JsonObject.class);
            JsonElement element = jsonObject.get("deleteId");
            Integer deleteId = element.getAsInt();
            
            JsonObject result = new JsonObject();
            if (memberService.deleteById(deleteId) > 0) {
                result.addProperty("result_code", 1);
                writer.write(GSON.toJson(result));
            } else {
                result.addProperty("result_code", 0);
                writer.write(GSON.toJson(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
