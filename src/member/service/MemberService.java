package member.service;

import java.util.Arrays;
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
	
	//regiter 傳去後端用,同帳號回傳0。不同則存回data service。
	public int registInsert(Member memberRegist){
		
		if (compareAccountList(dao.selectAccountList(), memberRegist.getAccount()) != true) {
			return 0;
		}else {
			return dao.insertRegist(memberRegist);
		}
	}
	
	private boolean compareAccountList(List<Member> acc , String value) {
		return Arrays.asList(acc).contains(value);
	}
	
    public int deleteById(Integer id) {
        return dao.deleteById(id);
    }
    
    public List<Member> selectAll() {
        return dao.selectAll();
    }
}
