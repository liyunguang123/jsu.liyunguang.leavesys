package Bean;

//请假表
public class LeaveTable  {
	private int lid;
	private int uid;
	private String userName;
	private String email;
	private String begin;
	private String end;
	private String reason;
	private String message;
	private	String state;
	//无lid的构造方法
	public LeaveTable(int uid,String name,String email, String begin, String end, String reason, String state) {
		this.uid=uid;
		this.userName=name;
		this.email=email;
		this.begin = begin;
		this.end = end;
		this.reason = reason;
		this.state = state;
	}
	public LeaveTable(int lid, int uid, String name, String email, String begin, String end, String reason, String state,String message) {
		this.lid=lid;
		this.uid = uid;
		this.userName=name;
		this.email=email;
		this.begin = begin;
		this.end = end;
		this.reason = reason;
		this.state = state;
		this.message=message;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	//获取保存所需属性
	public Object[] getSaveParaments() {
		Object[] param= {uid,userName,email,begin,end,reason,state};
		return param;
	}
	//获取所有属性,修改用
	public Object[] getAllParaments() {
		Object[] param= {uid,userName,email,begin,end,reason,state,lid};
		return param;
	}
	//setter和getter
	public void setLid(int lid) {
		this.lid = lid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setUserName(String username){this.userName=username;}

	public void setEmail(String email){
		this.email=email;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getLid() {
		return lid;
	}

	public int getUid() {
		return uid;
	}

	public String getUserName(){return  userName;}

	public String  getEmail(){return email;}

	public String getBegin() {
		return begin;
	}

	public String getEnd() {
		return end;
	}

	public String getReason() {
		return reason;
	}

	public String getState() {
		return state;
	}
}
