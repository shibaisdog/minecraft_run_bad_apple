import pygame
pygame.init()
pygame.mixer.init()
sound = "./source/video.wav"
so = pygame.mixer.Sound(sound)
so.set_volume(0.5)
so.play()
while pygame.mixer.get_busy():
    pygame.time.Clock().tick(10)