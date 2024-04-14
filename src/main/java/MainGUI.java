import java.util.ArrayList;

public class MainGUI {
    public static void main(String[] args) {

        /*System.out.println(lib.getLibraries().get(0).getTitle());*/

        //attribute deneme (çalışıyor)
        /*for(int i = 0 ; i<lib.getLibraries().get(0).getAttr().size();i++){
            System.out.println(lib.getLibraries().get(0).getAttr().get(i));
        }*/
        

        // search deneme (çalışıyor)

        //create book yazıyorum
        //irtibar : tuna
        /*Scanner sc = new Scanner(System.in);
        System.out.println("type title");
        String inputtitle = sc.nextLine();
        ArrayList<String> typeauthor = new ArrayList<String>();
        System.out.println("type authors until you are done");
        boolean done = false;
        while (!done) {
            String inputauthor = sc.nextLine();
            typeauthor.add(inputauthor);
            done = true;
        }
        ArrayList<String> typetranslator = new ArrayList<String>();
        typetranslator.add("jhon wick");
        lib.createBook(lib, inputtitle, "xsubtit", typeauthor, typetranslator, "911", "görkem", "09.08.1990", "3", "hard", "en", "7", null);
        */

        // tag return error denemesi (çözüldü)
        /*lib.booktagprint(lib.getLibraries().get(0));
        lib.booktagprint(lib.getLibraries().get(1));*/

        //tagla aramak (çalışıyor)
        /*ArrayList<String> selectedTags = new ArrayList<String>();
        selectedTags.add("frank");
        lib.listByTags(selectedTags);*/

        // bakılacak
        /*lib.getLibraries().get(0).editTitle("selam1 patlican", lib.getLibraries().get(0));
        for(int i = 0 ; i<lib.getLibraries().get(0).getAttr().size();i++){
            System.out.println(lib.getLibraries().get(0).getAttr().get(i));
        }*/



        // yarı çalışıyor (false (çalışıyor)) (true (çalışmıyor))
        //jsn.writeToJson(lib, "test.json", false);
        GUI.main(args);
    }
    
}


