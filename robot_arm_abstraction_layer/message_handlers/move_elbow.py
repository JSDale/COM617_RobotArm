from maplin_arm_controller import MaplinArmController


class MoveElbow:

    __up_message = 'ELBOW:UP'
    __down_message = 'ELBOW:DOWN'

    def __init__(self, controller):
        self.controller = controller

    def can_handle(self, message):
        if message.upper() == self.__up_message or message.upper() == self.__down_message:
            return True

        return False

    def handle(self, message):
        if message.upper() == self.__up_message:
            self.controller.move_elbow_up()
        elif message.upper() == self.__down_message:
            self.controller.move_elbow_down()
