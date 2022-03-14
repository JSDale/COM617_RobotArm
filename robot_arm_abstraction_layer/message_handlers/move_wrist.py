from maplin_arm_controller import MaplinArmController


class MoveWrist:

    __up_message = 'WRIST:UP'

    __down_message = 'WRIST:DOWN'

    def can_handle(self, message):
        if message.upper() == self.__up_message or message.upper() == self.__down_message:
            return True

        return False

    def handle(self, message):
        controller = MaplinArmController()
        if message.upper() == self.__up_message:
            controller.move_wrist_up()
        elif message.upper() == self.__down_message:
            controller.move_wrist_down()
