import java.io.*;

import javax.json.*;
//import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonHandler {


    public JsonHandler(){}

    public void readFile(Library lib , String filePath) {

        try ( Reader rd = new FileReader(filePath)) {
            JsonReader jrd = Json.createReader(rd);
            Object typeTest = jrd.readValue();

            if (typeTest instanceof JsonObject bookObject) { // for files with only one book objects
                
                inputFromJson(lib ,bookObject);

            } else if (typeTest instanceof JsonArray libArray) { // for files with multiple book objects

                for (JsonValue book : libArray) {

                    JsonObject bookObject = (JsonObject) book;
                    inputFromJson(lib ,bookObject);

                }
            }


        } catch (IOException e) {
            //tbd
            System.out.println(e);
        }
    }
// çoklu kelimeler için ayırarak ekleyeceğiz
    private static void inputFromJson(Library lib , JsonObject bookObject) {
        Book input = new Book();

        if (bookObject.containsKey("title")) {
            String title = bookObject.getString("title");
            input.setTitle(title);
        }
        
        if (bookObject.containsKey("subtitle")) {
            String subtitle = bookObject.getString("subtitle");
            input.setSubtitle(subtitle);
        }

        if (bookObject.containsKey("authors")) {
            JsonValue aut = bookObject.get("authors"); // to check whether the input is a string or an array
            ArrayList<String> authors = new ArrayList<>();
            if (aut.getValueType() == JsonValue.ValueType.STRING) {   // for string inputs
                String author = bookObject.getString("authors");
                authors.add(author);
            } else {                                                // for array inputs
                JsonArray temp = bookObject.getJsonArray("authors");
                for (JsonValue authorJson : temp) {
                    String author = authorJson.toString().substring( 1, authorJson.toString().length() - 1 );
                    authors.add(author);
                }
            }
            input.setAuthors(authors);
        }

        if (bookObject.containsKey("translators")) {
            JsonValue tran = bookObject.get("translators"); // to check whether the input is a string or an array
            ArrayList<String> translators = new ArrayList<>();
            if (tran.getValueType() == JsonValue.ValueType.STRING) { // for string inputs
                String translator = bookObject.getString("translators");
                translators.add(translator);
            } else {                                                // for array inputs
                JsonArray temp = bookObject.getJsonArray("translators");
                for (JsonValue translatorJson : temp) {
                    String translator = translatorJson.toString().substring( 1, translatorJson.toString().length() - 1 );
                    translators.add(translator);
                }
            }
            input.setTranslators(translators);
        }

        if (bookObject.containsKey("isbn")) {
            String isbn = bookObject.getString("isbn");
            input.setIsbn(isbn);
        }

        if (bookObject.containsKey("publisher")) {
            String publisher = bookObject.getString("publisher");
            input.setPublisher(publisher);
        }

        if (bookObject.containsKey("date")) {
            String date = bookObject.getString("date");
            input.setDate(date);
        }

        if (bookObject.containsKey("edition")) {
            String edition = bookObject.getString("edition");
            input.setEdition(edition);
        }
        if (bookObject.containsKey("cover")) {
            String cover = bookObject.getString("cover");
            input.setCover(cover);
        }

        if (bookObject.containsKey("language")) {
            String language = bookObject.getString("language");
            input.setLanguage(language);
        }

        if (bookObject.containsKey("rating")) {
            String rating = bookObject.getString("rating");
            input.setRating(rating);
        }

        if (bookObject.containsKey("tags")) {
            JsonValue tag = bookObject.get("tags"); // to check whether the input is a string or an array
            ArrayList<String> tagList = new ArrayList<>();
            if (tag.getValueType() == JsonValue.ValueType.STRING) { // for string inputs
                String tagS = bookObject.getString("tags");
                tagList.add(tagS);
            } else {                                                // for array inputs
                JsonArray temp = bookObject.getJsonArray("tags");
                for (JsonValue tagJson : temp) {
                    String tagS = tagJson.toString().substring( 1, tagJson.toString().length() - 1 );
                    tagList.add(tagS);
                }
            }
            
            input.setTags(tagList);
        }

        ArrayList<String> attrList = new ArrayList<>();
        String[] divide ;
        if(input.getTitle()!=null) {
            divide = input.getTitle().split(" ");
            for (int l = 0;l<divide.length;l++) {
                attrList.add(divide[l]);
            }
        }
        if(input.getSubtitle()!=null) {
            divide = input.getSubtitle().split(" ");
            for (int l = 0;l<divide.length;l++) {
                attrList.add(divide[l]);
            }
        }
        //autohor kelimeleri ayırıyor 
        if(input.getAuthors()!=null) {
            for(int i = 0;i<input.getAuthors().size();i++){
                divide = input.getAuthors().get(i).split(" ");
                for (int l = 0;l<divide.length;l++) {
                    attrList.add(divide[l]);
                }
            }
        }
        //aynı şekilde translator
        if(input.getTranslators()!=null) {
            for(int i = 0;i<input.getTranslators().size();i++){
                divide = input.getTranslators().get(i).split(" ");
                for (int l = 0;l<divide.length;l++) {
                    attrList.add(divide[l]);
                }
            }
        }

        if(input.getIsbn()!=null) {
            divide = input.getIsbn().split(" ");
            for (int l = 0;l<divide.length;l++) {
                attrList.add(divide[l]);
            }
        }
        if(input.getPublisher()!=null) {
            divide = input.getPublisher().split(" ");
            for (int l = 0;l<divide.length;l++) {
                attrList.add(divide[l]);
            }
        }
        if(input.getDate()!=null) {
            divide = input.getDate().split(" ");
            for (int l = 0;l<divide.length;l++) {
                attrList.add(divide[l]);
            }
        }
        if(input.getEdition()!=null) {
            divide = input.getEdition().split(" ");
            for (int l = 0;l<divide.length;l++) {
                attrList.add(divide[l]);
            }
        }
        if(input.getLanguage()!=null) {
            divide = input.getLanguage().split(" ");
            for (int l = 0;l<divide.length;l++) {
                attrList.add(divide[l]);
            }
        }
        if(input.getRating()!=null) {
            divide = input.getRating().split(" ");
            for (int l = 0;l<divide.length;l++) {
                attrList.add(divide[l]);
            }
        }
        if(input.getCover()!=null) {
            divide = input.getCover().split(" ");
            for (int l = 0;l<divide.length;l++) {
                attrList.add(divide[l]);
            }
        }

        
        if(input.getTags()!=null) {
            for(int i = 0;i<input.getTags().size();i++){
                divide = input.getTags().get(i).split(" ");
                for (int l = 0;l<divide.length;l++) {
                    attrList.add(divide[l]);
                }
            }
        }
        input.setAttr(attrList);
        lib.addToLib(input);

        boolean ckck = false;
        for (int m = 0;m<lib.getLibraries().size();m++){
            if(lib.getLibraries().get(m).getIsbn().equals(input.getIsbn())){
                ckck= true;
                break;
            }
        }
        if(!ckck){lib.addToLib(input);}
    }

    public void writeToJson(Library input , String filePath , boolean mode) {
        ArrayList<Book> lib = input.getLibraries();
        boolean printed = false;
        if (input.getSize() == 0) {
            // gui will print an error due to empty input
        } else  {
            try (FileWriter fw = new FileWriter(filePath , mode)) {
                JsonWriter jw = Json.createWriter(fw);
                JsonArrayBuilder output = Json.createArrayBuilder();

                for ( int i = 0 ; i < input.getSize() ; i++) {
                    JsonObjectBuilder obj = Json.createObjectBuilder();
                    if (lib.get(i).getTitle() != null) {
                        obj.add("title" , lib.get(i).getTitle());
                    }

                    if (lib.get(i).getSubtitle() != null) {
                        obj.add("subtitle" , lib.get(i).getSubtitle());
                    }

                    if (lib.get(i).getAuthors() != null && !(lib.get(i).getAuthors().isEmpty()) ) {
                        if (lib.get(i).getAuthors().size() == 1) {
                            obj.add("authors" , lib.get(i).getAuthors().get(0));
                        } else {
                            JsonArrayBuilder aut = Json.createArrayBuilder();
                            for ( String author : lib.get(i).getAuthors()) {
                                aut.add(author);
                            }
                            obj.add("authors" , aut);
                        }
                    }

                    if (lib.get(i).getTranslators() != null && !(lib.get(i).getTranslators().isEmpty()) ) {
                        if (lib.get(i).getTranslators().size() == 1) {
                            obj.add("translators" , lib.get(i).getTranslators().get(0));
                        } else {
                            JsonArrayBuilder tran = Json.createArrayBuilder();
                            for ( String translator : lib.get(i).getTranslators()) {
                                tran.add(translator);
                            }
                            obj.add("translators" , tran);
                        }
                    }

                    if (lib.get(i).getIsbn() != null) {
                        obj.add("isbn" , lib.get(i).getIsbn());
                    }

                    if (lib.get(i).getPublisher() != null) {
                        obj.add("publisher" , lib.get(i).getPublisher());
                    }

                    if (lib.get(i).getDate() != null) {
                        obj.add("date" , lib.get(i).getDate());
                    }

                    if (lib.get(i).getEdition() != null) {
                        obj.add("edition" , lib.get(i).getEdition());
                    }

                    if (lib.get(i).getCover() != null) {
                        obj.add("cover" , lib.get(i).getCover());
                    }

                    if (lib.get(i).getLanguage() != null) {
                        obj.add("language" , lib.get(i).getLanguage());
                    }

                    if (lib.get(i).getRating() != null) {
                        obj.add("rating" , lib.get(i).getRating());
                    }

                    if (lib.get(i).getTags() != null && !(lib.get(i).getTags().isEmpty()) ) {
                        if (lib.get(i).getTags().size() == 1) {
                            obj.add("tags" , lib.get(i).getTags().get(0));
                        } else {
                            JsonArrayBuilder tags = Json.createArrayBuilder();
                            for ( String tag : lib.get(i).getTags()) {
                                tags.add(tag);
                            }
                            obj.add("tags" , tags);
                        }
                    }
                    if (input.getSize() == 1) {
                        jw.writeObject(obj.build());
                        printed = true;
                    } else {
                        output.add(obj);
                    }
                }
                if ( !printed ) {
                    jw.writeArray(output.build());
                }


            } catch (IOException e) {
                //
            }
        }
    }

}