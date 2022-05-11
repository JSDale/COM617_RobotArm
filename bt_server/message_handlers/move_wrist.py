

class MoveWrist:

    __up_message = 'WRIST:UP'

    __down_message = 'WRIST:DOWN'

    def __init__(self, controller):
        self.controller = controller

    def can_handle(self, message):
        if message.upper() == self.__up_message or message.upper() == self.__down_message:
            return True

        return False

    def handle(self, message):
        if message.upper() == self.__up_message:
            self.controller.move_wrist_up()
        elif message.upper() == self.__down_message:
            self.controller.move_wrist_down()
