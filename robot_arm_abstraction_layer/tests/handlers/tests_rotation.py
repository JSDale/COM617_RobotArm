import unittest
from unittest.mock import Mock
from robot_arm_abstraction_layer.src.message_handlers import rotate_base


class TestsRotationHandler(unittest.TestCase):
    def test_can_handle_clockwise(self):
        controller = Mock()
        rotate_handler = rotate_base.RotateBase(controller)
        message = 'ROTATE:CLOCKWISE'
        self.assertTrue(rotate_handler.can_handle(message))
        message = 'rotate:clockwise'
        self.assertTrue(rotate_handler.can_handle(message))

    def test_can_handle_anti_clockwise(self):
        controller = Mock()
        rotate_handler = rotate_base.RotateBase(controller)
        message = 'ROTATE:CTRCLOCKWISE'
        self.assertTrue(rotate_handler.can_handle(message))
        message = 'rotate:ctrclockwise'
        self.assertTrue(rotate_handler.can_handle(message))

    def test_can_not_handle_random(self):
        controller = Mock()
        rotate_handler = rotate_base.RotateBase(controller)
        message = 'WIBBLE'
        self.assertFalse(rotate_handler.can_handle(message))
        message = 'floob'
        self.assertFalse(rotate_handler.can_handle(message))


if __name__ == '__main__':
    unittest.main()
