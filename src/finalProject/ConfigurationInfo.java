package finalProject;

// perform XML parsing to get database configuration

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ConfigurationInfo {
    private static String username, password, driver, url;

    public ConfigurationInfo() {
    }

    public ConfigurationInfo(String user, String pass, String driver, String url) {
        this.username = user;
        this.password = pass;
        this.driver = driver;
        this.url = url;
    }

    public  String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    public  String getUrl() {
        return url;
    }
    
    @Override
    public String toString(){
        return "Username: " + username + "\nPassword: " + password + "\nDriver: " + driver + "\nURL: " + url;
    }
    
    public static ConfigurationInfo getDatabaseConfiguration(){
        ConfigurationInfo obj = null;
        String filePath = "lib\\DatabaseLogin.xml";
        
        try{
            File file = new File(filePath);
            if(file.exists() == false)
            {
                System.out.println("XML file not found!!");
                return null;
            }
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(file);
            
            NodeList nList = document.getElementsByTagName("Info");
            Node node = nList.item(0);
            
            Element elt = (Element)node;
            
            url = elt.getElementsByTagName("url").item(0).getTextContent();
            username = elt.getElementsByTagName("username").item(0).getTextContent();
            password = elt.getElementsByTagName("password").item(0).getTextContent();
            driver = elt.getElementsByTagName("driver").item(0).getTextContent();
            
            obj = new ConfigurationInfo(username, password, driver, url);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return obj;
    }
}
