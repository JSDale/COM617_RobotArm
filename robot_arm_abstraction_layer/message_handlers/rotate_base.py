from maplin_arm_controller import MaplinArmController


class RotateBase:

    __up_message = 'ROTATE:CLOCKWISE'
    __down_message = 'ROTATE:CTRCLOCKWISE'

    def can_handle(self, message):
        if message.upper() == self.__up_message or message.upper() == self.__down_message:
            return True

        return False

    def handle(self, message):
        controller = MaplinArmController()
        if message.upper() == self.__up_message:
            controller.rotate_arm_clockwise()
        elif message.upper() == self.__down_message:
            controller.rotate_arm_counter_clockwise()
            