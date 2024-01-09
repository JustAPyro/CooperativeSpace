import pickle
import socket
import logging
import sys

import CSN
from Game import Player

logging.basicConfig(level=logging.INFO)
UDP_IP = "127.0.0.1"
UDP_PORT = 31415


class ClientManager:
    def __init__(self, addr: tuple[str, int]):
        logging.info(f'Created new client manager for client at: {addr}')
        self.player = Player()

    def update_inputs(self, inputs):
        self.player.update(inputs)


class GameServer:
    LOBBY = bytes([0])

    def __init__(self, udp_ip: str, udp_port: int, connections_allowed: int):
        logging.info('Attempting to create game server...')

        self.udp_ip = udp_ip
        self.udp_port = udp_port
        self.clients = {}

        # Create a socket (Specifically for UDP) and then bind it to the IP/port
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        try:
            sock.bind((UDP_IP, UDP_PORT))
        except OSError:
            logging.error('Cannot start server on this socket because it is being used. Aborting.')
            exit(0)

        # We should be good to listen now
        logging.info('Server started successfully! Listening for connections...')
        pos = [0, 0]
        while True:
            data, addr = sock.recvfrom(1024)  # buffer size is 1024 bytes

            # If this is a new address we create a new client manager
            if addr not in self.clients:
                self.clients[addr] = ClientManager(addr)

            self.clients[addr].update_inputs(
                CSN.byte_to_bools(data, 4))

            pickle_game = pickle.dumps([cm.player.position for cm in self.clients.values()])
            logging.info(f'Pickle: {sys.getsizeof(pickle_game)} bytes')
            state = ''
            for client_addr in self.clients:
                px, py = self.clients[client_addr].player.position
                r = self.clients[client_addr].player.heading
                state += f'|{px},{py},{r}'
            logging.info(f'String: {sys.getsizeof(state)} bytes')
            sock.sendto(state.strip('|').encode('utf-8'), addr)


x = GameServer(UDP_IP, UDP_PORT, 2)
