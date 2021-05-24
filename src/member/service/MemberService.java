package member.service;

import member.bean.Member;
import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;

public class MemberService {
	public MemberDao dao;
	
	//初始化MemberDao , 不對成宣告
	public MemberService() {
		dao = new MemberDaoImpl();
	}
	
	//regiter 傳去後端用,同帳號回傳0。不同則存回data service且傳1。
	public int registInsert(Member memberRegist){
		
		if ( dao.selectAccountExist(memberRegist) == 0 ) {
			dao.insertRegist(memberRegist);
			return 1;
		 
		}else {
			return 0;
		}
	}

}
