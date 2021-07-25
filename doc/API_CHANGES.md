**Stable:**

GamePlay interface stayed the same

Loader stayed the same however the random game generator feature was added.

**Unstable:**

Entity (Moveable and MovableBound) changed quite a bit. Underestimated amount of data needed from entities. Increased method due to making game rely more on the state on entities that could be set by the entitiy itself, collisions, or the game. Decided to make entity handle some collision actions rather than game.

Obstacle changed since we added collision methods to obstacle rather than handling them in game.

Viewer is still in code however did not need to implement and is unused.

We added a lot of new interfaces to have cleaner code: 
MovableBound, Unmovable were added to help with class interaction.

Collideable changed since it was originally in obstacle package. However, we changed our definition of Entity and Obstacle so it made more sense. This meant there were now Entity, Entity collisions and Entity, Obstacle collisions. Rather than just Entity, Obstacle collisions. This meant that both Entity and Obstacle should implement collision.
