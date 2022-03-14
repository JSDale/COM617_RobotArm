from maplin_arm_controller import MaplinArmController


class MoveGrips:

    __open_message = 'GRIPS:UP'
    __close_message = 'GRIPS:DOWN'

    def can_handle(self, message):
        if message.upper() == self.__open_message or message.upper() == self.__close_message:
            return True

        return False

    def handle(self, message):
        controller = MaplinArmController()
        if message.upper() == self.__open_message:
            controller.open_grips()
        elif message.upper() == self.__close_message:
            controller.close_grips()
