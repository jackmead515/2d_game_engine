Just a simple 2D game engine that I'm working on.

Maybe it will be a weekend project, but who knows!

EDIT 10/09/2019

Looks like it's not a weekend project. I've been working on it quite a bit. Here is a list of things that I want to implement:

- [ ] Convert to using Canvas
```
// With canvas we can do bufferStrategy directly!

BufferStrategy bs = canvas.getBufferStrategy();
if (bs == null) {
  canvas.createBufferStrategy(3);
  return;
}

Graphics2D g2 = (Graphics2D) canvas.getDrawGraphics();

//Draw stuff!!

g2.dispose();
bs.show();
```

- [ ] Wandering AI
- [ ] Fleeing AI
- [ ] Attacking AI
- [ ] Scale objects
- [ ] Cursor move map around
- [ ] Snap camera to boundaries
- [ ] Map input to GUI
- [ ] Map input to player
- [ ] Button GUI
- [ ] Scrollable List  GUI
- [ ] Checkbox GUI
- [ ] Dropdown GUI
- [ ] Handle importing images in folders
- [ ] Use double[] instead of Float objects
- [ ] Make Utilites for geometric contains(), intersects(), and other stuff.
- [ ] Collision Detection using Quadtrees
- [ ] Shadow mapping with ray casting (light sources / enemy sight detection)
- [ ] Enviornment sprites (leafs / clouds)
- [ ] Particle Emitters
- [ ] Enable 2D opengl in AWT
- [ ] Create TimedEvent class
- [ ] Add forces (apply to collision sprites)
