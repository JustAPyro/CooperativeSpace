package cooperativespace.sprites.commands.movement;

import cooperativespace.sprites.commands.Command;
import cooperativespace.sprites.components.ActionComponent;

public class AbilityCommand extends Command {

    private final int abilityNumber;

    public AbilityCommand(int abilityNumber) {
        this.abilityNumber = abilityNumber;
    }

    @Override
    public void execute(ActionComponent actionComponent) {
        actionComponent.ability(abilityNumber);
    }

}
