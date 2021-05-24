package member.bean;

import java.sql.Timestamp;

public class Member {

	private Integer id;
	private String account;
	private String password;
	private String nickname;
	private Boolean pass;
	private Timestamp lastUpdateDate;
	private String img;
	private Integer roleId;
	
	
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Member() {
		super();
	}

	public Member(Integer id, String account, String password, String nickname, Boolean pass,
			Timestamp lastUpdateDate, Integer roleId) {
		super();
		this.id = id;
		this.account = account;
        this.password = password;
		this.nickname = nickname;
		this.pass = pass;
		this.lastUpdateDate = lastUpdateDate;
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password1) {
		this.password = password1;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
	
}
