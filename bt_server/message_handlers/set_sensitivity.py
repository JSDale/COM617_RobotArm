

class SetSensitivity:

    __set_message = 'SET:SENSITIVITY'

    def __init__(self, controller):
        self.controller = controller

    def can_handle(self, message):
        if message.upper().contains(self.__set_message):
            return True

        return False

    def handle(self, message):
        value = message.split(' ,')[1]
        self.controller.set_sensitivity(value)
