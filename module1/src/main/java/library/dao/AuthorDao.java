package library.dao;

import library.db.AuthorStorage;
import library.entity.Author;

public class AuthorDao {
    private final AuthorStorage authorStorage = AuthorStorage.getInstance();

    public void create(Author author) {
        authorStorage.create(author);
    }

    public void update(Author author) {
        authorStorage.update(author);
    }

    public void delete(String id) {
        authorStorage.delete(id);
    }

    public Author findOne(String id) {
        return authorStorage.findOne(id);
    }

    public Author[] findAll() {
        return authorStorage.findAll();
    }
}