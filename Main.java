



import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        //testScrum

        List<Integer> values = new ArrayList<Integer>();
        List<Integer> cost = new ArrayList<Integer>();
        List<String> desc = new ArrayList<String>();

        Scrum.readBacklog("file.txt",cost,values,desc);

/*
        System.out.println(values.toString());
        System.out.println(cost.toString());
        System.out.println(desc.toString());
*/

        //convertir lista a arreglo de Integer
        Integer[] v = values.toArray(new Integer[values.size()]);
        Integer[] t = values.toArray(new Integer[cost.size()]);

        //Obtener la matriz de tiempoxvalor con un sprint de 10 horas
        int[][] d = Scrum.dynSprintTimeValue(t,v,10);


        //Obtener indices de las historias elegidas para el primer sprint
        //@d : Matriz
        //@t : Arreglo de estimacion de tiempo costo
        List<Integer> p = Scrum.tracePickedStories(d,t);


        System.out.println(Arrays.deepToString(d));
        System.out.println(p.toString());


        System.out.println("Primer sprint:\nDescripcion-valor-costo");
        StringBuilder sb = new StringBuilder();
        for (int index:p
             ) {
            sb.append(desc.get(index)+"-"+values.get(index)+"-"+cost.get(index));
            sb.append(System.getProperty("line.separator"));
        }
        System.out.println(sb.toString());

    }
}

