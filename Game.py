import math
from enum import Enum
from CSN import clientside

import pygame

X = 0
Y = 1
ROTATION = 2


class Player:
    # Indexes for the position
    def __init__(self):
        # Load and scale the asset for the players ship
        self.img = pygame.image.load('resources/images/ships/destroyer.png')
        self.img = pygame.transform.smoothscale_by(self.img, 0.2)
        self.width = self.img.get_width()
        self.height = self.img.get_height()

        # Position variables (X, Y, and rotation)
        self.position = [100, 100, 0]

        # Acceleration and X/Y velocity
        self.acceleration = 0.25
        self.velocity = [0, 0]

    def update(self, inputs):
        if inputs[0]:  # W
            self.velocity[0] += math.sin(math.radians(self.position[ROTATION])) * self.acceleration
            self.velocity[1] += math.cos(math.radians(self.position[ROTATION])) * -self.acceleration
        if inputs[1]:  # A
            self.position[ROTATION] -= 1.5
        if inputs[2]:  # S
            self.velocity[0] -= math.sin(math.radians(self.position[ROTATION])) * self.acceleration
            self.velocity[1] -= math.cos(math.radians(self.position[ROTATION])) * -self.acceleration
        if inputs[3]:  # D
            self.position[ROTATION] += 1.5

        self.position[0] += self.velocity[0]
        self.position[1] += self.velocity[1]

    def _rot_center(self, image, rect, angle):
        """rotate an image while keeping its center"""
        rot_image = pygame.transform.rotate(image, angle)
        rot_rect = rot_image.get_rect(center=rect.center)
        return rot_image, rot_rect

    # clientside
    def draw(self, screen, position):
        transformed = pygame.transform.rotate(self.img, -position[ROTATION]+90)
        transformed_rect = transformed.get_rect(center=self.img.get_rect(topleft=(position[X], position[Y])).center)
        screen.blit(transformed, transformed_rect)

