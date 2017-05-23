

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by grazi on 18/05/17.
 */
public class Scrum {


    static public List<Historia> sprintBacklog(List<Historia> h, int sprintTime ) {


        int storyCount = h.size();
        int[][] T = new int[storyCount+1][sprintTime+1];

        //Historia ficticia con costo 0 y tiempo 0
        for (int j = 1;j<= storyCount;j++) {
            T[0][j] = 0;
        }

        for(int i = 1;i<= storyCount;i++) {
            for (int j = 1; j <= sprintTime ; j++) {

                if (j < h.get(i-1).getCosto()) {
                    T[i][j] = T[i-1][j];
                } else {
                    T[i][j] = Math.max(T[i-1][j],h.get(i-1).getValor() + T[i-1][j-h.get(i-1).getCosto()]);
                }
            }
        }


        int workTime = sprintTime;

        List<Historia> elegidos = new ArrayList<Historia>();

        for (int i = storyCount; i > 0; i--) {
            if(T[i][workTime]!= T[i-1][workTime]) {
                elegidos.add(h.get(i-1));
                workTime -= h.get(i-1).getCosto();
            }
        }

        return elegidos;

    }

    static public List<Historia> readBacklog(String path) {

        List<Integer> values = new ArrayList<Integer>();
        List<Integer> costs = new ArrayList<Integer>();
        List<String> descriptions = new ArrayList<String>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try( BufferedReader br = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream(path), "UTF-8"));) {
            StringBuilder sb = new StringBuilder();
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                if(line.length()!=0) {
                    String[] ar = line.split(",");
                    costs.add(Integer.parseInt(ar[0]));
                    values.add(Integer.parseInt(ar[1]));
                    descriptions.add(ar[2]);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Historia> historias = new ArrayList<Historia>();
        for(int i = 0;i < values.size();i++) {
            historias.add(new Historia(costs.get(i),values.get(i),descriptions.get(i)));
        }

        return historias;
    }

}
