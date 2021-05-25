package member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.bean.Member;
import member.dao.MemberDao;



public class MemberDaoImpl implements  MemberDao{
    private DataSource dataSource;
    
    //建構子初始化
    public MemberDaoImpl() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    //註冊成功的值，存去資料庫
    @Override
    public int insertRegist(Member member) {
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
            		"insert into MEMBER(ACCOUNT,PASSWORD,NICKNAME) value (?, ?, ?);");
        ) {
            pstmt.setString(1, member.getAccount());
            pstmt.setNString(2, member.getPassword());
            pstmt.setString(3, member.getNickname());
            
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    //拿出ACCOUNT比，有值 
	@Override
	public int selectAccountExist(Member member) {
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select ACCOUNT from MEMBER where ACCOUNT= ?;");
		){
			pstmt.setString(1, member.getAccount());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

    
    
	
	
	
	
    @Override
    public int update(Member member) {
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
            		"update MEMBER set ACCOUNT = '?', PASSWORD = '?', NICKNAME = '?', PASS = ?, LAST_UPDATE_DATE = ? where ID = ?;");
        ) {
            pstmt.setString(1, member.getAccount());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getNickname());
            pstmt.setBoolean(4, member.getPass());
            pstmt.setTimestamp(5, member.getLastUpdateDate());
            pstmt.setInt(6, member.getId());
            
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return -1;
    }

    @Override
    public int deleteById (Integer id) {
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("delete from MEMBER where ID = ?;");
        ) {
            pstmt.setInt(1, id);
            
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return -1;
    }

    @Override
    public List<Member> selectAll() {
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from MEMBER;");
            ResultSet rs = pstmt.executeQuery();
        ) {
            List<Member> list = new ArrayList<Member>();
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getInt("ID"));
                member.setAccount(rs.getString("ACCOUNT"));
                member.setPassword(rs.getString("PASSWORD"));
                member.setNickname(rs.getString("NICKNAME"));
                member.setPass(rs.getBoolean("PASS"));
                member.setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
                member.setRoleId(rs.getInt("ROLE_ID"));
            
                list.add(member);
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public Member selectById(Integer id) {
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from MEMBER where ID = ?;");
        ) {
            pstmt.setInt(1, id);
            try (
                ResultSet rs = pstmt.executeQuery();
            ) {
                if (rs.next()) {
                    Member member = new Member();
                    member.setId(rs.getInt("ID"));
                    member.setAccount(rs.getString("ACCOUNT"));
                    member.setPassword(rs.getString("PASSWORD"));
                    member.setNickname(rs.getString("NICKNAME"));
                    member.setPass(rs.getBoolean("PASS"));
                    member.setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
                    member.setRoleId(rs.getInt("ROLE_ID"));
                    
                    return member;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
