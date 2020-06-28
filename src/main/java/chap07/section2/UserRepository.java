package chap07.section2;

public interface UserRepository {
    void save(User user);
    User findById(String id);
}
