package member.service;

import java.util.List;

import member.bean.Member;
import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;

public class MemberService {
	public MemberDao dao;
	
	//初始化MemberDao , 不對成宣告
	public MemberService() {
		dao = new MemberDaoImpl();
	}
	
	//regiter 傳去後端用
	public int registInsert(Member memberRegist){
		return 	dao.insertRegist(memberRegist);
	}
	

}
