# CooperativeSpace TODO

These are featuers that need to be implemented and the (hopefully) precise steps that need to be taken for them to be implemented

### Todo

- [ ] Solve the problem of the player getting lost off screen.
  - [ ] 1. In the Sprite class, establish some kind of bounding box method.
  - [ ] 2. In the Sprite class, implement an offScreen() method that returns true if the sprite is off screen.
  - [ ] 3. In the PlayerSprite class, implement a drawOffScreen(GraphicsContext gc) method that draws a symbol for the player when off screen
  - [ ] 4. In the PlayerSprite.draw() class, implement a check for offScreen and then if so call the drawOffScreen method

### In Progress:

 None
 
### Completed:
