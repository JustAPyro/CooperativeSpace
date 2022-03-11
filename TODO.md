# CooperativeSpace TODO

These are featuers that need to be implemented and the (hopefully) precise steps that need to be taken for them to be implemented

### Todo

- [ ] Solve the problem of the player getting lost off screen.
  - [ ] 1. In the Sprite class, establish some kind of bounding box method.
  - [ ] 2. In the Sprite class, implement an offScreen() method that returns true if the sprite is off screen.
  - [ ] 3. In the PlayerSprite class, implement a drawOffScreen(GraphicsContext gc) method that draws a symbol for the player when off screen
  - [ ] 4. In the PlayerSprite.draw() class, implement a check for offScreen and then if so call the drawOffScreen method

- [ ] Drop players when they haven't contacted the server in a set length of time (Part 1/2 of solving Issue #6)
  - [ ] 1. in the server class make the blocking call (socket.receive(incomingPacket);, line 81) time out after a given period
  - [ ] 2. abstract out the client ID parsing to a "parseClientID(DatagramPacket incomingPacket)" method
  - [ ] 3. then abstract everything that needs to happen on a packet recieved to a "onPacketRecieved(DatagramPacket incoming Packet)" method
  - [ ] 4. Create a hashMap<clientID, long> to store times in, and onPacketRecieved should insert the current time into the array using the clientID
  - [ ] 5. create a validateClients() method that should be called from the loop, check each timestamp in the hashmap and if it's been too long, remove that clientID and drop the connection






### In Progress:

 None
 
### Completed:
