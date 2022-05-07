import unittest
from unittest.mock import Mock
from robot_arm_abstraction_layer.src.message_handlers import move_shoulder


class TestsRotationHandler(unittest.TestCase):
    def test_can_handle_clockwise(self):
        controller = Mock()
        handler = move_shoulder.MoveShoulder(controller)
        message = 'SHOULDER:UP'
        self.assertTrue(handler.can_handle(message))
        message = 'shoulder:up'
        self.assertTrue(handler.can_handle(message))

    def test_can_handle_anti_clockwise(self):
        controller = Mock()
        handler = move_shoulder.MoveShoulder(controller)
        message = 'SHOULDER:DOWN'
        self.assertTrue(handler.can_handle(message))
        message = 'shoulder:down'
        self.assertTrue(handler.can_handle(message))

    def test_can_not_handle_random(self):
        controller = Mock()
        handler = move_shoulder.MoveShoulder(controller)
        message = 'WIBBLE'
        self.assertFalse(handler.can_handle(message))
        message = 'floob'
        self.assertFalse(handler.can_handle(message))


if __name__ == '__main__':
    unittest.main()
