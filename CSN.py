TRY_HANDSHAKE = bytes([0])


def bools_to_byte(bools: list[bool]) -> bytes:
    """Convert a list of booleans into the byte they represent."""
    return bytes([sum(map(lambda x: x[1] << x[0], enumerate(bools)))])


def byte_to_bools(byte: bytes, length) -> list[bool]:
    """Convert a bytes object into a list of booleans representing hot bits."""
    return [bool(int.from_bytes(byte, 'big') >> digit & 1) for digit in range(length)]


# Example, w and d are pressed
# keys_pressed = [True, False, False, True]
# keybyte = bools_to_byte(keys_pressed)
# print(f'{keys_pressed} is now a single byte (8 bits). This bit can be represented as a:'
#       f'\ncharacter: {keybyte}'
#       f'\nbase 10 int: {int.from_bytes(keybyte, "big")}'
#       f'\nthe binary: {bin(int.from_bytes(keybyte, "big"))}')
# keys = byte_to_bools(keybyte, 4)
# print(f'And we can efficiently reverse the process: {keys}')

def clientside():
    pass
