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
        	Msg("��ӭʹ������ϵͳ������������û�����");
            String id=brd.readLine();
            Msg("���������룺");
            String pwd=brd.readLine();
            user=new User();
            user.setId(id);
            user.setMima(pwd);
            //�������ݿ⣬��֤�û��Ƿ����
            boolean loginState=Dao.checkLogin(user);
            if(!loginState) {
            	//�������������˺��˳�����
            	count ++;
            	Msg("�û�������������㻹ʣ"+ (3-count) +"�λ��ᣡ,����������");
            	if (count == 3) {
            		System.out.println("������λ���");
            		this.closeMe();
		}
            }
            else {
            	System.out.println("��ӭ"+id+"����������");
            	break;
            }
		}
 
        Chat.addClient(this);
        String input=brd.readLine();
        while(!input.equals("bye")) {
            System.out.println("��������������:"+input);
            Chat.castMsg(this.user, input);
            input=brd.readLine();
        }
        Chat.castMsg(this.user, "bye");
        this.closeMe();
    }

    public void closeMe() throws Exception {
    	Msg("�˳�ϵͳ��");
        cl.close();
    }
}
