package kcsj;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class Client extends Thread{
    InputStream input=null;
    OutputStream output=null;
    BufferedReader bufferinput=null;
    Socket socket=null;
    int i=30;
    
    public Client(){
        //��ʼ��ʱ���ӷ�����
        try {
            socket=new Socket("127.0.0.1",8572);
            input=socket.getInputStream();
            output=socket.getOutputStream();
            //��֤�û���Ϣ
            login();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    public static void main(String[] args){
        Client cl=new Client();
    }

    public void login(){
        try {
            int Login=0;
            bufferinput=new BufferedReader(new InputStreamReader(input));
            String line;
            while(Login<2){
                //��ȡ��Ϣ
                if((line=bufferinput.readLine())!=null){
                    System.out.println(line);
                }

                //������Ϣ
                BufferedReader brName = new BufferedReader(new InputStreamReader(System.in));
                String strName;
                strName = brName.readLine()+"\r\n";
                output.write(strName.getBytes());
                output.flush();
                Login++;
            }
            chat();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void chat() {
        start();
        bufferinput=new BufferedReader(new InputStreamReader(input));
        String line;
        try {
            while(true){
                if((line=bufferinput.readLine())!=null){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(socket!=null){
                try {
                    socket.close();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {
        while(true){
            BufferedReader brName = new BufferedReader(new InputStreamReader(System.in));
            String strName;
            try {
                strName = brName.readLine()+"\r\n";
                output.write(strName.getBytes());
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void run1() throws Exception {
    	while(true){
    		i--;
    		Thread.sleep(1000);
    		if(i==0) {
    			String getURL="http://www.turingapi.com/";
    			URL getUrl=new URL(getURL);
    			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
    			connection.connect(); 

    			// ȡ������������ʹ��Reader��ȡ 
    			BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8")); 
    			StringBuffer sb = new StringBuffer(); 
    			String line = ""; 
    			while ((line = reader.readLine()) != null) { 
    				sb.append(line); 
    			} 
    			reader.close(); 
    			// �Ͽ����� 
    			connection.disconnect(); 
    			System.out.println(sb); 

    		}
		}
	}		        
}
