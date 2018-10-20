package uk.co.rowney.pokemonteamcreator.dao;

import org.springframework.stereotype.Repository;
import uk.co.rowney.pokemonteamcreator.model.EffortValue;
import uk.co.rowney.pokemonteamcreator.model.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Repository
public class PokemonDao {

    private Statement statement;

    public Pokemon getPokemonFromName(String name) throws SQLException {
        String sql = format("SELECT * FROM pokemon WHERE UPPER(SEARCHNAME) LIKE UPPER('%s')", name);
        ResultSet resultSet = statement.executeQuery(sql);

        Pokemon pokemon = new Pokemon();
        while(resultSet.next()){
            pokemon.setId(resultSet.getInt("id"));
            pokemon.setName(resultSet.getString("name"));
            pokemon.setAbility(resultSet.getString("ability"));
            pokemon.setHeldItem(resultSet.getString("heldItem"));
            pokemon.setNature(resultSet.getString("nature"));
            pokemon.setEffortValues(getEffortValuesFromResultSet(resultSet));
            pokemon.setMoveList(getMoveListFromResultSet(resultSet));
        }

        return pokemon;
    }

    public List<Pokemon> getPokemonFromList(String[] pokemonNames) throws SQLException {
        statement = createConnection();
        List<Pokemon> pokemonList = new ArrayList<>();
        for (String pokemonName: pokemonNames) {
            Pokemon pokemon = getPokemonFromName(pokemonName);
            pokemonList.add(pokemon);
        }
        return pokemonList;
    }

    public List<String> getAllPokemon() throws SQLException {
        statement = createConnection();
        List<String> pokemonList = new ArrayList<>();
        String sql = "SELECT * FROM pokemon";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            pokemonList.add(resultSet.getString("searchName"));
        }
        return pokemonList;
    }

    private List<String> getMoveListFromResultSet(ResultSet resultSet) throws SQLException {
        List<String> moveList = new ArrayList<>();
        moveList.add(resultSet.getString("move1"));
        moveList.add(resultSet.getString("move2"));
        moveList.add(resultSet.getString("move3"));
        moveList.add(resultSet.getString("move4"));
        return moveList;


    };


    private List<EffortValue> getEffortValuesFromResultSet(ResultSet resultSet) throws SQLException {
        List<EffortValue> effortValues = new ArrayList<>();
        effortValues.add(new EffortValue("Atk", resultSet.getInt("attack")));
        effortValues.add(new EffortValue("Def", resultSet.getInt("defence")));
        effortValues.add(new EffortValue("SpA", resultSet.getInt("specialAttack")));
        effortValues.add(new EffortValue("SpD", resultSet.getInt("specialDefence")));
        effortValues.add(new EffortValue("HP", resultSet.getInt("hitPoints")));
        effortValues.add(new EffortValue("Spe", resultSet.getInt("speed")));
        return effortValues;
    }

    private Statement createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        return connection.createStatement();
    }
}
