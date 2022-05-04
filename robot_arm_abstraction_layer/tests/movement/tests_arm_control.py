import unittest

from maplin_arm_controller import MaplinArmController


class TestsArmControl(unittest.TestCase):

    def test_can_rotate_clockwise(self):
        control = MaplinArmController(50)
        control.rotate_arm_clockwise()

    def test_can_rotate_counter_clockwise(self):
        control = MaplinArmController(50)
        control.rotate_arm_counter_clockwise()

    def test_can_move_shoulder_up(self):
        control = MaplinArmController(50)
        control.move_shoulder_up()

    def test_can_move_shoulder_down(self):
        control = MaplinArmController(50)
        control.move_shoulder_down()

    def test_can_move_elbow_up(self):
        control = MaplinArmController(50)
        control.move_elbow_up()

    def test_can_move_elbow_down(self):
        control = MaplinArmController(50)
        control.move_elbow_down()

    def test_can_move_wrist_up(self):
        control = MaplinArmController(50)
        control.move_wrist_up()

    def test_can_move_wrist_down(self):
        control = MaplinArmController(50)
        control.move_wrist_down()

    def test_can_flash_light(self):
        control = MaplinArmController(50)
        control.blink_led()
