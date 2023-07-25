package library.db;

import library.entity.Author;
import library.util.AppUtil;

public class AuthorStorage {
    private static AuthorStorage instance;
    private Author[] authors = new Author[10];
    private int size = 0;

    private AuthorStorage() {
    }

    public static AuthorStorage getInstance() {
        if (instance == null) {
            instance = new AuthorStorage();
        }
        return instance;
    }

    public void create(Author author) {
        if (size == authors.length) {
            Author[] updatedArray = new Author[size * 2];
            System.arraycopy(authors, 0, updatedArray, 0, size);
            authors = updatedArray;
        }
        String id = AppUtil.getUUID();
        author.setId(id);
        authors[size] = author;
        size++;
    }

    public void update(Author author) {
        for (int i = 0; i < size; i++) {
            if (authors[i].getId().equals(author.getId())) {
                authors[i] = author;
                break;
            }
        }
    }

    public void delete(String id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (authors[i].getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                authors[i] = authors[i + 1];
            }
            authors[size - 1] = null;
            size--;
        }
    }

    public Author findOne(String id) {
        for (int i = 0; i < size; i++) {
            if (authors[i].getId().equals(id)) {
                return authors[i];
            }
        }
        return null;
    }

    public Author[] findAll() {
        Author[] allAuthors = new Author[size];
        System.arraycopy(authors, 0, allAuthors, 0, size);
        return allAuthors;
    }
}
