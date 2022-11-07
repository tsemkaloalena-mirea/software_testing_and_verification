"""Application with running mouse."""
import os
import pygame
from pygame.color import Color
from pygame.sprite import Group
from runtime_type_checker import check_type, check_types


@check_types
def load_image(name: str, color_key=None):
    """
    Load an image.

    :param name: image name
    :param color_key: -1 if background should be deleted
    :return: image
    """
    fullname = os.path.join('data', name)
    image = pygame.image.load(fullname).convert()
    if color_key is not None:
        if color_key == -1:
            color_key = image.get_at((0, 0))
        check_type(color_key, Color)
        image.set_colorkey(color_key)
    else:
        image = image.convert_alpha()
    return image


class Mouse(pygame.sprite.Sprite):
    """Mouse class."""

    @check_types
    def __init__(self, group: Group, image_size: tuple[int, int], screen_width: int, fps: int):
        """
        Initialize mouse.

        :param group: sprite group
        :param image_size: image size
        :param screen_width: screen width
        :param fps: frame per second
        """
        super().__init__(group)
        self.fps = fps
        self.screen_width = screen_width
        self.image = load_image("mouse.jpg", -1)
        self.image = pygame.transform.scale(self.image, image_size)
        self.rect = self.image.get_rect()
        self.rect.y = 10
        self.speed = 60  # пикселей в секунду
        self.fps = 60
        self.image_size = image_size

    def next_move(self):
        """Move mouse to the left or right and flip if necessary."""
        self.rect.x += self.speed * self.fps / 1000
        if self.rect.x > self.screen_width - self.image_size[0] or self.rect.x == 0:
            self.speed *= (-1)
            self.image = pygame.transform.flip(self.image, True, False)


def main():
    """Create mouse and make it run."""
    pygame.init()
    width, height = 600, 95
    image_size = "130", 80
    size = width, height
    screen = pygame.display.set_mode(size)
    fps = 60
    clock = pygame.time.Clock()
    all_sprites = pygame.sprite.Group()
    mouse = Mouse(all_sprites, image_size, width, fps)
    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
        screen.fill((255, 255, 255))
        mouse.next_move()
        all_sprites.draw(screen)
        clock.tick(fps)
        pygame.display.flip()


if __name__ == "__main__":
    main()
