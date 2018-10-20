package uk.co.rowney.pokemonteamcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.co.rowney.pokemonteamcreator.dao.PokemonDao;
import uk.co.rowney.pokemonteamcreator.model.Pokemon;

import java.sql.SQLException;
import java.util.List;

@Controller
public class PokemonPageController {

    @Autowired
    private PokemonDao pokemonDao;

    @RequestMapping(path="/index/pokemon/name={pokemonName}")
    public String getPokemon(@PathVariable("pokemonName") String pokemonName, Model model) throws SQLException {
        model.addAttribute("pokemon", pokemonDao.getPokemonFromName(pokemonName).toHtml());
        return "pokemon";
    }

    @RequestMapping(path="/index/pokemon/names={pokemonName}")
    public String getPokemon(@PathVariable("pokemonName") String[] pokemonNames, Model model) throws SQLException {
        List<Pokemon> pokemonList = pokemonDao.getPokemonFromList(pokemonNames);
        StringBuilder stringBuilder = new StringBuilder();
        for (Pokemon pokemon: pokemonList) {
            stringBuilder.append(pokemon.toHtml());
            stringBuilder.append("<br>");
        }
        model.addAttribute("pokemon", stringBuilder.toString());
        return "pokemon";
    }
}
