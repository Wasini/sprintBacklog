



import java.util.HashMap;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        //testScrum

        HashMap<Integer,List<Historia>> sprints = new HashMap<Integer,List<Historia>>();
        List<Historia> historias = Scrum.readBacklog("file.txt");

        int i = 0;
        while(!historias.isEmpty()){
            
            int sprintTime = args.length==0? 20 : Integer.valueOf(args[0]);

            List<Historia> SprintBacklog = Scrum.sprintBacklog(historias, sprintTime);

            sprints.put(i,SprintBacklog);
            for (Historia h : SprintBacklog
                    ) {
                int index = historias.indexOf(h);
                historias.remove(index);
            }

            i++;
        }

        for (int j = 0; j< i; j++ ) {
            System.out.println("Sprint numero: "+j);
            System.out.println(sprints.get(j).toString());

        }

    }
}

