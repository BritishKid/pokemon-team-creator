package uk.co.rowney.pokemonteamcreator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.lang.String.format;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    private int id;
    private String searchName;
    private String name;
    private String ability;
    private String heldItem;
    private List<EffortValue> effortValues;
    private String nature;
    private List<String> moveList;


    @Override
    public String toString(){
        return format("%s @ %s\nAbility: %s\nEVs: %s \n%s Nature\n- %s\n- %s\n- %s\n- %s",
                this.getName(), this.getHeldItem(), this.getAbility(),
                getEvsString(), this.getNature(), this.getMoveList().get(0),
                this.getMoveList().get(1), this.getMoveList().get(2), this.getMoveList().get(3));
    }

    public String toHtml(){
        return format("%s @ %s<BR>Ability: %s<BR>EVs: %s <BR>%s Nature<BR>- %s<BR>- %s<BR>- %s<BR>- %s",
                this.getName(), this.getHeldItem(), this.getAbility(),
                getEvsString(), this.getNature(), this.getMoveList().get(0),
                this.getMoveList().get(1), this.getMoveList().get(2), this.getMoveList().get(3));
    }

    private String getEvsString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;

        for (EffortValue effortValue: effortValues) {
            stringBuilder.append(format("%s %s ", effortValue.getValue(), effortValue.getName()));
            if(i < 5){
                stringBuilder.append("/ ");
            }
            i++;
        }

        return stringBuilder.toString();
    }

}
