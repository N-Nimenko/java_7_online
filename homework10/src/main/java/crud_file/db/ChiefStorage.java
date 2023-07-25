package crud_file.db;

import com.google.gson.Gson;
import crud_file.entity.Chief;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ChiefStorage {
    private static ChiefStorage instance;
    private final List<Chief> chiefs = new ArrayList<>();

    private ChiefStorage() {
    }

    public static ChiefStorage getInstance() {
        if (instance == null) {
            instance = new ChiefStorage();
        }
        return instance;
    }

    public void create(Chief chief) {
        readChiefsFromJson();
        chief.setId(generateId());
        chiefs.add(chief);
        writeChiefsToJson();
    }

    public void update(Chief chief) {
        readChiefsFromJson();
        int index = findChiefIndex(chief.getId());
        if (index != -1) {
            chiefs.set(index, chief);
            writeChiefsToJson();
        } else {
            System.out.println("Chief not found");
        }
    }

    public void delete(String id) {
        readChiefsFromJson();
        int index = findChiefIndex(id);
        if (index != -1) {
            chiefs.remove(index);
            writeChiefsToJson();
        } else {
            System.out.println("Chief not found");
        }
    }

    public Chief findOne(String id) {
        readChiefsFromJson();
        for (Chief chief : chiefs) {
            if (chief.getId().equals(id)) {
                return chief;
            }
        }
        return null;
    }

    public Collection<Chief> findAll() {
        readChiefsFromJson();
        return chiefs;
    }

    public void readChiefsFromJson() {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader("json/chiefs.json")) {
            Chief[] currentChiefs = gson.fromJson(fileReader, Chief[].class);
            if (currentChiefs != null) {
                chiefs.clear();
                chiefs.addAll(Arrays.asList(currentChiefs));
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void writeChiefsToJson() {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter("json/chiefs.json")) {
            String json = gson.toJson(chiefs);
            fileWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (chiefs.stream().anyMatch(chief -> chief.getId().equals(id))) {
            return generateId();
        }
        return id;
    }

    private int findChiefIndex(String id) {
        for (int i = 0; i < chiefs.size(); i++) {
            if (chiefs.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static String getChiefUUID() {
        return UUID.randomUUID().toString();
    }
}
