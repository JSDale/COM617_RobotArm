import unittest
from unittest.mock import Mock
from robot_arm_abstraction_layer.src.message_handlers import move_wrist


class TestsRotationHandler(unittest.TestCase):
    def test_can_handle_clockwise(self):
        controller = Mock()
        handler = move_wrist.MoveWrist(controller)
        message = 'WRIST:UP'
        self.assertTrue(handler.can_handle(message))
        message = 'wrist:up'
        self.assertTrue(handler.can_handle(message))

    def test_can_handle_anti_clockwise(self):
        controller = Mock()
        handler = move_wrist.MoveWrist(controller)
        message = 'WRIST:DOWN'
        self.assertTrue(handler.can_handle(message))
        message = 'wrist:down'
        self.assertTrue(handler.can_handle(message))

    def test_can_not_handle_random(self):
        controller = Mock()
        handler = move_wrist.MoveWrist(controller)
        message = 'WIBBLE'
        self.assertFalse(handler.can_handle(message))
        message = 'floob'
        self.assertFalse(handler.can_handle(message))


if __name__ == '__main__':
    unittest.main()
