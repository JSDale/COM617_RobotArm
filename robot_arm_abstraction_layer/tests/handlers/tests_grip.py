import unittest
from unittest.mock import Mock
from message_handlers import move_grips


class TestsRotationHandler(unittest.TestCase):
    def test_can_handle_clockwise(self):
        controller = Mock()
        handler = move_grips.MoveGrips(controller)
        message = 'GRIPS:OPEN'
        self.assertTrue(handler.can_handle(message))
        message = 'grips:open'
        self.assertTrue(handler.can_handle(message))

    def test_can_handle_anti_clockwise(self):
        controller = Mock()
        handler = move_grips.MoveGrips(controller)
        message = 'GRIPS:CLOSE'
        self.assertTrue(handler.can_handle(message))
        message = 'grips:close'
        self.assertTrue(handler.can_handle(message))


if __name__ == '__main__':
    unittest.main()
