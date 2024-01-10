import pickle
import socket
import logging
import pygame
import CSN
from Game import Player

logging.basicConfig(level=logging.INFO)
UDP_IP = "127.0.0.1"
UDP_PORT = 31415

track_keys = [pygame.K_w, pygame.K_a, pygame.K_s, pygame.K_d, ]


class GameClient:
    def __init__(self, udp_ip: str, udp_port: int):
        logging.info('Attempting to create and launch game client...')

        logging.info('initializing pygame...')
        pygame.init()
        self.screen = pygame.display.set_mode((1280, 720))
        self.clock = pygame.time.Clock()
        self.running = True
        self.dt = 0
        self.img = pygame.image.load('resources/images/ships/destroyer.png')

        logging.info('PyGame initialized! initializing network sockets...')
        self.udp_ip = udp_ip
        self.udp_port = udp_port
        # These arguments just specify that the socket uses UDP on the internet
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

        logging.info('Network sockets initialized! Starting game...')
        self.game_loop()

    def game_loop(self):
        while self.running:
            # poll for events
            # pygame.QUIT event means the user clicked X to close your window
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.running = False

            # Clear the screen
            self.screen.fill('white')

            # This will encode the keys being pressed
            # (that are also in track_keys) and send them to the server
            keys = pygame.key.get_pressed()
            self.sock.sendto(CSN.bools_to_byte([keys[key] for key in track_keys]), (self.udp_ip, self.udp_port))

            data, _ = self.sock.recvfrom(1024)
            gs = pickle.loads(data)
            logging.info(gs)
            for player in gs:
                x = int(player[0])
                y = int(player[1])
                r = int(player[2])
                Player().draw(self.screen, (x, y, r))

            # flip() the display to put your work on screen
            pygame.display.flip()

            # limits FPS to 60
            # dt is delta time in seconds since last frame, used for framerate-
            # independent physics.
            dt = self.clock.tick(60) / 100
        pygame.quit()


GameClient(UDP_IP, UDP_PORT)
