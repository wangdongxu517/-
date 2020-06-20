package kcsj;

import java.io.IOException;
import java.util.ArrayList;

public class Chat {
    private static ArrayList<Server> stList=new ArrayList();
    private Chat() {}
    public static void addClient(Server st) throws Exception {
        stList.add(st);
        castMsg(st.getOwerUser(), null);
    }
    public static void castMsg(User sender,String msg) throws Exception {
        msg = sender.getId()+"£º"+msg;
        for(int i=0;i<stList.size();i++) {
            Server st=stList.get(i);
            st.Msg(msg);
        }
    }    
}
    
