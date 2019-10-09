/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webhook;

/**
 *
 * @author alison.louro
 */
import java.util.ArrayList;
import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;





public class Webhook {
    public static ArrayList<String> DataList;

    


    public static void main(String args []) throws FileNotFoundException, IOException  {
        // TODO code application logic here
      
	DataList = new ArrayList<>();

        String response;
                
                //Path para arquivo
                File logReturn = new File("/Users/alison.louro/Downloads/TESTE/log.txt");
                BufferedReader log = new BufferedReader(new FileReader(logReturn));
                    
                    //Leitura, filtro e armazenamento das informações importantes
                    while ((response = log.readLine()) != null) {
			    String[] words = response.split("\"");
                                //System.out.println(response);
                        for (String word : words) {
                            if (word.matches("\\b((https?)://|())\\S+")) {
                                DataList.add(word);
                        }
                                //System.out.println(fullUrlList);
                            }
                    }
                //Map para separar as URLs
                Map<String, Integer> urls = new HashMap<>();
                   
                    DataList.forEach((str) -> {
                        if (str.contains("http")){
                            if (urls.containsKey(str)) {
                                urls.put(str, urls.get(str) + 1);
                            } 
                            else {
                                urls.put(str, 1);
                            }          
                        }
                    }
                    );
                //stream para ordernar e printar URLs    
                urls.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3).forEach((entry) -> {
      
          
                        System.out.println(entry.getKey()+ " - " + entry.getValue());
                        
                        
                }
                );
                //Map para armazenar status 
                Map<String, Integer> status = new HashMap<>();
                    DataList.forEach((sta) -> {
                        if (sta.matches("[0-9]+")){
                        if (status.containsKey(sta)) {
                            status.put(sta, status.get(sta) + 1);
                        } else {
                            status.put(sta, 1);
                        }          
                        
                        }
                    }
                    );
                
                //stream para ordernar e printar status
                status.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.naturalOrder())).forEach((entry) -> {
 
                        System.out.println(entry.getKey()+ " - " + entry.getValue());
                   
                    
                    
                }
                );
    }   
}