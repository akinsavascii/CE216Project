import java.io.*;

import javax.json.*;
import javax.json.stream.JsonGenerator;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonHandler {


    public JsonHandler(){}

    public void readFile(Library lib , String filePath) {

        try ( FileReader rd = new FileReader(filePath)) {
            File file = new File(filePath);
            if (file.length() != 0) { // File is empty and will crash the program
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
            }



        } catch (IOException e) {
            //tbd
            System.out.println(e);
        }
    }
// çoklu kelimeler için ayırarak ekleyeceğiz
    private static void inputFromJson(Library lib , JsonObject bookObject) {
        Book input = new Book();

        if (bookObject.containsKey("title") && !(bookObject.isNull("title")) ) {
            String title = bookObject.getString("title");
            input.setTitle(title);
        }
        
        if (bookObject.containsKey("subtitle") && !(bookObject.isNull("subtitle")) ) {
            String subtitle = bookObject.getString("subtitle");
            input.setSubtitle(subtitle);
        }

        if (bookObject.containsKey("authors") && !(bookObject.isNull("authors")) ) {
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

        if (bookObject.containsKey("translators") && !(bookObject.isNull("translators")) ) {
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

        if (bookObject.containsKey("isbn") && !(bookObject.isNull("isbn")) ) {
            String isbn = bookObject.getString("isbn");
            input.setIsbn(isbn);
        }

        if (bookObject.containsKey("publisher") && !(bookObject.isNull("publisher")) ) {
            String publisher = bookObject.getString("publisher");
            input.setPublisher(publisher);
        }

        if (bookObject.containsKey("date") && !(bookObject.isNull("date")) ) {
            String date = bookObject.getString("date");
            input.setDate(date);
        }

        if (bookObject.containsKey("edition") && !(bookObject.isNull("edition")) ) {
            String edition = bookObject.getString("edition");
            input.setEdition(edition);
        }
        if (bookObject.containsKey("cover") && !(bookObject.isNull("cover")) ) {
            String cover = bookObject.getString("cover");
            input.setCover(cover);
        }

        if (bookObject.containsKey("language") && !(bookObject.isNull("language")) ) {
            String language = bookObject.getString("language");
            input.setLanguage(language);
        }

        if (bookObject.containsKey("rating") && !(bookObject.isNull("rating")) ) {
            String rating = bookObject.getString("rating");
            input.setRating(rating);
        }

        if (bookObject.containsKey("tags") && !(bookObject.isNull("tags")) ) {
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
        } else if (!bookObject.containsKey("tags")) {
            ArrayList<String> tagList = new ArrayList<>();
            tagList.add(" ");
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
        if ( !(lib.isDuplicate(input)) ) {
            lib.addToLib(input);
        }



    }

    public void writeToJson(Library input , String filePath , boolean mode) {
        ArrayList<Book> lib = input.getLibraries();
        Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(config);
        boolean printed = false;
        if (input.getSize() == 0) {
            try (PrintWriter pw = new PrintWriter("test.json")) {
                pw.close();
            } catch (Exception ignored) {

            }
        } else  {
            try (FileWriter fw = new FileWriter(filePath , mode)) {
                JsonWriter jw = writerFactory.createWriter(fw);
                JsonArrayBuilder output = Json.createArrayBuilder();

                for ( int i = 0 ; i < input.getSize() ; i++) {
                    JsonObjectBuilder obj = Json.createObjectBuilder();
                    if (lib.get(i).getTitle() != null && !lib.get(i).getTitle().isEmpty() ) {
                        obj.add("title" , lib.get(i).getTitle());
                    } else {
                        obj.addNull("title");
                    }

                    if (lib.get(i).getSubtitle() != null && !lib.get(i).getSubtitle().isEmpty()) {
                        obj.add("subtitle" , lib.get(i).getSubtitle());
                    } else {
                        obj.addNull("subtitle");
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
                    } else {
                        obj.addNull("authors");
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
                    } else {
                        obj.addNull("translators");
                    }

                    if (lib.get(i).getIsbn() != null && !lib.get(i).getIsbn().isEmpty()) {
                        obj.add("isbn" , lib.get(i).getIsbn());
                    }  else {
                        obj.addNull("isbn");
                    }

                    if (lib.get(i).getPublisher() != null && !lib.get(i).getPublisher().isEmpty()) {
                        obj.add("publisher" , lib.get(i).getPublisher());
                    }  else {
                        obj.addNull("publisher");
                    }

                    if (lib.get(i).getDate() != null && !lib.get(i).getDate().isEmpty()) {
                        obj.add("date" , lib.get(i).getDate());
                    }  else {
                        obj.addNull("date");
                    }

                    if (lib.get(i).getEdition() != null && !lib.get(i).getEdition().isEmpty()) {
                        obj.add("edition" , lib.get(i).getEdition());
                    }  else {
                        obj.addNull("edition");
                    }

                    if (lib.get(i).getCover() != null && !lib.get(i).getCover().isEmpty()) {
                        obj.add("cover" , lib.get(i).getCover());
                    }  else {
                        obj.addNull("cover");
                    }

                    if (lib.get(i).getLanguage() != null && !lib.get(i).getLanguage().isEmpty()) {
                        obj.add("language" , lib.get(i).getLanguage());
                    }  else {
                        obj.addNull("language");
                    }

                    if (lib.get(i).getRating() != null && !lib.get(i).getRating().isEmpty()) {
                        obj.add("rating" , lib.get(i).getRating());
                    }  else {
                        obj.addNull("rating");
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
                    } else {
                        obj.addNull("tags");
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