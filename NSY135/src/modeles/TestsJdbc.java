package modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestsJdbc {
	private static final Integer port = 3306;
	/**
	 * Pour communiquer avec MySQL
	 */
	private Connection connexion;

	/**
	 * Constructeur sans connexion
	 */
	public TestsJdbc() throws ClassNotFoundException {
		/* On commence par "charger" le pilote MySQL */
		Class.forName("com.mysql.jdbc.Driver");
	}

	public void connect(String server, String bd, String u, String p)
			throws SQLException {
		String url = "jdbc:mysql://" + server + ":" + port + "/" + bd;
		connexion = DriverManager.getConnection(url, u, p);
	}

	public ResultSet chercheFilmsA() throws SQLException {
		Statement statement = connexion.createStatement();
		return statement.executeQuery("SELECT * FROM Film");
	}

	public List<Map<String, String>> chercheFilmsB() throws SQLException {
		List<Map<String, String>> resultat = new ArrayList<Map<String, String>>();
		Statement statement = connexion.createStatement();
		ResultSet films = statement.executeQuery("SELECT * FROM Film");
		while (films.next()) {
			Map<String, String> film = new HashMap<String, String>();
			film.put("titre", films.getString("titre"));
			film.put("genre", films.getString("genre"));
			// film.put("annee", (String) films.getInt("annee"));
			resultat.add(film);
		}
		// Et on renvoie
		return resultat;
	}

	public List<Film> chercheFilmsC() throws SQLException {
		List<Film> resultat = new ArrayList<Film>();
		Statement statement = connexion.createStatement();
		ResultSet films = statement.executeQuery("SELECT * FROM Film");
		while (films.next()) {
			Film film = new Film();
			film.setTitre(films.getString("titre"));
			film.setAnnee(films.getInt("annee"));
			film.setId_realisateur(films.getInt("id_realisateur"));
			resultat.add(film);
		}
		// Et on renvoie
		return resultat;
	}

}