public interface ProjetRepository {
    void create(Projet projet);
    Projet read(Long id);
    List<Projet> readAll();
    void update(Projet projet);
    void delete(Long id);
}