

class RotateBase:

    __up_message = 'ROTATE:CLOCKWISE'
    __down_message = 'ROTATE:CTRCLOCKWISE'

    def __init__(self, controller):
        self.controller = controller

    def can_handle(self, message):
        if message.upper() == self.__up_message or message.upper() == self.__down_message:
            return True

        return False

    def handle(self, message):
        if message.upper() == self.__up_message:
            self.controller.rotate_arm_clockwise()
        elif message.upper() == self.__down_message:
            self.controller.rotate_arm_counter_clockwise()
            