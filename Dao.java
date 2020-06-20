package kcsj;

import java.util.HashMap;
import java.util.Map;

public class Dao {
    private static Map<String,User>User=new HashMap();
    static {
        for(int i=1;i<=10;i++) {
            User user=new User();
            user.setId("user"+i);
            user.setMima("psw"+i);
            User.put(user.getId(), user);
        }
    }

    public static boolean checkLogin(User user) {
        if(User.containsKey(user.getId())) {
            return true;
        }
        System.out.println(user.getId()+"ÑéÖ¤Ê§°Ü£¡");
        return false;
    }
}
