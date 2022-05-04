import unittest
from unittest.mock import Mock

from maplin_arm_controller import MaplinArmController
from message_handlers import move_wrist, set_sensitivity


class TestsSensitivity(unittest.TestCase):

    def tests_can_set(self):
        controller = MaplinArmController('0.1')
        wrist = move_wrist.MoveWrist(controller)
        self.assertTrue(wrist.controller.sensitivity == '0.1')
        print(f"value: {wrist.controller.sensitivity == '0.1'}")

        controller.set_sensitivity('5')
        self.assertTrue(wrist.controller.sensitivity == '5')
