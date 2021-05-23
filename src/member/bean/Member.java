package member.bean;

import java.sql.Timestamp;

public class Member {

	private Integer id;
	private String account;
	private String password1;
	private String nickname;
	private Boolean pass;
	private Timestamp lastUpdateDate;
	private String img;
	
	
	
	
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
			Timestamp lastUpdateDate) {
		super();
		this.id = id;
		this.account = account;
		this.password1 = password1;
		this.nickname = nickname;
		this.pass = pass;
		this.lastUpdateDate = lastUpdateDate;
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
		return password1;
	}

	public void setPassword(String password1) {
		this.password1 = password1;
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
	
	
}
