import math

import pygame


class Player:
    def __init__(self):
        # Load and scale the asset for the players ship
        self.img = pygame.image.load('resources/images/ships/destroyer.png')
        self.img = pygame.transform.smoothscale_by(self.img, 0.35)

        # Position variables
        self.velocity = 0
        self.position = [0, 0]
        self.heading = 0

    def update(self, inputs):
        if inputs[0]:
            self.velocity += 1
        if inputs[1]:
            self.heading -= 1
        if inputs[2]:
            self.position[1] += 1
        if inputs[3]:
            self.heading += 1

        if self.velocity != 0:
            self.position = (
                int(math.sin(90-self.heading) / self.velocity),
                int(math.cos(90-self.heading) / self.velocity)
            )

    def draw(self, screen, pos, heading):
        tranformed = pygame.transform.rotate(self.img, heading*-1)
        screen.blit(tranformed, pos)
