package kcsj;

public class User{
    private String Id;//�û���
    private String Mima;//����
	public User(String id, String mima) {
		super();
		Id = id;
		Mima = mima;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getMima() {
		return Mima;
	}
	public void setMima(String mima) {
		Mima = mima;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", Mima=" + Mima + "]";
	}

 
}
