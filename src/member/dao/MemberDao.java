package member.dao;

import java.util.List;

import member.bean.Member;


public interface MemberDao {
	//regist 用
	int insertRegist(Member member);
	
	//regist 用
	List<Member> selectAccountList();
	
	int deleteById(Integer id);
	
	int update(Member member);
	
	Member selectById(Integer id);
	
	List<Member> selectAll();

}
