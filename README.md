# Java-Game-Marvel

## Decription
 ### Intro
Marvel: Ultimate War is a 2 player battle game. Each player picks 3 champions to form his team
and fight the other player’s team. The players take turns to fight the other player’s champions.
The turns will keep going back and forth until a player is able to defeat all of the other player’s
champions which will make him the winner of the battle.
During the battle, each player will use his champions to attack the opponent champions either
by using normal attacks or using special attacks/abilities. The battle takes place on a 5x5 grid.
Each cell in the grid can either be empty, or contain a champion or obstacle/cover. At the
beginning of the battle, each team will stand at one of the sides/edges of the grid as a starting
position.

 ### Champions
Champions are the fighters that each player will form his team from. Each champion will have
a certain type which influences how the champion deals damage to other types as well as how
much damage it will receive from them. The available types are:-
• Heroes: they deal extra damage when attacking villains.
• Villains: they deal extra damage when attacking heroes.
• Anti-Heroes: when being attacked or attacking a hero or villain, the antihero will always
act as the opposite type. If attacking an antihero, damage is calculated normally.

 ### Abilities
These are special attacks that a champion can use. They are categorized under the following
categories:-
1. Damaging abilities: Abilities that deal damage to the opponent champion(s) or covers.
2. Healing abilities: Abilities that restore health points to friendly champion(s).
3. Effect abilities: Abilities that can empower or weaken their targets by applying different
effects. These effects can last for multiple turns and will affect how the affected champion
interacts or reacts to abilities or attacks.
Example of some effects: stun, weaken, embrace, shield, silence, disarm.
Abilities have different targets and ranges. Some abilities are single target abilities which
affect only a single champion (or a cover in some cases) per use. Or can affect any champion
standing in a certain area (area of effect). These areas can be directional (Horizontal or
Vertical), or Circuilar (affect an area surrounding a central point). Finally, some abilities
can affect all friendly or opposing champions.
Each ability requires a certain amount of action points to be present in the champion
casting them as well as some mana. Also, each ability has a specific range of cells that
the target needs to be present in it in order for the ability to affect it.
Leader Abilities
At the beginning of the battle, each player promotes one of his champions to be the leader of
his team. The leader will then receive a special ability based on his type that can be used only
once per battle
## To run game
  1. import project to eclipse IDE
  2. go to src folder
  3. go to engine package
  4. run controller.java file
![alt text](http://url/to/img.png)
