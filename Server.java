package kcsj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Server extends Thread{
    private Socket cl;
    private OutputStream ous;
    private User user;

    public Server(Socket cl) {
        this.cl=cl;
    }

    public User getOwerUser() {
        return this.user;
    }

    public void run() {
        try {
            Socket();
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Msg(String msg) throws Exception {
        msg+="\r\n";
        ous.write(msg.getBytes());
        ous.flush();
    }


    private void Socket() throws Exception {
// TODO Auto-generated method stub
        InputStream ins=cl.getInputStream();
        ous=cl.getOutputStream();
        BufferedReader brd=new BufferedReader(new InputStreamReader(ins));
        int count = 0;
        while (count <= 2) {
        	Msg("欢迎使用聊天系统，请输入你的用户名：");
            String id=brd.readLine();
            Msg("请输入密码：");
            String pwd=brd.readLine();
            user=new User();
            user.setId(id);
            user.setMima(pwd);
            //调用数据库，验证用户是否存在
            boolean loginState=Dao.checkLogin(user);
            if(!loginState) {
            	//如果不存在这个账号退出聊天
            	count ++;
            	Msg("用户名或密码错误！你还剩"+ (3-count) +"次机会！,请重新输入");
            	if (count == 3) {
            		System.out.println("最多三次机会");
            		this.closeMe();
		}
            }
            else {
            	System.out.println("欢迎"+id+"进入聊天室");
            	break;
            }
		}
 
        Chat.addClient(this);
        String input=brd.readLine();
        while(!input.equals("bye")) {
            System.out.println("服务器读到的是:"+input);
            Chat.castMsg(this.user, input);
            input=brd.readLine();
        }
        Chat.castMsg(this.user, "bye");
        this.closeMe();
    }

    public void closeMe() throws Exception {
    	Msg("退出系统！");
        cl.close();
    }
}
