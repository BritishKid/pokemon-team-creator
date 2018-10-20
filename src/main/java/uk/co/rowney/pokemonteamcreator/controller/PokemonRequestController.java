package uk.co.rowney.pokemonteamcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.rowney.pokemonteamcreator.dao.PokemonDao;
import uk.co.rowney.pokemonteamcreator.model.Pokemon;

import java.sql.SQLException;
import java.util.List;

@RestController
public class PokemonRequestController {

    @Autowired
    private PokemonDao pokemonDao;

    @RequestMapping(path="/pokemon/names={pokemonName}")
    public String getPokemon(@PathVariable("pokemonName") String[] pokemonNames, Model model) throws SQLException {
        List<Pokemon> pokemonList = pokemonDao.getPokemonFromList(pokemonNames);
        StringBuilder stringBuilder = listToHtml(pokemonList);
      return stringBuilder.toString();
    }

    private StringBuilder listToHtml(List<Pokemon> pokemonList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Pokemon pokemon: pokemonList) {
            stringBuilder.append(pokemon.toHtml());
            stringBuilder.append("<BR><BR>");
        }
        return stringBuilder;
    }

    @RequestMapping(path="/pokemon/all")
    public List<String> getAllPokemon() throws SQLException {
        return pokemonDao.getAllPokemon();
    }

    @RequestMapping(path="/pokemon/random")
    public String getRandomTeamPokemon() throws SQLException {
        List<Pokemon> pokemonList = pokemonDao.getRandomPokemon();
        StringBuilder stringBuilder = listToHtml(pokemonList);
        return stringBuilder.toString();
    }
}
