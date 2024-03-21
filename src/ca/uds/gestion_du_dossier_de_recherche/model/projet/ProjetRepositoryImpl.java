public class ProjetRepositoryImpl implements ProjetRepository {

    /*
    Faut le récuperer l'insance à partir de la classe de responsabl, l'implementation devrait
    faite en pattern Singleton @AMG ce qui 'est en bas est faut
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/BD", "utilisateur", "motdepasse");
    }

    @Override
    public void create(Projet projet) {
        String sql = "INSERT INTO Projet (titre, description, dateDebut, dateFin, equipe, financement) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projet.getTitre());
            stmt.setString(2, projet.getDescription());
            stmt.setDate(3, Date.valueOf(projet.getDateDebut()));
            stmt.setDate(4, Date.valueOf(projet.getDateFin()));
            stmt.setString(5, projet.getEquipe());
            stmt.setString(6, projet.getFinancement);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Projet read(Long id) {
        Projet projet = null;
        String query = "SELECT * FROM projet WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                projet = new Projet();
                projet.setId(rs.getLong("id"));
                projet.setTitre(rs.getString("titre"));
                projet.setDescription(rs.getString("dateDebut"));
                projet.setDescription(rs.getString("dateFin"));
                projet.setEquipe(rs.getString("equipe"));
                projet.setFinancement(rs.getString("financement"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projet;
    }

    @Override
    public List<Projet> readAll() {
        List<Projet> projets = new ArrayList<>();
        String query = "SELECT * FROM projet";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Projet projet = new Projet();
                projet.setId(rs.getLong("id"));
                projet.setTitre(rs.getString("titre"));
                projet.setDescription(rs.getString("dateDebut"));
                projet.setDescription(rs.getString("dateFin"));
                projet.setEquipe(rs.getString("equipe"));
                projet.setFinancement(rs.getString("financement"));
                projets.add(projet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projets;
    }

    @Override
    public void update(Projet projet) {
        String query = "UPDATE projet SET titre = ?, description = ?, dateDebut = ?, dateFin = ?, equipe = ?, financement = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, projet.Titre));
            ps.setString(2, projet.getDescription());
            ps.setString(3, projet.getDateDebut());
            ps.setString(4, projet.getDateFin());
            ps.setString(5, projet.getEquipe());
            ps.setString(6, projet.Financement());
            ps.setLong(7, projet.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM projet WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


}