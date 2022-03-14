from maplin_arm_controller import MaplinArmController


class MoveShoulder:

    __up_message = 'SHOULDER:UP'
    __down_message = 'SHOULDER:DOWN'

    def can_handle(self, message):
        if message.upper() == self.__up_message or message.upper() == self.__down_message:
            return True

        return False

    def handle(self, message):
        controller = MaplinArmController()
        if message.upper() == self.__up_message:
            controller.move_shoulder_up()
        elif message.upper() == self.__down_message:
            controller.move_shoulder_down()
