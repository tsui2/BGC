import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

// Driver code
public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String[]> list = new ArrayList<String[]>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        File file = new File(args[0]);    //creates a new file instance
        FileReader fr = new FileReader(file);   //reads the file
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
        String line;
        int id = 0;
        while ((line = br.readLine()) != null) {
            String[] temp = line.split("\\s*,\\s*");
            list.add(temp);
            if (!map.containsKey(temp[0])) {
                map.put(temp[0], id++);
            }
            if (!map.containsKey(temp[1])) {
                map.put(temp[1], id++);
            }
        }
        fr.close();    //closes the stream and release the resources
        DisjointUnionSets dus =
                new DisjointUnionSets(map.size());
        list.forEach((item) -> dus.union(map.get(item[0]), map.get(item[1])));
        if (!map.containsKey(args[1]) || !map.containsKey(args[2]))
            System.out.println("No");
        else {
            if (dus.find(map.get(args[1])) == dus.find(map.get(args[2])))
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}
