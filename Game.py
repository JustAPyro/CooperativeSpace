import math

import pygame


class Player:
    def __init__(self):
        # Load and scale the asset for the players ship
        self.img = pygame.image.load('resources/images/ships/destroyer.png')
        self.img = pygame.transform.smoothscale_by(self.img, 0.2)

        # Position variables
        self.position = [0, 0]
        self.heading = 0

        # Acceleration and x/y velocity
        self.acceleration = 0.25
        self.velocity = [0, 0]



    def update(self, inputs):
        if inputs[0]:  # W
            self.velocity[0] += math.sin(math.radians(self.heading)) * self.acceleration
            self.velocity[1] += math.cos(math.radians(self.heading)) * -self.acceleration
        if inputs[1]:  # A
            self.heading -= 1
        if inputs[2]:  # S
            self.velocity[0] -= math.sin(math.radians(self.heading)) * self.acceleration
            self.velocity[1] -= math.cos(math.radians(self.heading)) * -self.acceleration
        if inputs[3]:  # D
            self.heading += 1

        self.position[0] += self.velocity[0]
        self.position[1] += self.velocity[1]

    def draw(self, screen, pos, heading):
        tranformed = pygame.transform.rotate(self.img, (heading * -1) + 90)
        screen.blit(tranformed, pos)
