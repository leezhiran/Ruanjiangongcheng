package com.example.ruanjiangongcheng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**All methods in this class must be run in a separate thread or async task.
 * Implements internet connections with the sever.
 * */
public class InternetInterface {
    private final String addr="http://localhost:8080/DealCards/";
    private List<String> ret;
    private HttpURLConnection connection=null;
    /**Default constructor of InternetInterface
     * Use method @code {getRet()} after construction to get returns.
     * Make connection to the server.
     * @param target The server you want to make connection with.
     * @param args A list of internet arguments.First indicates key,second indicates value
     * */
    public InternetInterface(Map<String,String> args,String target){
        Thread r=new Thread(()->{
            try {
                HttpURLConnection c = (HttpURLConnection) new URL(addr + target).openConnection();
                c.setConnectTimeout(5000);
                c.setDoOutput(true);
                c.setDoInput(true);
                c.setRequestMethod("POST");
                c.setUseCaches(false);
                c.connect();
                connection = c;
                ret = doAction(args, target);
            }catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    /**
     *Do actions on certain server.
     * @param args A list of internet arguments.First indicates key,second indicates value
     * @param target Where you want send instructions to.
     * @return A list of returned strings.
     * */
    private List<String> doAction(Map<String,String> args, String target) {
        List<String> ret1=new ArrayList<String>();
        String ret=new String();
        for(Map.Entry<String,String> i:args.entrySet()) {
            ret+=i.getKey()+'='+i.getValue();
            ret+='&';
        }
        StringBuilder strBuilder=new StringBuilder(ret);
        if(strBuilder.charAt(strBuilder.length()-1)=='&') {
            strBuilder.deleteCharAt(strBuilder.length()-1);
        }
        try {
            OutputStream pw=connection.getOutputStream();
            pw.write(strBuilder.toString().getBytes());
            BufferedReader is=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s=new String();
            while((s = is.readLine()) != null) {
                ret1.add(s);
            }
            pw.close();
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        args.clear();
        return ret1;
    }
    public List<String> getRet() {
        return ret;
    }

}
