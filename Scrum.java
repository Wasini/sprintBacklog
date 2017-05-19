

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


    /**
     * Pre: Las historias estan ordenadas por costo de tiempo de forma creciente
     * @param storyValues Valores de las historias del backlog
     * @param storyCost Costo de tiempo estimado de las historias del backlog
     * @param sprintTime Tiempo total del sprint
     * @return Matriz bidimensional con cada columna representando una hora del sprint y en la linea i
     * esta la mejor suma de valores para el tiempo j eligiendo hasta los i'esimas historias
     */
    static public int[][] dynSprintTimeValue(Integer[]storyCost,Integer[] storyValues, int sprintTime ) {

        int storyCount = storyValues.length;
        int[][] T = new int[storyCount+1][sprintTime+1];

        //Historia ficticia con costo 0 y tiempo 0
        for (int j = 1;j<= storyCount;j++) {
            T[0][j] = 0;
        }

        for(int i = 1;i<= storyCount;i++) {
            for (int j = 1; j <= sprintTime ; j++) {

                if (j < storyCost[i-1]) {
                    T[i][j] = T[i-1][j];
                } else {
                    T[i][j] = Math.max(T[i-1][j],storyValues[i-1] + T[i-1][j-storyCost[i-1]]);
                }
            }
        }

        return T;
    }


//Retorna lista de indeces de las historias elegidas por la matriz de TiempoxValor
    static public List<Integer> tracePickedStories(int[][] T, Integer[] storyCost) {
        int totalStories = T.length;
        int workTime = T[0].length-1;

        List<Integer> indexs = new ArrayList<Integer>();


        for (int i = totalStories-1; i > 0; i--) {
            if(T[i][workTime]!= T[i-1][workTime]) {
                indexs.add(i-1);
                workTime -= storyCost[i-1];
            }
        }

        return indexs;
    };


//Carga backlog desde un txt con dir @path
    static public void readBacklog(String path,List<Integer> costs,List<Integer> values,List<String> descriptions) {

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
    }

}
