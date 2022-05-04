import unittest
from unittest.mock import Mock
from message_handlers import move_elbow


class TestsRotationHandler(unittest.TestCase):
    def test_can_handle_clockwise(self):
        controller = Mock()
        handler = move_elbow.MoveElbow(controller)
        message = 'ELBOW:UP'
        self.assertTrue(handler.can_handle(message))
        message = 'elbow:up'
        self.assertTrue(handler.can_handle(message))

    def test_can_handle_anti_clockwise(self):
        controller = Mock()
        handler = move_elbow.MoveElbow(controller)
        message = 'ELBOW:DOWN'
        self.assertTrue(handler.can_handle(message))
        message = 'elbow:down'
        self.assertTrue(handler.can_handle(message))

    def test_can_not_handle_random(self):
        controller = Mock()
        handler = move_elbow.MoveElbow(controller)
        message = 'WIBBLE'
        self.assertFalse(handler.can_handle(message))
        message = 'floob'
        self.assertFalse(handler.can_handle(message))


if __name__ == '__main__':
    unittest.main()
