

class MoveGrips:

    __open_message = 'GRIPS:OPEN'
    __close_message = 'GRIPS:CLOSE'

    def __init__(self, controller):
        self.controller = controller

    def can_handle(self, message):
        if message.upper() == self.__open_message or message.upper() == self.__close_message:
            return True

        return False

    def handle(self, message):
        if message.upper() == self.__open_message:
            self.controller.open_grips()
        elif message.upper() == self.__close_message:
            self.controller.close_grips()
