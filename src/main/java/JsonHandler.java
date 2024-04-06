import java.io.*;

import javax.json.*;
//import java.nio.file.Paths;
import java.util.ArrayList;
//import java.util.Scanner;

public class JsonHandler {
    public JsonHandler(){}

    public void readFile(Library lib , String filePath) {
        Book input;
        ArrayList<String> tags = new ArrayList<>();
        try ( Reader rd = new FileReader(filePath)) {
            input = new Book();
            JsonReader jrd = Json.createReader(rd);
            JsonObject bookObject = jrd.readObject();
    //title+, subtitle+,<String> authors+,<String> translators+, isbn+,publisher+, date+, edition+, cover+, language+, rating+,<String> tags+

            if (bookObject.containsKey("title")) {
                String title = bookObject.getString("title");
                input.setTitle(title);
                tags.add(title);
            }

            if (bookObject.containsKey("subtitle")) {
                String subtitle = bookObject.getString("subtitle");
                input.setSubtitle(subtitle);
                tags.add(subtitle);
            }

            if (bookObject.containsKey("authors")) {
                JsonValue aut = bookObject.getValue("authors"); // to check whether the input is a string or an array
                ArrayList<String> authors = new ArrayList<>();
                if (aut.getValueType() == JsonValue.ValueType.STRING) {   // for string inputs
                    String author = bookObject.getString("authors");
                    authors.add(author);
                    tags.add(author);
                } else {                                                // for array inputs
                    JsonArray temp = bookObject.getJsonArray("authors");
                    for (JsonValue authorJson : temp) {
                        String author = authorJson.toString().substring( 1, authorJson.toString().length() - 1 );
                        authors.add(author);
                        tags.add(author);
                    }
                }
                input.setAuthors(authors);
            }

            if (bookObject.containsKey("translators")) {
                JsonValue tran = bookObject.getValue("translators"); // to check whether the input is a string or an array
                ArrayList<String> translators = new ArrayList<>();
                if (tran.getValueType() == JsonValue.ValueType.STRING) { // for string inputs
                    String translator = bookObject.getString("translators");
                    translators.add(translator);
                    tags.add(translator);
                } else {                                                // for array inputs
                    JsonArray temp = bookObject.getJsonArray("translators");
                    for (JsonValue translatorJson : temp) {
                        String translator = translatorJson.toString().substring( 1, translatorJson.toString().length() - 1 );
                        translators.add(translator);
                        tags.add(translator);
                    }
                }
                input.setTranslators(translators);
            }

            if (bookObject.containsKey("isbn")) {
                String isbn = bookObject.getString("isbn");
                input.setIsbn(isbn);
                tags.add(isbn);
            }

            if (bookObject.containsKey("publisher")) {
                String publisher = bookObject.getString("publisher");
                input.setPublisher(publisher);
                tags.add(publisher);
            }

            if (bookObject.containsKey("date")) {
                String date = bookObject.getString("date");
                input.setDate(date);
                tags.add(date);
            }

            if (bookObject.containsKey("edition")) {
                String edition = bookObject.getString("edition");
                input.setEdition(edition);
                tags.add(edition);
            }

            if (bookObject.containsKey("cover")) {
                String cover = bookObject.getString("cover");
                input.setCover(cover);
                tags.add(cover);
            }

            if (bookObject.containsKey("language")) {
                String language = bookObject.getString("language");
                input.setLanguage(language);
                tags.add(language);
            }

            if (bookObject.containsKey("rating")) {
                String rating = bookObject.getString("rating");
                input.setRating(rating);
                tags.add(rating);
            }

            input.setTags(tags);
            lib.addToLib(input);


        } catch (IOException e) {
            //tbd
        }



    }
}
